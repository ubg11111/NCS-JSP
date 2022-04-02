package human_controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import human_model.HumanDAO;

@WebServlet("/delete")
public class HumanDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HumanDeleteServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 한글 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 1. 삭제 버튼을 눌럿을 때 get 방식으로 넘어오는 부서번호를 받아야 한다.
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 2. 삭제할 부서번호를  DB에 넘겨 주어야 한다.
		HumanDAO dao = new HumanDAO(); // DAO 객체주소를 생성한다.
		
		// res 변수에 dao.deleteHuman() 메서드의 매개변수 no를 넘겨준다
		int res = dao.deleteHuman(no);
		
		// PrintWriter 내장클래스를 통해 해당값을 화면에 출력해준다.
		PrintWriter out = response.getWriter();
		
		if(res > 0) {
			out.println("<script>");
			out.println("alert('친구삭제 완료')");
			out.println("location.href='select'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('친구삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
}
