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
import com.products.model.ProductDTO;


@WebServlet("/update.do")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // 제품번호에 해당하는 제품의 정보를 조회해서 수정 폼 페이지로 이동시키는 비즈니스 로직.
		
		int productNumber = Integer.parseInt(request.getParameter("no").trim());
		
		ProductDAO dao = ProductDAO.getInstance();
		
		ProductDTO dto = dao.getContent(productNumber);
		
		List<CategoryDTO> categoryList =  dao.getCategoryList();
		
		request.setAttribute("modify", dto);
		request.setAttribute("List", categoryList);
		
		RequestDispatcher rd = 
				request.getRequestDispatcher("view/product_update.jsp");
		
		rd.forward(request, response);
		
	}
}
