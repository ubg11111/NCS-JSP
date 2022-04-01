package human_controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import human_model.HumanDAO;
import human_model.HumanDTO;

@WebServlet("/select")
public class HumanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HumanServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 비즈니스 로직 작성
		
		// 1단계 DB연동 작업
		HumanDAO dao = new HumanDAO();
		
		// 2단계 Human테이블 전체를 조회
		List<HumanDTO> humanList = dao.getHumanList();
		
		// 3단계 DB에서 가져온 정보를 view page로 이동시켜야한다.
		request.setAttribute("human", humanList);
		
		// 4단계 페이지 이동을 진행하자.
		RequestDispatcher rd = 
				request.getRequestDispatcher("view/human_list.jsp");
		
		rd.forward(request, response); // forward메서드를 통해서 rd에서 요청하는 이동페이지에 대해서 요청 및 응답해주기
	}
}
