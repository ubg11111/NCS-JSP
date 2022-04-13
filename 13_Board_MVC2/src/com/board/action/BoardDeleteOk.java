package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;

public class BoardDeleteOk implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 삭제폼 요청을 하였을때 삭제진행을 하기위한 비즈니스로직
		
		String board_pwd = request.getParameter("pwd");
		
		int board_no = 
				Integer.parseInt(request.getParameter("board_no"));
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int check = dao.boardDelete(board_no, board_pwd);
		
		dao.updateSequence(board_no);
		
		PrintWriter out = response.getWriter();
		
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('삭제성공')");
			out.println("location.href='select.do'");
			out.println("</script>");
		} else if(check == -1) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('삭제실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
}
