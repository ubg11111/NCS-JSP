package com.khie.model;

// DB 상의 DEPT 테이블의 컬럼과 동일하게 멤버 구성


public class DeptDTO {
	
	private int deptno;
	private String dname;
	private String loc;
	
	
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
}
