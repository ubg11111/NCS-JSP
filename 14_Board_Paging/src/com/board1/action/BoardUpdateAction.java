package com.board1.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

public class BoardUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// get으로 넘어온 비즈니스로직을 작성하기.
		
		int board_no = 
				Integer.parseInt(request.getParameter("no"));
		
		int nowPage = 
				Integer.parseInt(request.getParameter("page"));
		
		BoardDAO dao = BoardDAO.getInstance();
		
		
		BoardDTO cont = dao.getBoardCont(board_no);
		
		request.setAttribute("modify", cont);
		request.setAttribute("page", nowPage);
	}
}
