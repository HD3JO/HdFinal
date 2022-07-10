package com.hyundai.project.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hyundai.project.dto.BCountDTO;
import com.hyundai.project.dto.BoardDTO;
import com.hyundai.project.dto.ReplyDTO;
import com.hyundai.project.service.BoardService;
import com.hyundai.project.service.S3Service;

import lombok.RequiredArgsConstructor;

@RestController
public class SommunityRESTController {
	
	@Autowired
	BoardService boardService;
	
	@PostMapping(value="/getBoard")
	public List<BoardDTO> getBoardList(@RequestBody BoardDTO boardDTO) {
		return boardService.getBoardList(boardDTO.getBrand());
	} 
	
	@PostMapping(value="/getPageBoard")
	public List<BoardDTO> getPageBoard(@RequestBody BoardDTO boardDTO) {
		System.out.println(boardDTO);
		return boardService.getPageBoard(boardDTO);
	} 
	
	@PostMapping(value="/delBoard")
	public void delBoard(@RequestParam("bid") String bid) {
		boardService.delBoard(bid);
	}
	
	@PostMapping(value="/enrollReply")
	public boolean enrollReply(@RequestBody ReplyDTO replyDTO) {
		boardService.enrollReply(replyDTO);
		return true;
	}
	
	@PostMapping(value="/getReplyList")
	public List<ReplyDTO> getReplyList (@RequestBody ReplyDTO replyDTO) {
		return boardService.getReplyList(replyDTO.getBid());
	}
	
	@PostMapping(value="/delReply")
	public void delReply(@RequestParam("rno") String rno) {
		boardService.delReply(rno);
	}
	
	@PostMapping(value="/incLikes")
	public boolean incLikes(@RequestBody BCountDTO bCountDTO) throws SQLException {
		boardService.incLikes(bCountDTO);
		return true;
	}
	
	@PostMapping(value="/getLikesCount")
	public int getLikesCount(@RequestParam("bid") String bid) {
		return boardService.getLikesCount(bid);
	}
	
	
	@RequestMapping(value="/imageUpload", method = RequestMethod.POST)
    public void imageUpload(HttpServletRequest request,
            HttpServletResponse response, MultipartHttpServletRequest multiFile
            , @RequestParam MultipartFile upload) throws Exception{
		
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		
        // 랜덤 문자 생성
        UUID uid = UUID.randomUUID();
        
        OutputStream out = null;
        PrintWriter printWriter = null;
        
        //인코딩
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        
        try{
            
            //파일 이름 가져오기
            String fileName = upload.getOriginalFilename();
            byte[] bytes = upload.getBytes();
            
            //이미지 경로 생성
            String path = "C:\\Users\\Myeongha\\Desktop\\upload";
            String ckUploadPath = path + uid + "_" + fileName;
            File folder = new File(path); // 이미지 저장경로
            
            //해당 디렉토리 확인
            if(!folder.exists()){
                try{
                    folder.mkdirs(); // 폴더 생성
                }catch(Exception e){
                    e.getStackTrace();
                }
            }
            
            out = new FileOutputStream(new File(ckUploadPath));
            out.write(bytes);
            out.flush(); // outputStram에 저장된 데이터를 전송하고 초기화
            
            String callback = request.getParameter("CKEditorFuncNum");
            printWriter = response.getWriter();
            String fileUrl = "/imageShow?uid=" + uid + "&fileName=" + fileName;  // 작성화면
            
        // 업로드시 메시지 출력
          printWriter.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
          printWriter.flush();
            
        }catch(IOException e){
            e.printStackTrace();
        } finally {
          try {
           if(out != null) { out.close(); }
           if(printWriter != null) { printWriter.close(); }
          } catch(IOException e) { e.printStackTrace(); }
         }
        
        return;
    }
	
	@RequestMapping(value="/imageShow")
    public void ckSubmit(@RequestParam(value="uid") String uid
    		, @RequestParam(value="fileName") String fileName
    		, HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
    	
    	//서버에 저장된 이미지 경로
		String path = "C:\\Users\\Myeongha\\Desktop\\upload";	// 저장된 이미지 경로
    	System.out.println("path:"+path);
    	String sDirPath = path + uid + "_" + fileName;
    	
    	File imgFile = new File(sDirPath);
    	
    	//사진 이미지 찾지 못하는 경우 예외처리로 빈 이미지 파일을 설정한다.
    	if(imgFile.isFile()){
    		byte[] buf = new byte[1024];
    		int readByte = 0;
    		int length = 0;
    		byte[] imgBuf = null;
    		
    		FileInputStream fileInputStream = null;
    		ByteArrayOutputStream outputStream = null;
    		ServletOutputStream out = null;
    		
    		try{
    			fileInputStream = new FileInputStream(imgFile);
    			outputStream = new ByteArrayOutputStream();
    			out = response.getOutputStream();
    			
    			while((readByte = fileInputStream.read(buf)) != -1){
    				outputStream.write(buf, 0, readByte); 
    			}
    			
    			imgBuf = outputStream.toByteArray();
    			length = imgBuf.length;
    			out.write(imgBuf, 0, length);
    			out.flush();
    			
    		}catch(IOException e){
    			e.printStackTrace();
    		}finally {
    			outputStream.close();
    			fileInputStream.close();
    			out.close();
    			}
    		}
    }
	
}
