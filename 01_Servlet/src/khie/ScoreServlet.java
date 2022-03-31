package khie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ScoreServlet
 */
@WebServlet("/score")
public class ScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ScoreServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// 인코딩을 전송 및 전달해주자
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	
	// 서버로부터 요청하기
	String studentName = request.getParameter("name"); 
	int studentKor = Integer.parseInt(request.getParameter("kor"));
	int studentEng = Integer.parseInt(request.getParameter("eng")); 
	int studentMat = Integer.parseInt(request.getParameter("mat")); 
	
	// 총점
	int total = studentKor + studentEng + studentMat;
	
	// 평균
	double avg = (total / 3.0);
	
	
	// 학점
	String grade = "";
	
	if(avg >= 90) {
		grade = "A학점";
	}else if(avg >= 80) {
		grade = "B학점";
	}else if(avg >= 70) {
		grade = "C학점";
	}else if(avg >= 60) {
		grade = "D학점";
	}else {
		grade = "F학점";
	}
	
	
	// 서버로부터 응답 받기.
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out = response.getWriter();
	
	out.println("<html>");
	out.println("<head></head>");
	out.println("<body>");
		
		out.println("<div align='center'>");
		out.println("<table border='1' cellspacing='0' width='200'>");
		
		
		out.println("<tr>");
		out.println("<th>");
		out.println("이름");
		out.println("</th>");
		out.println("<td>");
		out.println(studentName);
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>");
		out.println("국어점수");
		out.println("</th>");
		out.println("<td>");
		out.println(studentKor + "점");
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("<tr>");
		out.println("<th>");
		out.println("영어점수");
		out.println("</th>");
		out.println("<td>");
		out.println(studentEng + "점");
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("<tr>");
		out.println("<th>");
		out.println("수학점수");
		out.println("</th>");
		out.println("<td>");
		out.println(studentMat + "점");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>");
		out.println("총 점수");
		out.println("</th>");
		out.println("<td>");
		out.println(total + "점");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>");
		out.println("평균 점수");
		out.println("</th>");
		out.println("<td>");
		out.println(String.format("%.1f점",avg));
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>");
		out.println("학점");
		out.println("</th>");
		out.println("<td>");
		out.println(grade);
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("</table>");
		out.println("</div>");
	out.println("</body>");
	out.println("</html>");
	
	}
}
