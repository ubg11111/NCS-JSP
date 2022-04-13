package com.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MeberListAction implements Action{ // 다형성 조상이 자식을 참조하는경우 (구현)

	@Override // 오버라이딩(메서드 재정의)
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// MEMBER10 테이블에 있는 회원 전체 리스트를 조회하여
		// view page로 이동시키는 비즈니스 로직.
		
		
		MemberDAO dao = MemberDAO.getInstance();
		
		List<MemberDTO> memberList = dao.getMemberList();
		
		// DB에서 가져온 전체 회원 리스트를 view page로 이동시켜야한다.
		request.setAttribute("List", memberList);
		
		
		// 리턴으로 해당 메서드를 반환해준다.
		return "view/member_list.jsp";
	
	
	}
}
