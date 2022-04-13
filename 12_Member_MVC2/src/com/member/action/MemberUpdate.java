package com.member.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberUpdate implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int Member_num = 
				Integer.parseInt(request.getParameter("num").trim());
		
		MemberDAO dao = MemberDAO.getInstance();
		
		
		MemberDTO update = dao.contentMember(Member_num);
		
		request.setAttribute("Update", update);
		
		
		return "view/member_update.jsp";
	}
}
