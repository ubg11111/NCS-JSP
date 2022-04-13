package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardUpdate implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 글수정 버튼을 눌러서 넘어온 폼의내용의 비즈니스 로직 작성하기
		
		
		int board_no = 
				Integer.parseInt(request.getParameter("no"));
		
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardDTO cont = dao.getBoardContent(board_no);
		
		request.setAttribute("modify", cont);
		
	}
}
