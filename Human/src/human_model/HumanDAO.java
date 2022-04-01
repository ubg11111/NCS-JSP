package human_model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HumanDAO {
	
	Connection con = null; // DB와 연동하는 객체
	String sql = null; // DB와 연동하는 객체
	PreparedStatement pstmt = null; // DB에 SQL문을 전송하는 객체
	ResultSet rs = null; // SQL문을 실행한 후 결과값을 가지고 있는 객체
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "web";
	String password = "1234";
	
	public HumanDAO(){ // 기본 생성자 생성하기
		
		try {
			// 1단계 오라클 데이터베이스 연결하기
			Class.forName(driver);
			
			// 2단계 오라클데이터베이스에 접속하기
			con = DriverManager.getConnection(url, user, password);
			
			if(con != null) {
				System.out.println("데이터베이스 접속 성공");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} // 기본생성자 엔드문
	
	public List<HumanDTO> getHumanList(){
		
		// human 데이터베이스에서 회원 목록을 전체조회하는 메서드
		List<HumanDTO> list = new ArrayList<HumanDTO>(); // 다형성
		
	
		try {
			// 1단계 sql 테이블에 전체조회하기
			sql = "select * from human order by id desc";
			
			// 2단계 sql 작성문을 데이터베이스에 전달하기
			pstmt = con.prepareStatement(sql);
			
			// 3. 데이터베이스에 SQL문을 전송 및 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				HumanDTO dto = new HumanDTO();
				
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				dto.setJob(rs.getString("job"));
				dto.setAge(rs.getInt("age"));
				dto.setAddr(rs.getNString("addr"));
				dto.setPhone(rs.getNString("phone"));
				
				
				list.add(dto);
			}
			
			// 데이터베이스 관련 자원을 종료해준다
			con.close(); pstmt.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	} // getHumanList의 엔드문
}