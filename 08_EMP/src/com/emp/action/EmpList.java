package com.emp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpDAO;
import com.emp.model.EmpDTO;

public class EmpList implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// EMP 리스트를 가져오는 비즈니스 로직
		
		// dao 싱글턴 객채를 생성
		EmpDAO dao = EmpDAO.getInstance();
		
		// List<EmpDTO>를 통해서 ArrayList객체를 생성 (다형성)
		List<EmpDTO> EmpList = dao.getEmpList();
		
		// setAttribute 메서드를 통해서 키와 벨류값으로 리스트를 저장.
		request.setAttribute("List", EmpList);
		
		// 해당 List클래스를 JSP에 전달하기
		return "view/emplist.jsp";
	}
}
