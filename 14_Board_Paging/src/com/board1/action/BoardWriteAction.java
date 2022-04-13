package com.board1.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

public class BoardWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 글쓰기 폼페이지에서 post형식으로 보낸 값을 작성하는 비즈니스로직.
		
		String board_writer = request.getParameter("writer");
		String board_title = request.getParameter("title");
		String board_content = request.getParameter("content");
		String board_pwd = request.getParameter("pwd");
		
		
		BoardDTO dto = new BoardDTO();
		
		dto.setBoard_wirter(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_content);
		dto.setBoard_pwd(board_pwd);
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int check = dao.getBoardWriter(dto);
		
		PrintWriter out = response.getWriter();
		
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('등록 성공')");
			out.println("location.href='board_list.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}
}
