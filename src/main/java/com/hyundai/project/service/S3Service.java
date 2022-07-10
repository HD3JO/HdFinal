package com.hyundai.project.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.swing.text.Document;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.google.inject.spi.Element;
import com.google.inject.spi.Elements;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class S3Service {
	private AmazonS3 s3Client;

	@Value("${cloud.aws.credentials.accessKey}")
	private String accessKey;

	@Value("${cloud.aws.credentials.secretKey}")
	private String secretKey;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Value("${cloud.aws.region.static}")
	private String region;

	@PostConstruct
	public void setS3Client() {
	    AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

	    s3Client = AmazonS3ClientBuilder.standard()
	            .withCredentials(new AWSStaticCredentialsProvider(credentials))
	            .withRegion(this.region)
	            .build();
	}
	
	public String upload(MultipartFile multipartFile, String fileName, String dirName) throws IOException {
	    File uploadFile = convert(multipartFile).orElseThrow(() -> new IllegalArgumentException("파일변환실패"));

	    fileName = dirName + fileName;
	    String uploadImageUrl = putS3(uploadFile, fileName);
	    removeNewFile(uploadFile);
	    return uploadImageUrl;
	}

	private void removeNewFile(File targetFile) {
	    if(targetFile.delete()) {
	        log.info("파일이 삭제되었습니다.");
	    }else {
	        log.info("파일이 삭제되지 못했습니다.");
	    }
	}

	private Optional<File> convert(MultipartFile file) throws  IOException {
	    File convertFile = new File(file.getOriginalFilename());
	    if(convertFile.createNewFile()) {
	        try (FileOutputStream fos = new FileOutputStream(convertFile)) {
	            fos.write(file.getBytes());
	        }
	        return Optional.of(convertFile);
	    }
	    return Optional.empty();
	}
	
	private String putS3(File uploadFile, String fileName) {
	    s3Client.putObject(
	            new PutObjectRequest(bucket, fileName, uploadFile)
	                    .withCannedAcl(CannedAccessControlList.PublicRead)
	    );
	    return s3Client.getUrl(bucket, fileName).toString();
	}
	/*
	public Post parseContextAndMoveImages(Post post) {
	    Document doc = Jsoup.parse(post.getContext());
	    String context = post.getContext();
	    Elements images = doc.getElementsByTag("img");

	    if (images.size() > 0) {
	        for (Element image : images) {
	            String source = image.attr("src");

	            if (!source.contains("/temp/")) {
	                continue;
	            }

	            source = source.replace("https://ninda-file.s3.ap-northeast-2.amazonaws.com/", "");
	            String newSource = LocalDate.now().toString() + "/" + source.split("/")[2];

	            context = context.replace(source, newSource);

	            s3Service.update(source, newSource);

	            try {
	                Photo photo = Photo.builder()
	                        .UUID(newSource.split("/")[1].split("_")[0])
	                        .fileName(URLDecoder.decode(newSource.split("/")[1].split("_")[1], "UTF-8"))
	                        .filePath(newSource.split("/")[0] + "/")
	                        .post(post)
	                        .build();
	                photoRepository.save(photo);
	            } catch (UnsupportedEncodingException e) {
	                e.printStackTrace();
	            }

	        }
	    }

	    post.update(post.getTitle(), context);
	    return post;
	}
	*/
	public void update(String oldSource, String newSource) {
	    try {
	        oldSource = URLDecoder.decode(oldSource, "UTF-8");
	        newSource = URLDecoder.decode(newSource, "UTF-8");
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }

	    moveS3(oldSource, newSource);
	    deleteS3(oldSource);
	}
	
	private void moveS3(String oldSource, String newSource) {
	    s3Client.copyObject(bucket, oldSource, bucket, newSource);
	}

	private void deleteS3(String source) {
	    s3Client.deleteObject(bucket, source);
	}
	
}
