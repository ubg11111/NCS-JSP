package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;

@WebServlet("/delete_ok.do")
public class DeleteOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteOkServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 삭제 폼페이지에서 넘어온 글 번호에 해당하는 게시글을 DB에서 삭제하는 비즈니스로직.
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		String board_pwd = request.getParameter("pwd").trim();
		int board_no = 
				Integer.parseInt(request.getParameter("board_no").trim());
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int check = dao.deleteBoard(board_no,board_pwd);
		
		dao.updateSequence(board_no);
		
		PrintWriter out = response.getWriter();
		
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('게시글 삭제 성공')");
			out.println("location.href='select.do'");
			out.println("</script>");
		} else if(check == -1) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다')");
			out.println("history.go(-1)");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게시글 삭제 실패')");
			out.println("history.go(-1)");
			out.println("</script>");
		}
	}
}
