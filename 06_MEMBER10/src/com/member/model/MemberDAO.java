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
	
	
	// member10 테이블에 회원을 등록하는 메서드
	public int insertMember(MemberDTO dto) {
		
		int result = 0;
		int count = 0;
	
		try {
			sql = "select max(num) from member10";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "insert into member10 values(?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getMemid());
			pstmt.setString(3, dto.getMemname());
			pstmt.setString(4, dto.getPwd());
			pstmt.setInt(5, dto.getAge());
			pstmt.setInt(6, dto.getMileage());
			pstmt.setString(7, dto.getJob());
			pstmt.setNString(8, dto.getAddr());
			
			result = pstmt.executeUpdate();
			
			// 자원종료
			con.close(); rs.close(); pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	} // insertMember의 메서드 엔드
	
	// 회원번호에 해당하는 회원의 정보를 조회하는 메서드
	
	public MemberDTO getContentMember(int no) {
		
		MemberDTO dto = new MemberDTO();
		
		
		try {
			sql = "select * from member10 where num = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				dto.setNum(rs.getInt("num"));
				dto.setMemid(rs.getString("memid"));
				dto.setMemname(rs.getString("memname"));
				dto.setPwd(rs.getString("pwd"));
				dto.setAge(rs.getInt("age"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setJob(rs.getString("job"));
				dto.setAddr(rs.getString("addr"));
				dto.setRegdate(rs.getString("regdate"));
			}
			
			rs.close(); pstmt.close(); con.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	} // getContentMember 메서드 end
	
	
	// MEMBER10 테이블에서 회원번호에 해당하는 회원의 정보를 수정하는 메서드.
	public int updateMember(MemberDTO dto) {
		int result = 0;
		
		try {
			sql = "select * from member10 where num = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getNum());
			
			rs = pstmt.executeQuery();
		
			if(rs.next()) {
				if(rs.getString("pwd").equals(dto.getPwd())) {
					sql = "update member10 set age= ?,"
							+ " mileage = ?, job = ?, "
							+ " addr = ? where num = ?";
				
					pstmt = con.prepareStatement(sql);
					
					pstmt.setInt(1, dto.getAge());
					pstmt.setInt(2, dto.getMileage());
					pstmt.setString(3, dto.getJob());
					pstmt.setString(4, dto.getAddr());
					pstmt.setInt(5, dto.getNum());
				
					result = pstmt.executeUpdate();
				}
			} else{ // 위에 if조건문의 데이터베이스상의 비밀번호와 폼태그에서 입력한 비밀번호가 다른경우
				result = -1;
			}
			
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}// updateMember() 메서드 end
	
	
	// 회원번호에 해당하는 회원을 DB에서 삭제시키는 메서드
	public int deleteMember(int no) {
		
		int result = 0;
		
		try {
			sql = "delete from member10 where num = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
			// 자원종료
			 pstmt.close(); //con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	} // deleteMember() 메서드 end
	
	
	// 회원 번호 순번 다시 작업해 주는 메서드.
	public void updateNum(int no) {
		
		
		
		try {
			// 현재 삭제되는 선택번호에서 상위번호들은 전부 -1값이 내려가서 정렬된다.
			sql = "update member10 set num = num -1 where num > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,no);
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}// updateNum() 메서드 end
}
