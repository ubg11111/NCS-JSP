package com.khie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khie.model.DeptDAO;


@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteServlet() {
        super();
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 삭제 버튼을 누르면 get 방식으로 넘어온 부서번호를 DB에서 삭제하는 작업.
		response.setContentType("text/html; charset=UTF-8");
		
		// 1. 삭제 버튼을 눌럿을 때 get 방식으로 넘어오는 부서번호를 받아야 한다.
		int no = Integer.parseInt(request.getParameter("no").trim());
		
		// 2. 삭제할 부서번호를  DB에 넘겨 주어야 한다.
		DeptDAO dao = new DeptDAO();
		
		int res = dao.deletDept(no);
		
		PrintWriter out = response.getWriter();
		
		if(res > 0 ) { // 부서추가 성공한경우 1값을 반환해야한다.
			out.println("<script>");
			out.println("alert('부서삭제 성공!!!!')");
			out.println("location.href='select' ");
			out.println("</script>");
		} 
		else { // 부서추가가 실패한경우 res값이 0을 반환한 경우
			out.println("<script>");
			out.println("alert('부서삭제 실패!!!!')");
			out.println("history.bakc()"); // 이전페이지로 이동
			out.println("</script>");
		}
	}
}
