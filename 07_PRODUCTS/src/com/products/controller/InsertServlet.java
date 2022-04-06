package com.products.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.products.model.CategoryDTO;
import com.products.model.ProductDAO;

@WebServlet("/insert.do")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB에서 카테고리 테이블의 전체 리스트를 조회하여 
		// 카테고리 전체 리스트를 제품 등록 폼 페이지로 이동시키는 비즈니스 로직.
		
		
		ProductDAO dao = ProductDAO.getInstance();
		
		
		System.out.println("제품 등록 시 dao >>> " + dao);
		
		List<CategoryDTO> categoryList = dao.getCategoryList();
		
		
		request.setAttribute("cList", categoryList);
		
		
		RequestDispatcher rd = 
				request.getRequestDispatcher("view/product_insert.jsp");
		
		
		rd.forward(request, response);
	}
}
