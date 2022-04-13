package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardUpdateOk implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 글수정 버튼을 클릭해서 넘어온 폼데이터를 가져오는 비즈니스 로직 작성하기.
		
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
		
		int check = dao.boardUpdate(dto);
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('수정성공')");
			out.println("location.href='board_content.do?no="+dto.getBoard_no()+"'");
			out.println("</script>");
		} else if(check == -1) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('수정실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}
}
