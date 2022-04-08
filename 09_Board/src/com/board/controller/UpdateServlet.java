package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

@WebServlet("/update.do")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// get 방식으로 넘어온 글번호에 해당하는 게시물을 DB에서 조회하여 
			// 수정 폼 페이지(view page)로 이동시키는 비지니스 로직.
		
			int board_no = 
					Integer.parseInt(request.getParameter("no").trim());
			
			
			BoardDAO dao = BoardDAO.getInstance();
			
			BoardDTO cont = dao.getBoardCotent(board_no);
			
			
			request.setAttribute("modify", cont);
			
			RequestDispatcher rd = request.getRequestDispatcher("view/board_update.jsp");
			
			rd.forward(request, response);
			
	}
}
