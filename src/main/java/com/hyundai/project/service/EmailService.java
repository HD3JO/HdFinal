package com.hyundai.project.service;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.EmailDTO;
import com.hyundai.project.dto.MailHandler;
import com.hyundai.project.dto.OrderCompleteDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Component
public class EmailService {

    @Autowired(required=false)
    private JavaMailSender mailSender;

    public void sendMail(EmailDTO email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getAddress());
        message.setFrom("yearsk@naver.com"); //from 값을 설정하지 않으면 application.yml의 username값이 설정됩니다.
        message.setSubject(email.getTitle());
        message.setText(email.getMessage());

        mailSender.send(message);
    }
    
    public void sendMailWithFiles(EmailDTO email, List<OrderCompleteDTO> completeList) throws MessagingException, IOException {
        MailHandler mailHandler = new MailHandler(mailSender);
        mailHandler.setTo(email.getAddress());
        mailHandler.setFrom("yearsk@naver.com");
        mailHandler.setSubject(email.getTitle());
        mailHandler.setText("<h1>한섬3조</h1>"+"<a href='http://localhost:80/order/orderStatus?oid=" + completeList.get(0).getOid() +"'>주문확인</a>", true);
        mailHandler.send();
        
        //mailHandler.setInline("thehandsome-logo", "static/images/handsome-logo.jpg");
        //        StringBuffer htmlContent = new StringBuffer();
//        htmlContent.append("<html>");
//        htmlContent.append("<body>");
//        htmlContent.append("order");
//        mailHandler.setText(htmlContent.toString(), true);
        //"<p>" + email.getMessage() + "<p> <a href='http://localhost:80/order/orderStatus?'>";
        //mailHandler.setAttach("test.txt", "static/test.txt");
        
    }
}
