package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;

@WebServlet("/delete.do")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get 방식으로 넘겨 받은 회원 번호에 해당하는 회원을 
		// MEMBER10 테이블에서 삭제시키는 비지니스 로직.
		
		response.setContentType("text/html; charset=UTF-8");
		
		
		int member_no = Integer.parseInt(request.getParameter("num").trim());
		
		MemberDAO dao = new MemberDAO();
		
		int res = dao.deleteMember(member_no);
		
		dao.updateNum(member_no); // 글 번호를 재작업
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) {
			out.println("<script>");
			out.println("alert('회원삭제성공')");
			out.println("location.href='select.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원삭제실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}
}
