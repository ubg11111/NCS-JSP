package com.board.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class BoardDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = null;
	
	private static BoardDAO instance;
	
	private BoardDAO() {}; // BoardDAO 기본생성자를 생성
	
	public static BoardDAO getInstance() { // BoardDAO의 getInstance 메서드를 생성 (싱글턴)
		
		if(instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	} // getInstance의 end부분
	
	
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
	
	
	// 전체리스트를 가져오는 DB작성하기
	
	public List<BoardDTO> getBoardList(){
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		
	
		try {
			openConn();
			sql = "select * from board order by board_no";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_wirter(rs.getString("board_wirter"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
				
				list.add(dto);
			}
			con.close(); rs.close(); pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	} // getBoardList() end부분
	
	// 글 쓰기 버튼을 클릭하는 폼을 데이터베이스와 연동해주기.
	
	public int BoardWriteOk(BoardDTO dto) {
		
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
			pstmt.setString(2, dto.getBoard_wirter());
			pstmt.setString(3, dto.getBoard_title());
			pstmt.setString(4, dto.getBoard_cont());
			pstmt.setString(5, dto.getBoard_pwd());
			
			result = pstmt.executeUpdate();
			
			rs.close(); con.close(); pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	} // BoardWriteOk 메서드 end
	
	// content 출력해줄 화면을 데이터베이스에 불러오기
	
	public BoardDTO getBoardContent(int no) {
		
		BoardDTO dto = new BoardDTO();
		

		try {
			openConn();
			sql = "select * from board where board_no = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_wirter(rs.getString("board_wirter"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getNString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
			
			
			}
			rs.close(); con.close(); pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	
	
	// Board 테이블에 글수정 버튼을 클릭시 업데이트되는 메서드 만들기
	
	public int boardUpdate(BoardDTO dto) {
		
		int result = 0;
		

		try {
			openConn();
			sql = "select * from board where board_no = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getBoard_no());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getBoard_pwd().equals(rs.getString("board_pwd"))) {
					
					sql = "update board set board_title = ?,"
							+ "board_cont = ?, board_update = sysdate where board_no = ?";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, dto.getBoard_title());
					pstmt.setString(2, dto.getBoard_cont());
					pstmt.setInt(3, dto.getBoard_no());
					
					
					result = pstmt.executeUpdate();
				} else {
					result = -1;
				}
			} 
			con.close(); rs.close(); pstmt.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	// 글 삭제를 위한 메서드를 데이터베이스에 연동하기.
	
	public int boardDelete(int no, String pwd) {
		
		int result = 0;
		

		try {
			openConn();
			sql = "select * from board where board_no = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
		
			if(rs.next()) {
				if(pwd.equals(rs.getString("board_pwd"))) {
					
					sql= "delete from board where board_no = ?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setInt(1, no);
					
					result = pstmt.executeUpdate();
					
				}else {
					result = -1;
				}
			}
			rs.close(); pstmt.close(); con.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	} // boardDelete () 메서드 end부분
	
	
	// boardDelete 이후에 글순서를 정렬해주는 메서드
	
	public void updateSequence(int no) {
		
		try {
			openConn();
			sql = "update board set board_no = board_no -1 "
					+ "where board_no > ?";
			pstmt = con.prepareStatement(sql);
		
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // updateSequence () 메서드 end부분
}
