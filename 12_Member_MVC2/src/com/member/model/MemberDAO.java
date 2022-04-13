package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {
	
	Connection con = null; // DB연결 객체
	PreparedStatement pstmt = null ; // DB에서 SQL문을 전송하는 객체
	ResultSet rs = null; // SQL문 실행 후 결과 값을 가지고 있는 객체
	
	
	String sql = null;  // SQL문을 저장할 객체
	
	// ProductDAO 객체를 싱글턴 방식으로 만들어 보자.
	
	// 1단계 : 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로
	//			기본 생성자의 접근 제어자를 public에서 private으로 바꿔줘야한다.
	
	// 2단계 : ProductDAO 객체를 정적 멤버로 선언해 주어야 한다. - static으로 선언해야 한다.
	private static MemberDAO instance; // instance는 참조변수
	
	private MemberDAO() {} // 기본 생성자
	
	
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance()라는 메서드를
	// 만들어서 이 getInstance() 메서드에 외부에서 접근할 수 있게 해야 함.
	
	public static MemberDAO getInstance() {
		
		if(instance == null) {
			instance = new MemberDAO();
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
		
		
	// MEMBER10 테이블에서 전체 회원 리스트를 조회하는 메서드
	public List<MemberDTO> getMemberList(){
		
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		
		
		try {
			// 커넥션 풀 방식으로 데이터베이스와 연동작업
			openConn();
			sql = "select * from member10 order by num desc";
			pstmt = con.prepareStatement(sql);
		
			rs = pstmt.executeQuery();
			
			
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
			
			rs.close(); con.close(); pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} // getMemberList() end 부분
	
	
	// MEMBER10 테이블에 회원을 저장하는 메서드.
	public int insertMember(MemberDTO dto) {
		
		int result = 0, count = 0;
		
		try {
			openConn();
			sql = "select max(num) from member10";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "insert into member10 values(?,?,?,?,?,?,?,?, sysdate)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getMemid());
			pstmt.setString(3, dto.getMemname());
			pstmt.setString(4, dto.getPwd());
			pstmt.setInt(5, dto.getAge());
			pstmt.setInt(6, dto.getMileage());
			pstmt.setString(7, dto.getJob());
			pstmt.setString(8, dto.getAddr());
			
			result = pstmt.executeUpdate();
			
			// 사용되었던 자원을 반납을 하자.
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	} // insertMember() 메서드 end
	
	// 회원번호에 해당하는 회원의 정보를 조회하는 메서드.
	
	public MemberDTO contentMember(int no) {
		
		MemberDTO dto = new MemberDTO();
		

		try {
			openConn();
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
	
	} // contentMember() 메서드 end
	
	// MEMBER10 업데이트 최종수정 메서드
	
	public int MemberUpdate(MemberDTO dto) {
		
		int result = 0;

		try {
			openConn();
			
				sql = "update member10 set age = ?, mileage = ?, job = ?, addr = ? where num = ? ";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, dto.getAge());
				pstmt.setInt(2, dto.getMileage());
				pstmt.setString(3, dto.getJob());
				pstmt.setString(4, dto.getAddr());
				pstmt.setInt(5, dto.getNum());
				
				
				result = pstmt.executeUpdate();
					
				con.close(); pstmt.close();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// member10 테이블에서 회원번호에 해당하는 회원을 삭제하는 메서드.
	public int deleteMember(int no, String pwd) {
		
		int result = 0;
		

		try {
			openConn();
			sql = "select * from member10 where num = ? ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				// 현재 폼페이지에서 입력된 pwd와 데이터베이스상에 있는 pwd 값을 비교하여 같다면...?
				if(pwd.equals(rs.getString("pwd"))) {
					sql = "delete from member10 where num = ?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setInt(1, no);
					
					result = pstmt.executeUpdate();
					
					
					// 중간에 있는 회원 번호 삭제 시 회원번호 재작업 기능.
					
					sql = "update member10 set num = num - 1 where num > ?";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setInt(1, no);
					
					pstmt.executeUpdate();
					
				} else { // 비밀번호가 틀린경우
					result = -1;
				}
			}
			
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	} // deleteMember() 메서드 end
}