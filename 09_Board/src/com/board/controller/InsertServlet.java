package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

@WebServlet("/insert.do")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	
	public InsertServlet() {
        super();
    }
	
    
    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 게시글 폼 페이지에서 넘어온 데이터들을 DB에 저장하는 비즈니스 로직.
    	
    	// 1단계 : 한글 인코딩을 처리해준다
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");
    	
    	
    	// 2단계 : 게시물 입력 폼 페이지에서 넘어온 데이터들을 받아 주자.
    	String board_write = request.getParameter("writer").trim();
    	String board_title = request.getParameter("title").trim();
    	String board_content = request.getParameter("content").trim();
    	String board_pwd = request.getParameter("pwd").trim();
    	
    	
    	// 3단계 : DB에 전송해 주어야 함. 이 때 DTO객체의 setter() 메서드의 인자에
    	// 			2단계에서 넘겨 받은 정보들을 넘겨 주자.
    	
    	
    	BoardDTO dto = new BoardDTO();
    	
    	dto.setBoard_writer(board_write);
    	dto.setBoard_title(board_title);
    	dto.setBoard_cont(board_content);
    	dto.setBoard_pwd(board_pwd);
    	
    	// 4단계 : DTO 객체를 DB에 전송해 주어야 한다.
    	BoardDAO dao = BoardDAO.getInstance();
    	
    	int check = dao.insertBoard(dto);
    
    	PrintWriter out = response.getWriter();
    	
    	if(check > 0) {
    		out.println("<script>");
    		out.println("alert('게시글 추가 성공')");
    		out.println("location.href='select.do'");
    		out.println("</script>");
    	} else {
    		out.println("<script>");
    		out.println("alert('게시글 추가 실패')");
    		out.println("history.back()");
    		out.println("</script>");
    	}
    	
    }
}