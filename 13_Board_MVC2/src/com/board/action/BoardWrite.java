package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardWrite implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// get 폼으로 넘어온 글쓰기 비즈니스 로직 작성하기.
		
		String board_name = request.getParameter("writer");
		String board_title = request.getParameter("title");
		String board_content = request.getParameter("content");
		String board_pwd = request.getParameter("pwd");
		
		
		BoardDTO dto = new BoardDTO();
		
		// DB에 전송할 dto 객체만들기
		
		dto.setBoard_wirter(board_name);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_content);
		dto.setBoard_pwd(board_pwd);
		
		// DTO 객체를 DAO에 전송해주기
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int check = dao.BoardWriteOk(dto);
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('글쓰기 성공')");
			out.println("location.href='select.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('글쓰기 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}
}
