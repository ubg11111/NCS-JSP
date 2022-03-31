package khie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginTest
 */
@WebServlet("/login_test")
public class LoginTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 폰트가 깨지지않게끔 응답 및 요청 처리하기
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		// 서버로부터 로그인 전송 포스트 요청하기
		// getParameter는 디폴트값이 스트링으로 시작되어 전송을 숫자로 보내는경우 int 형변환이 필요하다.

		String userId = request.getParameter("id"); 
		String userPwd = request.getParameter("pwd");
		
		// 복수의 값을 가져와야하는경우 아래와같이 배열형태와 getParameterValues를 사용해야 한다.
		// String[] numbers = request.getParameterValues
		
		// 서버에서 해당 요청사항을 응답하기
		response.setContentType("text/html; charset=UTF-8");
		
		// PrintWriter 자바클래스를 통해서 텍스트로 전송받은 텍스틀 통해서 값을 출력해준다
		PrintWriter out = response.getWriter();
		
		
		out.println("<html>");
		out.println("<body>");
		
		// 변수로 저장된 내용을 화면에 출력해줄 수 있다.
		out.println("당신의 아이디는 : " + userId + "입니다.<br>");
		out.println("당신의 비밀번호는 : " + userPwd + "입니다.<br>");
		
		out.println("</body>");
		out.println("</html>");
		
		// 만약 numbers라는 배열을 출력해야하는경우 for반복문을 통해서 순서대로 출력하면 된다.
		//		for(int i=0; i<numbers.length; i++) {
		//			out.println(numbers[i]);
		//		}
		
	}	
}
