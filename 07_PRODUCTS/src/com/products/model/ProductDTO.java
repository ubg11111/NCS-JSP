package com.products.model;

public class ProductDTO {
	private int pnum;
	private String category_fk;
	private String category_name;
	private String ep_code_fk;
	private int input_pricte;
	private int output_price;
	private int trans_cost;
	private int mailge;
	private String company;
	
	
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	
	public String getCategory_fk() {
		return category_fk;
	}
	public void setCategory_fk(String category_fk) {
		this.category_fk = category_fk;
	}
	
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	
	public String getEp_code_fk() {
		return ep_code_fk;
	}
	public void setEp_code_fk(String ep_code_fk) {
		this.ep_code_fk = ep_code_fk;
	}
	
	public int getInput_pricte() {
		return input_pricte;
	}
	public void setInput_pricte(int input_pricte) {
		this.input_pricte = input_pricte;
	}
	
	public int getOutput_price() {
		return output_price;
	}
	public void setOutput_price(int output_price) {
		this.output_price = output_price;
	}
	
	public int getTrans_cost() {
		return trans_cost;
	}
	public void setTrans_cost(int trans_cost) {
		this.trans_cost = trans_cost;
	}
	
	public int getMailge() {
		return mailge;
	}
	public void setMailge(int mailge) {
		this.mailge = mailge;
	}
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
}
