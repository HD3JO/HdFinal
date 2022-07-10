package com.hyundai.project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hyundai.project.service.S3Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class S3ApiController {

    private final S3Service s3Service;

    @PostMapping("/api/upload")
    public String upload(MultipartHttpServletRequest multipartFile, HttpServletResponse response) throws IOException {

        MultipartFile file = multipartFile.getFile("upload");
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        String fileUrl = s3Service.upload(file, fileName, "temp/");
        
        System.out.println("fileName : " + fileName);
        System.out.println("fileUrl : " + fileName);
        
        PrintWriter printWriter = response.getWriter(); // 서버로 파일 전송 후 이미지 정보 확인을 위해 filename, uploaded, fileUrl 정보를 response 해주어야 함
        printWriter.println("{\"filename\" : \"" + fileName + "\", \"uploaded\" : 1, \"url\":\"" + fileUrl + "\"}");
        printWriter.flush();

        return null;
    }
}