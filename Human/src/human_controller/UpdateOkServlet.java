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

@WebServlet("/update_ok")
public class UpdateOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateOkServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글인코딩 받아오기
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// get으로 넘어온 자료를 데이터베이스에 저장하기
		
		String human_name = request.getParameter("human_name");
		
		String human_job = request.getParameter("human_job");
		
		int human_age = 
				Integer.parseInt(request.getParameter("human_age"));
		
		String human_addr = request.getParameter("human_addr");
		
		String human_phone = request.getParameter("human_phone");
		
		//type="hidden"으로 넘어온 번허도 넣어줘야한다.
		int human_num = 
				Integer.parseInt(request.getParameter("num"));
		
		// DTO 객체에 해당 파라미터 넣어주기
		
		HumanDTO dto = new HumanDTO();
		
		
		dto.setName(human_name);
		dto.setJob(human_job);
		dto.setAge(human_age);
		dto.setAddr(human_addr);
		dto.setPhone(human_phone);
		dto.setId(human_num);
		
		HumanDAO dao = new HumanDAO();
		
		int res = dao.getHumanUpdate(dto);
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) {
			out.println("<script>");
			out.println("alert('친구수정완료')");
			out.println("location.href='content.do?num="+dto.getId()+"'");
			out.println("</script>");
		} else if(res == -1) {
			out.println("<script>");
			out.println("alert('친구 정보가 잘못되었습니다.')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('친구 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}
}
