package com.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

@WebServlet("/select.do")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 비지니스 로직
		// MEMBER10 테이블의 전체 회원목록을 화면에 보여달라고 요청.
		// DB에서 MEMBER10 테이블의 회원 전체 리스트를 조회하여 
		// 해당 전체 리스트를 view page로 응답하여 넘겨주면 됨.
		
		// 1단계 : DB 연동 작업
		MemberDAO dao = new MemberDAO();
		
		// 2단계 : MEMBER10 테이블의 회원 전체 리스트를 조회.
		
		List<MemberDTO> memberList = dao.getMemberList();
		
		// 3단계 : DB에서 가져온 정보를 view page로 이동시켜야 한다.
		request.setAttribute("member", memberList);
		
		
		// 4단계: 페이지 이동을 진행하자.
		RequestDispatcher rd = 
				request.getRequestDispatcher("view/member_list.jsp");
		
		rd.forward(request, response);
		
	}
}
