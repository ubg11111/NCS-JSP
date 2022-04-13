package com.emp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.emp.action.*;

public class FrontController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		// 한글 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	
	
		// getRequestURI() : "/프로젝트명/파일명(*.do)" 라는 문자열을 
		//						반환해 주는 메서드.
		
		String uri = request.getRequestURI();
		System.out.println("uri >>> " + uri);
		
		
		// getContextPath() : 현재 프로젝트명을 문자열로 반환해 주는 메서드.
		String path = request.getContextPath();
		System.out.println("path >>> " + path);
		
		
		// uri에 path 프로젝트명을 문자열로 변환후 변환한값만큼 + 1 정의
		String command = uri.substring(path.length() + 1);
		System.out.println("Command >>> " + command);
		
		Action action = null;
		
		if(command.equals("select.do")) {
			action = new EmpList();
		}
		
		// action  메서드를 호출
		String path1 = action.execute(request, response);
		
		//RequestDispatcher 클래스를 통하여 path1 변수를 보내줌. (path 주소값이 바뀔때마다 조건문에의해서 값이 보내짐)
		RequestDispatcher rd = request.getRequestDispatcher(path1);
		
		rd.forward(request, response);
		
	}
}
