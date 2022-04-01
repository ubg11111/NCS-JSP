package com.khie.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * DAO(Data Access Object) : 데이터 접근 객체 ==> DB에 접속 (연동)하는 객체.
 * - DAO란 데이터베이스에 접속해서 데이터 추가,수정,삭제, 조회등의 작업을 하는 클래스.
 * - 일반적으로 JSP 또는 Servlet에서 위의 작업들을 같이 사용할 수 있지만,
 * 		유지보수, 코드의 모듈화를 위해서 DAO 클래스를 따로 만들어서 사용을 함.
 * 
 * 
 */


public class DeptDAO {
	
	Connection con = null; // DB와 연동하는 객체
	
	String sql = null; // DB와 연동하는 객체
	PreparedStatement pstmt = null; // DB에 SQL문을 전송하는 객체
	ResultSet rs = null; // SQL문을 실행한 후 결과값을 가지고 있는 객체
	
	public DeptDAO() { // 기본 생성자
		
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
		
	} // 기본생성자의 end
	
	// selectList() 라는 메서드를 만들자.
	// Dept 테이블에서 부서목록 전체리스트를 조회하여 해당 리스트를 반환하는 메서드
	public List<DeptDTO> selectList(){
		
		List<DeptDTO> list = new ArrayList<DeptDTO>(); // 다형성
		
		
		try {
			// 3단계 : 데이터베이스에 SQL문을 전송하기 위한 쿼리문 작성.
			sql = "select * from dept order by deptno"; 
			
			// 4단계 : SQL문을 전송객체에 저장
			pstmt = con.prepareStatement(sql);
			
			// 5단계 : SQL문을 데이터베이스에 전송 및 SQL문 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				DeptDTO dto = new DeptDTO();
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				
				dto.setDeptno(deptno);
				dto.setDname(dname);
				dto.setLoc(loc);
				
				System.out.println("dto >>> " + dto);
				
				list.add(dto);
				
			}
			
			// 6단계 : 오픈된 자원 종료시키기
			rs.close(); pstmt.close(); con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	} // selectList() 메서드 end
	
	
	public int insertDept(DeptDTO dto) {
		int result = 0;
		
		try {
			//1. 데이터베이스에 SQL문을 전송하기 위한 쿼리문을 작성.
			sql = "insert into dept values(?,?,?)";
			
			//2. SQL문을 데이터베이스 전송 객체에 저장.
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getDeptno());
			pstmt.setString(2, dto.getDname());
			pstmt.setString(3, dto.getLoc());
			
			
			//3. SQL문을 데이터베이스에 전송 및 실행
			result = pstmt.executeUpdate();
			
			// 4. 오픈되어있는 자원을 종료
			pstmt.close(); con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}// inserDept() end문
	
	// 매개변수로 넘어온 부서번호를 DB에서 삭제하는 메서드.
	public int deletDept(int no) {
		int result = 0;
		
		try {
			// 1. SQL문을 데이터베이스에 전송할 쿼리문을 작성.
			sql = "delete from dept where deptno = ?";
			// 2. SQL문을 데이터베이스에 전송 객체에 저장.
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			// 3. SQL문을 데이터베이스에 전송 및 실행
			result = pstmt.executeUpdate();
			
			// 4. 오픈된 자원 종료
			pstmt.close(); con.close(); 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	} // deleteDept() 메서드 end
	
}
