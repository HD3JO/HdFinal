package com.hyundai.project.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hyundai.project.dto.BCountDTO;
import com.hyundai.project.dto.BoardDTO;
import com.hyundai.project.dto.ReplyDTO;
import com.hyundai.project.service.BoardService;

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
	
	@Value("${image.upload.path}")
	private String uploadPath;

	@Value("${resource.handler}")
	private String resourceHandler;
	
	@PostMapping("/admin/post/imageUpload")
    public void postImage(MultipartFile upload, HttpServletResponse res, HttpServletRequest req){

        OutputStream out = null;
        PrintWriter printWriter = null;
        System.out.println("@@@@@@@@실행됨@@@@@@@@@@@@@@@@ ");
        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=utf-8");

        try{

            UUID uuid = UUID.randomUUID();
            String extension = FilenameUtils.getExtension(upload.getOriginalFilename());

            byte[] bytes = upload.getBytes();

            // 실제 이미지 저장 경로
            String imgUploadPath = uploadPath + File.separator + uuid + "." + extension;

            // 이미지 저장
            out = new FileOutputStream(imgUploadPath);
            out.write(bytes);
            out.flush();

            // ckEditor 로 전송
            printWriter = res.getWriter();
            String callback = req.getParameter("CKEditorFuncNum");
            String fileUrl = "/admin/post/image/" + uuid + "." + extension;

            printWriter.println("<script type='text/javascript'>"
                    + "window.parent.CKEDITOR.tools.callFunction("
                    + callback+",'"+ fileUrl+"','이미지를 업로드하였습니다.')"
                    +"</script>");

            printWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(out != null) { out.close(); }
                if(printWriter != null) { printWriter.close(); }
            } catch(IOException e) { e.printStackTrace(); }
        }
    }
	
}
