package com.aionsoft.qblink.model.odoo;

public class ProductOdoo {
	
	String name_template;
	String pt_id;
	SupplierInfoOdoo supplier;
	String cost_value;
	String income;
	String expense;
	
	
	String accountasset_code;
	
	String description_sale;
	String description_purchase;
	
	String list_price;
	
	String write_date;
	ProductType type;
	
	String categ_id;
	CategoryOdoo category;
	String weight_net;
	
	public ProductOdoo(String pt_id,String name_template,SupplierInfoOdoo supplier, String cost_value, String income, String expense,
			String accountasset_code, String description_sale, String description_purchase, String list_price,
			String write_date, ProductType type, String categ_id, CategoryOdoo category,String weight_net) {
		super();
		this.pt_id=pt_id;
		this.name_template=name_template;
		this.supplier = supplier;
		this.cost_value = cost_value;
		this.income = income;
		this.expense = expense;
		this.accountasset_code = accountasset_code;
		this.description_sale = description_sale;
		this.description_purchase = description_purchase;
		this.list_price = list_price;
		this.write_date = write_date;
		this.type = type;
		this.categ_id = categ_id;
		this.category = category;
		this.weight_net=weight_net;
	}

	
	public ProductOdoo(){
		
	}
	
	
	
	


	public enum ProductType{
		stock,
		consummable,
		service,
	}
		
	public SupplierInfoOdoo getSupplier() {
		return supplier;
	}
	public String getCost_value() {
		return cost_value;
	}

	public String getAccountasset_code() {
		return accountasset_code;
	}
	public String getDescription_sale() {
		return description_sale;
	}
	public String getDescription_purchase() {
		return description_purchase;
	}
	public String getList_price() {
		return list_price;
	}
	public String getWrite_date() {
		return write_date;
	}
	public void setSupplier(SupplierInfoOdoo supplier) {
		this.supplier = supplier;
	}
	public void setSupplier(String pt_id, String supp_id, String product_name, String product_code, String sequence,
			String supp_name) {
		this.supplier = new SupplierInfoOdoo(pt_id,supp_id,product_name,product_code,sequence,supp_name);
	}
	public void setCost_value(String cost_value2) {
		this.cost_value = cost_value2;
	}

	public void setAccountasset_code(String accountasset_code) {
		this.accountasset_code = accountasset_code;
	}
	public void setDescription_sale(String description_sale) {
		this.description_sale = description_sale;
	}
	public void setDescription_purchase(String description_purchase) {
		this.description_purchase = description_purchase;
	}
	public void setList_price(String list_price2) {
		this.list_price = list_price2;
	}
	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}
	public ProductType getType() {
		return type;
	}
	public void setType(ProductType type) {
		this.type = type;
	}


	public String getCateg_id() {
		return categ_id;
	}


	public CategoryOdoo getCategory() {
		return category;
	}


	public void setCateg_id(String categ_id2) {
		this.categ_id = categ_id2;
	}


	public void setCategory(CategoryOdoo category) {
		this.category = category;
	}


	public String getIncome() {
		return income;
	}


	public String getExpense() {
		return expense;
	}


	public void setIncome(String income) {
		this.income = income;
	}


	public void setExpense(String expense) {
		this.expense = expense;
	}


	public void setType(String type2) {
		
		if(type2.equals("product")){
			type=ProductType.stock;
		}
		else if(type2.equals("service")){
			type=ProductType.service;
		}
		else if(type2.equals("consummable")){
			type=ProductType.consummable;
		}
			
		// TODO Auto-generated method stub
		
	}


	public String getName_template() {
		return name_template;
	}


	public void setName_template(String name_template) {
		this.name_template = name_template;
	}


	public String getPt_id() {
		return pt_id;
	}


	public void setPt_id(String pt_id) {
		this.pt_id = pt_id;
	}
	public String information(){
		String text = 				
		"====PRODUCT INFORMATION====\n"+
		"\tname:"+name_template+"\n"+
		"\tsupplier:"+supplier+"\n"+
		"\tcost_value:"+cost_value+"\n"+
		"\tincome:"+income+"\n"+
		"\texpense:"+expense+"\n"+
		"\taccountasset_code:"+accountasset_code+"\n"+
		"\tdescription_sale:"+description_sale+"\n"+
		"\tdescription_purchase:"+description_purchase+"\n"+
		"\tlist_price:"+list_price+"\n"+
		
		"\twrite_date:"+write_date+"\n"+
		"\ttype:"+type+"\n"+
		
		"\tcateg_id:"+categ_id+"\n"+
		"\tcategory:"+category+"\n"
		+"\n\n";
		
		return text;
	}
	public String toString (){
		String text = "---------------------------------------------\n"+""
				+ "\tNAME:"+getName_template()+"\n"
				+"\tPROD:"+getSupplier().getProduct_name()+"\n"
				+"\tSUPP:"+getSupplier().getSupp_name()+"\n"
				+"\tTYPE:"+getType()+"\n";
		return text;
		
	}


	public String getWeight_net() {
		return weight_net;
	}


	public void setWeight_net(String weight_net) {
		this.weight_net = weight_net;
	}

}
