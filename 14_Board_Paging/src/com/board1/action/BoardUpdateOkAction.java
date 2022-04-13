package com.board1.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

public class BoardUpdateOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// update에서 폼으로 가져온 내용을 저장해주는 비즈니스 로직.
		
		String board_writer = request.getParameter("writer");
		String board_title = request.getParameter("title");
		String board_content = request.getParameter("content");
		String board_pwd = request.getParameter("pwd");
		
		// 히든으로 넘어온 데이터들도 받아주자
		int board_no = 
				Integer.parseInt(request.getParameter("board_no"));
		
		String db_pwd = request.getParameter("db_pwd");
		
		int nowPage = 
				Integer.parseInt(request.getParameter("page"));
		
		
		BoardDTO dto = new BoardDTO();
		
		dto.setBoard_wirter(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_content);
		dto.setBoard_pwd(board_pwd);
		dto.setBoard_no(board_no);
		
		BoardDAO dao = BoardDAO.getInstance();
		
		PrintWriter out = response.getWriter();
		
		if(dto.getBoard_pwd().equals(db_pwd)) {
			int check = dao.getBoardUpdate(dto);
			
			if(check > 0) {
				out.println("<script>");
				out.println("alert('수정성공')");
				out.println("location.href='board_content.do?no="+dto.getBoard_no()+"&page="+nowPage+"'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('수정실패')");
				out.println("history.go(-1)");
				out.println("</script>");
			}
		}else { // 비밀번호가 틀린경우
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.')");
			out.println("history.go(-1)");
			out.println("</script>");
		}
	}
}
