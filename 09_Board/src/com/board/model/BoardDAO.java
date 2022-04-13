package com.board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




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
	
	
	
	// DB를 연동하는 작업을 진행하는 메서드
	public void openConn() {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "web";
		String password = "1234";
		
		
		try {
			// 1단계 오라클 드라이버를 로딩
			Class.forName(driver);
			
			// 2단계 오라클 데이터베이스 연결 진행
			con = DriverManager.getConnection(url, user, password);
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	} //openConn 메서드의 end부분
	
	// 
	public List<BoardDTO> getBoardList(){
		
		List<BoardDTO> list = new ArrayList<BoardDTO>(); // 다형성 조상이 자식객체를 참조한다.
		

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
			
			con.close(); pstmt.close(); rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} // getBoardList() 메서드 end
	
	// board 테이블에 게시글을 추가해 주는 메서드.
	public int insertBoard(BoardDTO dto) {
		
		int result = 0, count = 0;
		

		
		try {
			openConn();
			sql = "select max(board_no) from board";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			
			}
			
			sql = "insert into board values(?,?,?,?,?, default, sysdate, '')";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getBoard_writer());
			pstmt.setString(3, dto.getBoard_title());
			pstmt.setString(4, dto.getBoard_cont());
			pstmt.setString(5, dto.getBoard_pwd());
			
			result = pstmt.executeUpdate();
			
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}// insertBoard() 메서드 end
	
	// board 테이블의 글번호에 해당하는 게시글의 조회수를 증가시키는 메서드
	public void boardHit(int no) {
		

		
		try {
			openConn();
			sql = "update board set board_hit = board_hit + 1"
					+ "where board_no = ? ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	} // boardHit() 메서드 end
	
	
	// content 출력해줄 화면을 데이터베이스에 불러오기
	
	public BoardDTO getBoardCotent(int no) {
		
		BoardDTO dto = new BoardDTO();

		try {
			openConn();
			sql = "select * from board where board_no = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_wirter"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getNString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
				
			}
			
			rs.close(); con.close(); pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	
	// board 테이블의 글번호에 해당하는 게시글을 수정하는 메서드.
	public int updateBoard(BoardDTO dto) {
		
		int result = 0;
		
	
		try {
			openConn();
			sql = "select * from board where board_no = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getBoard_no());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getBoard_pwd().equals(rs.getString("board_pwd"))){
					sql = "update board set board_title = ?,"
							+ "board_cont = ?, board_update = sysdate where board_no = ?";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, dto.getBoard_title());
					pstmt.setString(2, dto.getBoard_cont());
					pstmt.setInt(3, dto.getBoard_no());
					
					result = pstmt.executeUpdate();
					
				}else {
					result = -1;
				}
			}
			con.close(); pstmt.close(); rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	} // updateBoard() end부분
	
	
	// board 테이블에서 글번호에 해당하는 게시글을 삭제하는 메서드.
	public int deleteBoard(int no, String pwd) {
		
		int result = 0;
		

		try {
			openConn();
			sql = "select * from board where board_no = ? ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(pwd.equals(rs.getString("board_pwd"))) {
					
					sql = "delete from board where board_no = ?";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setInt(1, no);
					
					result = pstmt.executeUpdate();
				}
			} else {
				result = -1;
			}
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	} // deleteBoard() 메서드 end
	
	
	// board 테이블에서 중간의 게시글 삭제 시 글번호 재정렬 하는 메서드.
	public void updateSequence(int no ) {
		
	
		try {
			openConn();
			sql = "update board set board_no = board_no -1"
					+ "where board_no > ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} // updateSequence() 메서드 end
	
	
	public List<BoardDTO> searchBoard(String field, String name){
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		openConn();
		
		if(field.equals("title")) { // 제목으로 선택하여 검색한 경우
			

			try {
				sql = "select * from board "
						+ "where board_title like ?"
						+ "order by board_no desc";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, "%"+name+"%");
				
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
				
				rs.close(); pstmt.close(); con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if(field.equals("content")) { // 내용으로 검색한 경우
			try {
				sql = "select * from board "
						+ "where board_cont like ?"
						+ "order by board_no desc";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, "%"+name+"%");
				
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
				
				rs.close(); pstmt.close(); con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				sql = "select * from board "
						+ "where board_title like ?"
						+ "or board_cont like ?"
						+ "order by board_no desc";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, "%"+name+"%");
				pstmt.setString(2, "%"+name+"%");
				
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
				
				rs.close(); pstmt.close(); con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	} 
	

	
}
