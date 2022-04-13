package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberUpdateOk implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		String Member_id = request.getParameter("member_id");
		String Member_name = request.getParameter("member_name");
		String Member_pwd = request.getParameter("member_pwd");
		int Member_age = 
				Integer.parseInt(request.getParameter("member_age"));
		int Member_mileage = 
				Integer.parseInt(request.getParameter("member_mileage"));
		String Member_job = request.getParameter("member_job");
		String Member_addr = request.getParameter("member_addr");
		
		// type="hidden" 데이터도 받아주기
		int Member_no = 
				Integer.parseInt(request.getParameter("mem_num").trim());
		
		String db_pwd = request.getParameter("db_pwd").trim();
		
		
		MemberDTO dto = new MemberDTO();
		
		dto.setNum(Member_no);
		
		dto.setMemid(Member_id);
		dto.setMemname(Member_name);
		dto.setPwd(Member_pwd);
		dto.setAge(Member_age);
		dto.setMileage(Member_mileage);
		dto.setJob(Member_job);
		dto.setAddr(Member_addr);
		
		
		
		MemberDAO dao = MemberDAO.getInstance();
		
		PrintWriter out = response.getWriter();
		
		if(dto.getPwd().equals(db_pwd)) { // 비밀번호가 맞는 경우 
			int check = dao.MemberUpdate(dto);
			
			if(check > 0) {
				out.println("<script>");
				out.println("alert('정보 수정 성공')");
				out.println("location.href='content.do?num=+"+dto.getNum()+"'");
				out.println("</script>");
	
			} else {
				out.println("<script>");
				out.println("alert('정보수정 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
			
		} else { // 비밀번호가 다른경우
				out.println("<script>");
				out.println("alert('비밀번호가 틀립니다.')");
				out.println("history.back()");
				out.println("</script>");
		}
		
		return null;
	}
}
