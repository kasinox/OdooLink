package com.aionsoft.qblink.model.quickbooks;

import com.alltekusa.qbLink.Model.Base.Table;

public class ProductCategory extends Product{
	private String category_id;
	private String category_name;
	private String odoo_id;
	private String type;

	public ProductCategory(){
		super();
		init();
	
	}
	
	//odoo id used to identify the id
	
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	
	public String getCategory_id() {
		return category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	
	
	public void init(){
		
		setTableName("productCategory");

		addColumn("ListID","String",false,false);//no insert
		addColumn("Name","String","name_template",true,true);//name_template
		
		addColumn("SalesDesc","String","description_sale",true,true);//description_sale
		addColumn("SalesPrice","Double","list_price",true,true);//list_price	
		addColumn("PurchaseDesc","String","description_purchase",true,true);//description_purchase		
		addColumn("PurchaseCost","Double","cost_value",true,true);//value_float
		addColumn("ManufacturerPartNumber","String","product_name",true,true);//product_name
		addColumn("customFieldcateg_id","String","pp_id",true,true);//pp_id
		addColumn("ParentRefFullName","String","product_categ_name",true,true);
		addColumn("ParentRefListID","String","product_categ_id",true,true);//need only one
		addColumn("IncomeAccountRefListID","String","accountincome_code",true,true);//default
		addColumn("COGSAccountRefListID","String","accountexpense_code",true,true);//required
		addColumn("AssetAccountRefListID","String","accountasset_code",true,true);//required
		addColumn("IsActive", "Boolean","active",true,true);//required
		
		//get from quickbooks // not required
//		addColumn("PrefVendorRefListID","String",true,true);		
//		addColumn("PrefVendorRefFullName","String","product_categ_name",true,true);	
//		addColumn("Type","String",true,true);
//		addColumn("SalesTaxCodeRefListID","String",true,true);	
//		addColumn("TimeModified","String");		
//		addColumn("FullName","String");
//		addColumn("Description","String");				
//		addColumn("Sublevel",FieldType.Integer);		
//		addColumn("SalesTaxCodeRefFullName","String");			
//		addColumn("IncomeAccountRefFullName","String");		
//		addColumn("COGSAccountRefFullName","String");		
//		addColumn("ProductConfig","String");
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}




	
	
	
}
