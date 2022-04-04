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

@WebServlet("/content.do")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ContentServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한국어 코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	
		
		// 전체리스트에서 회원이름을 클릭했을 때 get 방식으로 넘어온 회원번호에
		// 해당하는 회원의 정보를 데이터베이스에서 조회 후에 해당 회원의 정보를 view page로 이동시키는 비지니스 로직
		
		int human_number = Integer.parseInt(request.getParameter("num"));
		
		// 데이터 억세스 dao값의 객체를 생성해주기
		HumanDAO dao = new HumanDAO();
		
		// DTO객체에 dao객체를 넣어주기 dao객체는 메서드를 참고한다.
		HumanDTO cont = dao.getConectHuman(human_number);
		
		request.setAttribute("human_content", cont);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/human_content.jsp");
		
		rd.forward(request, response);
	}
}
