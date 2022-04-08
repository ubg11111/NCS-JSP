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

@WebServlet("/content.do")
public class CotentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CotentServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get으로 넘어온 내용을 한글인코딩 해주기
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		// get으로 넘어온 폼 내용의 비즈니스 로직 작성하기
		int board_no = 
				Integer.parseInt(request.getParameter("no"));
		
		BoardDAO dao = BoardDAO.getInstance();
		
		// 조회수를 증가시켜 주는 메서드 호출
		dao.boardHit(board_no);
		
		// 상세 내역을 조회하는 메서드 호출
		BoardDTO cont = dao.getBoardCotent(board_no);
		
		request.setAttribute("board_cont", cont);
		
		RequestDispatcher rd = 
				request.getRequestDispatcher("view/board_content.jsp");
		
		rd.forward(request, response);
		
	}
}
