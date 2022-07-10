package com.hyundai.project.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.hyundai.project.dto.EmailDTO;
import com.hyundai.project.dto.MailHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class EmailService {

    @Autowired(required=false)
    private JavaMailSender mailSender;
    
    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendMail(EmailDTO email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getAddress());
        message.setFrom("gsmtpuser@gmail.com"); //from 값을 설정하지 않으면 application.yml의 username값이 설정됩니다.
        message.setSubject(email.getTitle());
        message.setText(email.getMessage());

        mailSender.send(message);
    }
    
    public void sendMailWithFiles(EmailDTO emailDTO, Map<String, Object> variables) throws Exception {
    	
    	Context context = new Context();
        variables.forEach((key, value)->{
        	context.setVariable(key,value);
        });
        
        System.out.println(variables+"@@@@@@@@@@@@@@@@@@@");
        //completeList = orderService.getOrderComplete(oid);
        
        MailHandler mailHandler = new MailHandler(mailSender);
        String html = templateEngine.process("emailtemplate", context);
        mailHandler.setText(html, true);
        mailHandler.setTo(emailDTO.getAddress());
        mailHandler.setFrom("gsmtpuser@gmail.com");
        mailHandler.setSubject(emailDTO.getTitle());
        mailHandler.send();
        
        //mailHandler.setText("<h1>한섬3조</h1>"+"<a href='http://localhost:80/order/orderStatus?oid=" + completeList.get(0).getOid() +"'>주문확인</a>", true);
       
//        StringBuffer htmlContent = new StringBuffer();
//        htmlContent.append("<html>");
//        htmlContent.append("<body>");
//        htmlContent.append("<table style=\"width:760px;padding:60px 20px 30px;margin:0 auto\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
//        		+ "	<tbody>\r\n"
//        		+ "		<tr>\r\n"
//        		+ "			<td style=\"border:0;padding:0;margin:0\">\r\n"
//        		+ "				<table style=\"width:760px;padding:0;margin:0\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
//        		+ "					<colgroup>\r\n"
//        		+ "						<col style=\"width:380px\">\r\n"
//        		+ "						<col style=\"width:380px\">\r\n"
//        		+ "					</colgroup>\r\n"
//        		+ "					<tbody>\r\n"
//        		+ "						<tr>\r\n"
//        		+ "							<td style=\"text-align:left;border:0;padding:0 0 5px\">\r\n"
//        		+ "								<a href=\"http://localhost:80/main\" style=\"border:0;padding:0 0 5px;margin:0\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://www.thehandsome.com&amp;source=gmail&amp;ust=1657247980877000&amp;usg=AOvVaw1ORlcS50EIvbzEaA6ZsOK7\"><img src=\"https://ci5.googleusercontent.com/proxy/X5B6mn8H3_MGqtKZlWDClYuVuxSy6HRc5spBA_THHbq91e9cmv-D07cAjtXuhdvI_Y-bOkVAy96NGuHw3mVYt0qBbgnoDp8JmCLPyWgvp9EbV_a5pfs=s0-d-e1-ft#http://www.thehandsome.com/_ui/desktop/common/images/email/logo.gif\" alt=\"thehandsome.com\" border=\"0\" class=\"CToWUd\"></a>\r\n"
//        		+ "							</td>\r\n"
//        		+ "						</tr>\r\n"
//        		+ "					</tbody>\r\n"
//        		+ "				</table>\r\n"
//        		+ "				<hr width=\"760px\">\r\n"
//        		+ "			</td>\r\n"
//        		+ "		</tr>\r\n"
//        		+ "\r\n"
//        		+ "		<tr>\r\n"
//        		+ "			<td style=\"color:#222;font-size:28px;line-height:40px;font-family:Malgun Gothic,Dotum,sans-serif;font-weight:bold;border:0;padding:50px 0 10px;margin:0\">주문 완료 안내</td>\r\n"
//        		+ "		</tr>\r\n"
//        		+ "		<tr>\r\n"
//        		+ "			<td style=\"color:#222;font-size:14px;line-height:20px;font-family:'MalgunSemilight',sans-serif;font-weight:lighter;border:0;padding:15px 0 0;line-height:1.7em\">\r\n"
//        		+ "			고객님, 안녕하십니까? 더<span class=\"il\">한섬</span>닷컴을 이용해주셔서 감사합니다.<br>\r\n"
//        		+ "			<br>\r\n"
//        		+ "			더<span class=\"il\">한섬</span>닷컴 고객님의 주문 완료 내용을 다음과 같이 링크로 안내드립니다.			\r\n"
//        		+ "			</td>\r\n"
//        		+ "		</tr>\r\n"
//        		+ "		<tr>\r\n"
//        		+ "			<td style=\"height:50px;border:0;padding:0;margin:0\"></td>\r\n"
//        		+ "		</tr>\r\n"
//        		+ "		<tr>\r\n"
//        		+ "		</tr>\r\n"
//        		+ "	</tbody>\r\n"
//        		+ "</table></body></html>");
//        mailHandler.setText(htmlContent.toString(), true);
        
      //mailHandler.setInline("thehandsome-logo", "static/images/handsome-logo.jpg");
        //"<p>" + email.getMessage() + "<p> <a href='http://localhost:80/order/orderStatus?'>";
        //mailHandler.setAttach("test.txt", "static/test.txt");
        
    }
}
