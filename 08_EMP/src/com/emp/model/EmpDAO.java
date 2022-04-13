package com.emp.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class EmpDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = null;
	
	// 싱글턴 참조변수 instance;
	private static EmpDAO instance;
	
	private EmpDAO() {} // 기본생성자 
	
	public static EmpDAO getInstance() {
	if(instance == null) {
		instance = new EmpDAO();
		}
		return instance;
		}
	
	
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
	
	
	// DB를 연동하고 EMP테이블의 전체리스트를 setter메서드에 저장하기
	
	public List<EmpDTO> getEmpList(){
		
		List<EmpDTO> list = new ArrayList<EmpDTO>();
		

		try {
			openConn();
			sql = "select * from emp order by empno";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				
				EmpDTO dto = new EmpDTO();
				
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setJob(rs.getString("job"));
				dto.setMgr(rs.getInt("mgr"));
				dto.setSal(rs.getInt("sal"));
				dto.setComm(rs.getInt("comm"));
				dto.setDeptno(rs.getInt("deptno"));
				
				list.add(dto);
			}
			
			// 자원종료
			rs.close(); con.close(); pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	} //getEmpList end부분
}
