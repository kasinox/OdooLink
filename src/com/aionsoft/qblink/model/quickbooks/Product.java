package com.aionsoft.qblink.model.quickbooks;

import java.util.ArrayList;
import java.util.logging.Level;

import com.aionsoft.qblink.model.base.Column;
import com.aionsoft.qblink.model.base.Row;
import com.aionsoft.qblink.model.base.Table;
import com.aionsoft.qblink.model.odoo.ProductOdoo.ProductType;

public class Product extends Table {
	// Product information required to build quickbooks item
	// FieldList product;
	// Table insertTable = new Table();
	// String name = "item";

	String productName;
	ProductType productType;
	String productParentRefListID;
	

	
	public Product() {
		super();
		init();

	}

	public Product(String category_id) {
		super();
		init();
	}

	public void init() {
		setTableName("product");	
		addColumn("ListID", "String", false, false);// no insert
		addColumn("Name", "String", "name_template", true, true);// name_template
		addColumn("SalesDesc", "String", "description_sale", true, true);// description_sale
		addColumn("SalesPrice", "Double", "list_price", true, true);// list_price
		addColumn("PurchaseDesc", "String", "description_purchase", true, true);// description_purchase
		addColumn("ManufacturerPartNumber", "String", "product_name", true, true);// product_name
		// get from quickbooks // not required
		addColumn("PrefVendorRefListID","String",true,true);//need to add
//		addColumn("PrefVendorRefFullName","String","product_categ_name",true,true);				
//		addColumn("ParentRefFullName","String","product_categ_name",true,true);
//		addColumn("ParentRefListID","String","product_categ_id",true,true);		
		addColumn("IncomeAccountRefListID", "String", "accountincome_code", true, true);// default
		addColumn("COGSAccountRefListID", "String", "accountexpense_code", true, true);
//		addColumn("IncomeAccountRefFullName", "String", "accountincome_code", true, true);// default
//		addColumn("COGSAccountRefFullName", "String", "accountexpense_code", true, true);
		addColumn("PurchaseCost", "Double", "cost_value", true, true);// value_float
		addColumn("AssetAccountRefListID", "String", "accountasset_code", true, true);
		addColumn("CustomFieldodoo_id", "String", "pp_id", true, true);// pp_id
		addColumn("IsActive", "Boolean", "active", true, true);// checkagain?		
//		addColumn("Type","String","type",true,true);
		// addColumn("SalesTaxCodeRefListID","String",true,true);

		// addColumn("TimeModified","String");
		// addColumn("FullName","String");
		// addColumn("Description","String");
		// addColumn("Sublevel",FieldType.Integer);
		// addColumn("SalesTaxCodeRefFullName","String");
		// addColumn("IncomeAccountRefFullName","String");
		// addColumn("COGSAccountRefFullName","String");

		// addColumn("ProductConfig","String");

	}
	public String printProductSetting() {
		String text = "PRODUCT SETTINGS\n";
		for (int i = 0; i < getColumnSize() ; i++) {
			String name = getColumn(i).getColumnName();
			boolean insert = getColumn(i).isInsert();
			text = text +  "INSERT:\t" + insert +"\tNAME:\t" + name +"\n";
		}
		
		return text;
	}
	
//	public String printProductXML(){
//		
//	}
	public String printInsertFieldList() {
		String text = "INSERT FIELD LIST\n";
		int i;
		for (i = 0; i < getColumnSize() - 1; i++) {
			if (getColumn(i).isInsert()) {
				text = text + getColumn(i).getColumnName() + "\n";
			}

		}
		text = text + getColumn(i).getColumnName() + " ";
		return text;
	}


	public String getProductOdooID(){
		String id = getFieldValue("CustomFieldodoo_id");
		return id;
	}
	public String getProductName(){
		String name = getFieldValue("Name");
		return name;
	}
	public String getFieldValue(String fieldName) {
		int j = 0;
		if (getRows().get(0).getCells().size() > 0) {

			Row row = getRows().get(0);

			for (j = 0; j < row.getCells().size() - 1; j++) {
				// get the name type and value of the columsn
				String name = row.getCells().get(j).getColumnName();
				String type = row.getCells().get(j).getColumn().getDataType();
				String value = row.getCells().get(j).getValue();

				// if name equals any of the accounts, need to look up their
				// cooresondign id
				if (name.equals(fieldName))
					return value;
			}

		} else {
			return null;
		}
		return null;

	}

	public Column getFieldByName(String name) {
		//return Column of the fieldName
		for (int i = 0; i < getColumnSize(); i++) {
			Column temp = getColumn(i);
			if (temp.getColumnName().equals(name)) {
				return temp;
			}
		}
		return null;
	}

	

	public String printProductValue() {
		String text = "";
//		 for (int i=0;i<getRowSize()-1;i++){
//		 String name = getRows().get(0).getCells().get(i).getColumnName();
//		 String value = getRows().get(0).getCells().get(i).getValue();
//		 text = text +"name:"+name+"\tvalue:"+value+"\n";
//		 }
		text = getRows().toString();
		return text;
	}

	public static void main(String[] args) {
		Product pro = new Product();
		
//
//		System.out.println("col size\t" + pro.getColumnSize());

		

		
//		System.out.println("Product Setting\n" + pro.printProductSetting());
		System.out.println("Insert Field List\n" + pro.printInsertFieldList());
	}

	public ProductType getProductType() {
		return productType;
	}

	public String getParentRefListID() {
		return productParentRefListID;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductType(ProductType productType2) {
		this.productType = productType2;
	}

	public void setParentRefListID(String parentRefListID) {
		productParentRefListID = parentRefListID;
	}




}
