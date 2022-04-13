package com.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.member.action.*;

// Servlet - FrontController
public class FrontController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 한글 인코딩을 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	
		
		// getRequestURI() : "/프로젝트명/파일명(*.do)" 라는 문자열을 
		//						반환해 주는 메서드.
		String uri = request.getRequestURI();
		System.out.println("URI >>>" + uri);
		
		
		// getContextPath() : 현재 프로젝트명을 문자열로 반환해 주는 메서드.
		String path = request.getContextPath();
		System.out.println("Path >>> " + path);
		
		String command = uri.substring(path.length() + 1);
		System.out.println("Command >>> " + command);
		
		Action action = null;
		
		if(command.equals("select.do")) {
			action = new MeberListAction();
			
		} 
		else if(command.equals("insert.do")) {
			action = new MemberJoinAction();
		} 
		else if(command.equals("insert_ok.do")){
			action = new MemberJoinOkAcition();
		}else if(command.equals("content.do")) {
			action = new MemberContentAction();
		}else if(command.equals("update.do")) {
			action = new MemberUpdate();
		}else if(command.equals("update_ok.do")) {
			action = new MemberUpdateOk();
		}else if(command.equals("delete.do")) {
			action = new MemberDeleteAction();
		}else if(command.equals("delete_ok.do")) {
			action = new MemberDeleteOkAction();
		}
			
		
		// 액션 메서드 호출 (MemberList)
		String path1 = action.execute(request, response);
		
		RequestDispatcher rd = request.getRequestDispatcher(path1);
		
		rd.forward(request, response);
		
	}
}
