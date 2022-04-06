package com.products.model;

// category 테이블의 컬럼과 동일하게 멤버 변수 구성

public class CategoryDTO {
	
	private int cnum;
	private String category_coed;
	private String category_name;
	
	
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	
	
	public String getCategory_coed() {
		return category_coed;
	}
	public void setCategory_coed(String category_coed) {
		this.category_coed = category_coed;
	}
	
	
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	
	
	
}
