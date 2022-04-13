package com.board1.model;

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
	
	// board 테이블의 전체 게시물의 수를 확인하는 메서드.
	
	public int getBoardCount() {
		
		int count = 0;

		try {
			openConn();
			sql = "select count(*) from board";
			pstmt = con.prepareStatement(sql);
		
			rs = pstmt.executeQuery();
		
			if(rs.next()) {
				
				count = rs.getInt(1);
				
			}
			
			rs.close(); con.close(); pstmt.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	} // getBoardCount() 메서드 end
	
	
	// board 테이블에서 현재 페이지에 해당하는 게시물을 조회하는 메서드.
	
	public List<BoardDTO> getBoardList(int page, int rowsize){
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		// 해당 페이지에서 시작 번호 
		int startNo = (page * rowsize) - (rowsize - 1);
		
		// 해당 페이지에서 끝 번호
		int endNo = (page * rowsize);
		
		

				
		
		try {
			openConn();
			sql = "select * from"
					+ "(select row_number() over(order by board_no desc) rnum,"
					+ "b.* from board b) where rnum >= ? and rnum <= ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
			
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
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}// getBoardList end부분
	
	
	// 글작성시 데이터베이스와 연동하는 메서드
	
	public int getBoardWriter(BoardDTO dto) {
		
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
			
			
			con.close(); pstmt.close(); rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	// board 테이블의 게시물 번호에 해당하는 게시글의 조회수를 증가시키는 메서드.
	
	public void boardHit(int no) {
		
	
		
		
		try {
			openConn();
			sql = "update board set board_hit = board_hit + 1"
					+ "where board_no = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}// boardHit() 메서드 end
	
	
	// board 테이블에서 게시글 번호에 해당하는 게시글을 조회하는 메서드.
	
	public BoardDTO getBoardCont(int no) {
		
		
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
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
				
			}
			
			rs.close(); con.close(); pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	} // getBoardCont() 메서드 end
	
	
	// Board 게시판을 수정하여 목록에 띄어주는 메서드.
	
	public int getBoardUpdate(BoardDTO dto) {
		
		int result = 0;
		
	
		try {
			openConn();
	
			sql = "update board set board_title =?,"
					+ "board_cont = ?, board_update = sysdate where board_no = ?";
			
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setString(1, dto.getBoard_title());
			pstmt.setString(2, dto.getBoard_cont());
			pstmt.setInt(3, dto.getBoard_no());
			
			result = pstmt.executeUpdate();
			
			con.close(); pstmt.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
