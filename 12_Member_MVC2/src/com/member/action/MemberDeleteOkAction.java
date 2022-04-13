package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;

public class MemberDeleteOkAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 삭제 폼 페이지에서 넘어온 회원번호와 회원 비밀번호를 가지고
		// DB에서 회원번호에 해당하는 회원을 삭제하는 비지니스 로직
		
		String member_pwd = request.getParameter("mem_pwd").trim();
		int member_no = 
				Integer.parseInt(request.getParameter("mem_no").trim());
		MemberDAO dao = MemberDAO.getInstance();
		
		
		int check = dao.deleteMember(member_no, member_pwd);
		
		
		PrintWriter out = response.getWriter();
		
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('회원삭제성공')");
			out.println("location.href='select.do'");
			out.println("</script>");
		} else if(check == -1) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.')");
			out.println("history.go(-1)");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원삭제실패')");
			out.println("history.go(-1)");
			out.println("</script>");
		}
		
		return null;
	}
}
