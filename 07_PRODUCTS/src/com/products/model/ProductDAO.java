package com.products.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// DAO 객체를 싱글턴 방식으로 사용을 해 보자.


public class ProductDAO {
	
	Connection con = null; // DB연결 객체
	PreparedStatement pstmt = null ; // DB에서 SQL문을 전송하는 객체
	ResultSet rs = null; // SQL문 실행 후 결과 값을 가지고 있는 객체
	
	
	String sql = null;  // SQL문을 저장할 객체
	
	// ProductDAO 객체를 싱글턴 방식으로 만들어 보자.
	
	// 1단계 : 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로
	//			기본 생성자의 접근 제어자를 public에서 private으로 바꿔줘야한다.
	
	// 2단계 : ProductDAO 객체를 정적 멤버로 선언해 주어야 한다. - static으로 선언해야 한다.
	private static ProductDAO instance; // instance는 참조변수
	
	private ProductDAO() {} // 기본 생성자
	
	
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance()라는 메서드를
	// 만들어서 이 getInstance() 메서드에 외부에서 접근할 수 있게 해야 함.
	
	public static ProductDAO getInstance() {
		
		if(instance == null) {
			instance = new ProductDAO();
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
	} // openConn() 메서드 end 부분
	
	// products 테이블에서 전체 제품 리스트를 조회하는 메서드
	public List<ProductDTO> getProductList() {
		
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		
		
		

		try {
			// 오라클 데이터베이스 로딩 및 DB 연동 작업 진행하는 메서드 호출
			openConn();
			
			sql = "select * from products order by pnum desc";
			
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ProductDTO dto = new ProductDTO();
				
				dto.setPnum(rs.getInt("pnum"));
				dto.setCategory_fk(rs.getString("category_fk"));
				dto.setCategory_name(rs.getString("category_name"));
				dto.setEp_code_fk(rs.getString("ep_code_fk"));
				dto.setInput_pricte(rs.getInt("input_price"));
				dto.setOutput_price(rs.getInt("output_price"));
				dto.setTrans_cost(rs.getInt("trans_cost"));
				dto.setMailge(rs.getInt("mailge"));
				dto.setCompany(rs.getString("company"));
				
				list.add(dto);
			}
			
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} // getProductList() 메서드 end
	
	
	// category 테이블에서 카테고리 전체 리스트를 조회하는 메서드
	public List<CategoryDTO> getCategoryList() {
		
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		
		try {
			openConn();
			sql = "select * from category order by category_code";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				
				CategoryDTO dto = new CategoryDTO();
				
				dto.setCnum(rs.getInt("cnum"));
				dto.setCategory_coed(rs.getString("category_code"));
				dto.setCategory_name(rs.getString("category_name"));
			
				list.add(dto);
			
			}
			
			con.close(); rs.close(); pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
	} // getCategoryList() 메서드 end
	
	// products 테이블에 제품을 등록하는 메서드.
	
	public int insertProduct(ProductDTO dto) {
		
		int result = 0, count = 0;
		
	
		try {
			openConn();
			sql = "select max(pnum) from products";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "insert into products values(?,?,?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getCategory_fk());
			pstmt.setString(3, dto.getCategory_name());
			pstmt.setString(4, dto.getEp_code_fk());
			pstmt.setInt(5, dto.getInput_pricte());
			pstmt.setInt(6, dto.getOutput_price());
			pstmt.setInt(7, dto.getTrans_cost());
			pstmt.setInt(8, dto.getMailge());
			pstmt.setString(9, dto.getCompany());
			
			result = pstmt.executeUpdate();
			
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	} // insertProduct() 메서드 end
	
	
	public ProductDTO getContent(int no) {
		
		ProductDTO dto = new ProductDTO();
		
		try {
			openConn();
			sql = "select * from products where pnum = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				dto.setPnum(rs.getInt("pnum"));
				dto.setCategory_fk(rs.getString("category_fk"));
				dto.setCategory_name(rs.getString("category_name"));
				dto.setEp_code_fk(rs.getString("ep_code_fk"));
				dto.setInput_pricte(rs.getInt("input_price"));
				dto.setOutput_price(rs.getInt("output_price"));
				dto.setTrans_cost(rs.getInt("trans_cost"));
				dto.setMailge(rs.getInt("mailge"));
				dto.setCompany(rs.getString("company"));
				
			}
			
			con.close(); rs.close(); pstmt.close(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	// products 테이블에서 제품에 대한 정보를 수정하는 메서드.
	
	public int updateProduct(ProductDTO dto) {
		
		int result = 0;
		

		
		try {
			openConn();
			sql = "update products set input_price = ?,"
					+ "output_price = ?, trans_cost = ?, mailge = ? where pnum = ? ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getInput_pricte());
			pstmt.setInt(2, dto.getOutput_price());
			pstmt.setInt(3, dto.getTrans_cost());
			pstmt.setInt(4, dto.getMailge());
			pstmt.setInt(5, dto.getPnum());
			
			result = pstmt.executeUpdate();
			
			con.close(); pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	} // updateProduct 메서드 end부분
	
	
	// products 테이블에서 해당하는 번호를 삭제하는 메서드
	
	public int deleteProduct(int no) {
		
		int result = 0;

		try {
			openConn();
			sql = "delete from products where pnum = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
			sql = "update products set pnum = pnum - 1"
					+ " where pnum > ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
