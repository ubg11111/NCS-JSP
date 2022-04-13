package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.action.Action;
import com.board.action.BoardContent;
import com.board.action.BoardDeleteOk;
import com.board.action.BoardList;
import com.board.action.BoardUpdate;
import com.board.action.BoardUpdateOk;
import com.board.action.BoardWrite;

public class FrontController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 한글 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		
		// getRequestURI() : "/프로젝트명/파일명(*.do)" 라는 문자열을 
		//						반환해 주는 메서드.
		String uri = request.getRequestURI();
		System.out.println("uri >>>" + uri);
		
		// getContextPath() : 현재 프로젝트명을 문자열로 반환해 주는 메서드.
		String path = request.getContextPath();
		System.out.println("path >>> " + path);
		
		// command 연결하기 : uri path를 + 1 증가하여 지정하는 방식
		String command = uri.substring(path.length() + 1);
		System.out.println("command >>> " + command);
		
		
		Action action = null;
		
		String viewPage = null;
		
		if(command.equals("select.do")) {
			action = new BoardList();
			action.execute(request, response);
			viewPage = "view/board_list.jsp";
		} else if(command.equals("insert.do")) {
			viewPage = "view/board_write.jsp";
		} 
		else if(command.equals("board_write_ok.do")) {
			action = new BoardWrite();
			action.execute(request, response);
		}
		else if(command.equals("board_content.do")) {
			action = new BoardContent();
			action.execute(request, response);
			
			viewPage = "view/board_content.jsp";
		}else if(command.equals("board_update.do")) {
			action = new BoardUpdate();
			action.execute(request, response);
			viewPage = "view/board_update.jsp";
		}else if(command.equals("border_update_ok.do")) {
			action = new BoardUpdateOk();
			action.execute(request, response);
		}else if(command.equals("board_delete.do")) {
			viewPage = "view/board_delete.jsp";
		
		}else if(command.equals("board_delete_ok.do")) {
			action = new BoardDeleteOk();
			action.execute(request, response);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}
}
