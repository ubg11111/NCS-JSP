package khie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JoinServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청한 데이터에 한글이 있으면 깨지는 현상이 발생함.
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		// 1단계 : Ex05.jsp 페이지에서 넘어온 데이터를 받아주자.
		String userId = request.getParameter("id");
		String userPwd = request.getParameter("pwd");
		String userName = request.getParameter("name");
		String userPhone = request.getParameter("phone");
		String userAdder = request.getParameter("adder");
		// 여러개의 데이터가 넘어올 경우 사용하는 방법
		String[] hobbys = request.getParameterValues("hobby");
		
		// 2단계 : 웹 브라우저에 요청한 결과를 화면에 보여 주자.
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<table border='1' cellspacing='0'>");
		
		out.println("<tr>");
		out.println("<th>");
		out.println("아이디");
		out.println("</th>");
		out.println("<td>");
		out.println(userId);
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("<tr>");
		out.println("<th>");
		out.println("비밀번호");
		out.println("</th>");
		out.println("<td>");
		out.println(userPwd);
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>");
		out.println("이름");
		out.println("</th>");
		out.println("<td>");
		out.println(userName);
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("<tr>");
		out.println("<th>");
		out.println("전화번호");
		out.println("</th>");
		out.println("<td>");
		out.println(userPhone);
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>");
		out.println("주소");
		out.println("</th>");
		out.println("<td>");
		out.println(userAdder);
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>");
		out.println("취미");
		out.println("</th>");
		out.println("<td>");
		for(int i=0; i<hobbys.length; i++) {
			out.println(hobbys[i]);
		}
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("</body>");
		out.println("</html>");
		
		
	}

}
