package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	
	Connection con = null; // DB와 연동하는 객체
	String sql = null; // DB와 연동하는 객체
	PreparedStatement pstmt = null; // DB에 SQL문을 전송하는 객체
	ResultSet rs = null; // SQL문을 실행한 후 결과값을 가지고 있는 객체
	
	public MemberDAO() {
		

		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "web";
		String password = "1234";
		
		
		try {
			// 1단계 오라클 드라이버를 로딩
			Class.forName(driver);
			
			
			// 2단계 오라클 데이터베이스 연결 진행
			con = DriverManager.getConnection(url, user, password);
			
			if(con != null) {
				System.out.println("데이터베이스 연결 성공");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // 기본생성자의 end 부분
	
	
	// MEMBER10 테이블에서 회원 전체 목록을 조회하는 메서드.
	public List<MemberDTO> getMemberList(){
		
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		
		try {
			// 1. DB에 전송할 SQL문 작성
			sql = "select * from member10 order by num desc";
			
			// 2. SQL문을 데이터베이스 전송 객체에 저장.
			pstmt = con.prepareStatement(sql);
			
			// 3. 데이터베이스에 SQL문을 전송 및 실행
			rs = pstmt.executeQuery();
			
			// 4. SQL문 실행 결과 값을 DTO 객체에 저장하여 list에 추가
			while(rs.next()) {
				
				MemberDTO dto = new MemberDTO();
				
				dto.setNum(rs.getInt("num"));
				dto.setMemid(rs.getString("memid"));
				dto.setMemname(rs.getString("memname"));
				dto.setPwd(rs.getString("pwd"));
				dto.setAge(rs.getInt("age"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setJob(rs.getString("job"));
				dto.setAddr(rs.getString("addr"));
				dto.setRegdate(rs.getString("regdate"));
				
				
				list.add(dto);
			}
			
			// 5. 데이터베이스에  있는 자원 종료 작업.
			rs.close(); pstmt.close(); con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}; // getMemeberList() 메서드 end 
}
