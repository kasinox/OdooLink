package com.alltekusa.qbLink.Odoo.Model;

public class CategoryOdoo {

	String product_category_id;
	String product_categ_name;
	String product_categ_type;
	
	
	public CategoryOdoo(String product_category_id, String product_categ_name,String product_categ_type) {
		super();
		this.product_category_id = product_category_id;
		this.product_categ_name = product_categ_name;
		this.product_categ_type= product_categ_type;
	}
	
	public String getProduct_category_id() {
		return product_category_id;
	}
	public String getProduct_categ_name() {
		return product_categ_name;
	}
	public void setProduct_category_id(String product_category_id) {
		this.product_category_id = product_category_id;
	}
	public void setProduct_categ_name(String product_categ_name) {
		this.product_categ_name = product_categ_name;
	}
	


	public String getProduct_categ_type() {
		return product_categ_type;
	}

	public void setProduct_categ_type(String product_categ_type) {
		this.product_categ_type = product_categ_type;
	}
	public String toString(){
		
		String text = "\n\t\tproduct_category_id:"+product_category_id+"\n"+
		"\t\tproduct_category_name:"+product_categ_name;
		
		return text;
	}
	
	
}
