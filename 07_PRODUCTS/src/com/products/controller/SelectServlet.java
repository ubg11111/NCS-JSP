package com.products.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.products.model.ProductDAO;
import com.products.model.ProductDTO;

@WebServlet("/select.do")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 제품 전체 목록 요청에 대한 응답을 해 주어야 함.
		// DB에서 PRODUCTS 테이블의 전체 제품 목록을 조회하여
		// 제품 전체 목록을 반환받은 후 view page로 이동 작업을 진행하는 비지니스 로직
		
		// static메서드에 접근하는 방법 클래스이름.멤버()
		ProductDAO dao = ProductDAO.getInstance();
	
		System.out.println("dao 객체 생성 후 >>> " + dao);
		
		List<ProductDTO> productList = dao.getProductList();
		
		// view page로 해당 데이터를 이동해 주어야 함.
		request.setAttribute("pList", productList);
	
		
		RequestDispatcher rd = request.getRequestDispatcher("view/product_list.jsp");
		
		rd.forward(request, response);
		
	}
}
