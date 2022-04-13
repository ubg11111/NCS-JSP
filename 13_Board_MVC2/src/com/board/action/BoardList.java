package com.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardList implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// Board의 전체 목록을 가져오는 비즈니스 로직
		
		BoardDAO dao = BoardDAO.getInstance();
		
		List<BoardDTO> BoardList = dao.getBoardList();
		
		// DB에서 가져오는 내용을 view page로 이동시켜야한다.
		request.setAttribute("List", BoardList);
		
	}
}
