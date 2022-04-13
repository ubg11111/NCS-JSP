package com.member.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberContentAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 회원번호에 해당하는 회원의 정보를 
		// DB에서 가져와서 view page로 이동시키는 비지니스 로직.
		
		
		int member_no = 
				Integer.parseInt(request.getParameter("num").trim());
		
		MemberDAO dao = MemberDAO.getInstance();
		
		
		MemberDTO content = dao.contentMember(member_no);
		
		request.setAttribute("Cont", content);
		
		return "view/member_content.jsp";
	}
}
