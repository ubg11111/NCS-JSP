package com.products.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.products.model.ProductDAO;

@WebServlet("/delete.do")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get 방식으로 넘어온 제품번호에 해당하는 제품을 
		// DB에서 삭제하는 비즈니스 로직
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		int Product_no = 
				Integer.parseInt(request.getParameter("no").trim());
	
		ProductDAO dao = ProductDAO.getInstance();
		
		
		int check = dao.deleteProduct(Product_no);
		
		PrintWriter out = response.getWriter();
		
		if(check > 0 ) {
			out.println("<script>");
			out.println("alert('제품 삭제 성공')");
			out.println("location.href='select.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('제품 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}
}
