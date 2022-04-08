package com.board1.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;




public class BoardDAO {
	
	Connection con = null; // DB연결 객체
	PreparedStatement pstmt = null ; // DB에서 SQL문을 전송하는 객체
	ResultSet rs = null; // SQL문 실행 후 결과 값을 가지고 있는 객체
	
	
	String sql = null;  // SQL문을 저장할 객체
	
	// ProductDAO 객체를 싱글턴 방식으로 만들어 보자.
	
	// 1단계 : 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로
	//			기본 생성자의 접근 제어자를 public에서 private으로 바꿔줘야한다.
	
	// 2단계 : ProductDAO 객체를 정적 멤버로 선언해 주어야 한다. - static으로 선언해야 한다.
	private static BoardDAO instance; // instance는 참조변수
	
	private BoardDAO() {} // 기본 생성자
	
	
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance()라는 메서드를
	// 만들어서 이 getInstance() 메서드에 외부에서 접근할 수 있게 해야 함.
	
	public static BoardDAO getInstance() {
		
		if(instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	} // getInstance() 메서드 end
	
	
	
	// DB를 연동하는 작업을 진행하는 메서드  -- DBCP 방식으로 데이터베이스와 연결 진행
	public void openConn() {
		
		// 1단계 : JNDI 서버 객체 생성
		try {
			// 1단계 : JNDI 서버 객체 생성
			Context ctx = new InitialContext();
		
			// 2단계 : lookup() 메서드를 이용하여 매칭되는 커넥션을 찾는다.
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
			
			// 3단계 : DataSource 객체를 이용하여 커넥션 객체를 하나 가져온다.
			con = ds.getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} //openConn 메서드의 end부분
	
	// board 테이블의 전체 레코드를 조회하는 메서드.
	
	public List<BoardDTO> getBoardList(){
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		

		try {
			openConn();
			sql = "select * from board order by board_no desc";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_wirter"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getNString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
				
				
				list.add(dto);
				
			}
			rs.close(); con.close(); pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	} //getBoardList end 
}
