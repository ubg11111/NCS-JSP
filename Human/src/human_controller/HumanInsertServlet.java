package human_controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import human_model.HumanDAO;
import human_model.HumanDTO;

@WebServlet("/insert_ok")
public class HumanInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public HumanInsertServlet() {
        super();
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 한국어 유니코드를 읽을 수 있도록 요청하기
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		// 1단계 : 부서등록 폼 페이지에서 넘어온 데이터들을 받아주어야 한다.
		// getParameter 메서드는 자료형이 문자형이기에 int자료형으로 받아오는경우 형변환을 해주어야한다.
		int humanId = Integer.parseInt(request.getParameter("id"));
		String humanName = request.getParameter("name");
		String humanJob = request.getParameter("job");
		int humanAge = Integer.parseInt(request.getParameter("age"));
		String humanAddr = request.getParameter("addr");
		String humanPhone = request.getParameter("phone");
		
		
		// 2단계 : DTO 객체를 이용하여 데이터베이스에 전송해야한다.
		HumanDTO dto = new HumanDTO(); // 객체주소를 생성
		
		dto.setId(humanId);
		dto.setName(humanName);
		dto.setJob(humanJob);
		dto.setAge(humanAge);
		dto.setAddr(humanAddr);
		dto.setPhone(humanPhone);
		
		// 3단계 : DB에 DTO 객체를 전송.
		HumanDAO dao = new HumanDAO();
		
		int res = dao.insertHuman(dto);
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) { // 부서추가가 성공한경우 1값을 반환한다.
			out.println("<script>");
			out.println("alert('추가완료')");
			out.println("location.href='select'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('추가실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}
}
