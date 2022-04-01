package com.khie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khie.model.DeptDAO;
import com.khie.model.DeptDTO;

@WebServlet("/insert_ok")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// insert.jsp 페이지에서 넘어온 데이터들을 DEPT 테이블에 저장하는 로직.
		
		// 한글 인코딩 처리 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 1단계 : 부서등록 폼 페이지에서 넘어온 데이터들을 받아주어야 한다.
		int deptNo = Integer.parseInt(request.getParameter("deptno").trim());
		
		String deptName = request.getParameter("deptName").trim();
		
		String location = request.getParameter("location").trim();
		
		// 2단계 : DTO 객체를 이용하여 데이터베이스에 전송해야한다.
		DeptDTO dto = new DeptDTO();
		
		dto.setDeptno(deptNo);
		dto.setDname(deptName);
		dto.setLoc(location);
		
		// 3단계 : DB에 DTO 객체를 전송.
		DeptDAO dao = new DeptDAO();
		
		int res = dao.insertDept(dto); // dto는 참조변수의 주소값이며 주소값을 dao 클래스에 넘겨주는것이다.
		
		PrintWriter out = response.getWriter();
		
		if(res > 0 ) { // 부서추가 성공한경우 1값을 반환해야한다.
			out.println("<script>");
			out.println("alert('부서추가 성공!!!!')");
			out.println("location.href='select' ");
			out.println("</script>");
		} else { // 부서추가가 실패한경우 res값이 0을 반환한 경우
			out.println("<script>");
			out.println("alert('부서추가 실패!!!!')");
			out.println("history.bakc()"); // 이전페이지로 이동
			out.println("</script>");
		}
		
	}

}
