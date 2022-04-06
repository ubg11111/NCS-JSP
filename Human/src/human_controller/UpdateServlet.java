package human_controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import human_model.HumanDAO;
import human_model.HumanDTO;

@WebServlet("/update.do")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 인코딩 받아오기
		// get으로 넘어온 폼 내용을 데이터베이스에 저장하기
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		int human_no = 
				Integer.parseInt(request.getParameter("num"));
		
		HumanDAO dao = new HumanDAO();
		
		HumanDTO cont = dao.getConectHuman(human_no);
		
		request.setAttribute("update", cont);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/human_update.jsp");
		
		rd.forward(request, response);
		
	}
}
