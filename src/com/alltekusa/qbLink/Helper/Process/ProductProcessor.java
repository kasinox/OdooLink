package com.alltekusa.qbLink.Helper.Process;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.alltekusa.qbLink.Odoo.Model.CategoryOdoo;
import com.alltekusa.qbLink.Odoo.Model.ProductOdoo;
import com.alltekusa.qbLink.Odoo.Model.SupplierInfoOdoo;
import com.alltekusa.qbLink.Odoo.Model.ProductOdoo.ProductType;

public class ProductProcessor {
	
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
	
	Document document;
	
	ProductOdoo product;
	
	public ProductProcessor(Document product){
		document = product;
		constructProduct(document);
	}

	public void constructProduct(Document document) {
		// TODO Auto-generated method stub
//		System.out.println(test.item(0));
		product = new ProductOdoo();
//		System.out.println(line);
//		System.out.println(doc);

			String pt_id = getItem("searchValue");
//			System.out.println(pt_id);

			if(pt_id.equals("null")){
				pt_id="106609";
			}
			
			String name_template =getItem("name_template");
//			System.out.println("searchVAlue"+pt_id);
			int recordNumberSupplier = getItemInt("recordNumberSupplier");
			String supp_id = "21806";
			String product_name = "TBD";
			String product_code = "TBD";
			String sequence = "1";
			String supp_name = "TBD";

			if(recordNumberSupplier==1){
				supp_id = getItem("supp_id");
				product_name = getItem("product_name");
				product_code = getItem("product_code");
				sequence = getItem("sequence");
				supp_name = getItem("supp_name");
			}
			if(name_template.length()>30){
				name_template=name_template.substring(0,30);
			}
			if(product_name==null||product_name.equals("")){
				product_name=name_template;
			}
//			System.out.println(product_name.length());
			if(product_name.length()>30){
				product_name=product_name.substring(0, 30);
			}
			

//			System.out.println(product_name.length());

//			System.exit(0);
		int rnIncome = getItemInt("recordNumberIncome");
		int rnExpense = getItemInt("recordNumberExpense");
		
		//setting default values for account;
		String accountincome_code="4111";
		String accountexpense_code ="5000";	
		if(rnIncome==1){
			accountincome_code = getItem("accountincome_code");
		}
		if(rnExpense==1){
			accountexpense_code = getItem("accountexpense_code");	
		}
			String accountasset_code = "12100";
			String description_sale = getItem("description_sale");
			String description_purchase = getItem("description_purchase");
			
			
			if(description_sale.length()>4095){
				description_sale=description_sale.substring(0,4095);
			}
			if(description_purchase.length()>4095){
				description_purchase=description_sale.substring(0,4095);
			}
			if(description_sale.contains("\"")){
				description_sale=description_sale.replace("\"", " ");
			}
			if(description_sale.contains("\"")){
				description_purchase=description_purchase.replace("\"", " ");
			}
			String write_date = getItem("write_date");
			String type = getItem("type");
			String active = getItem("active");
			String categ_id=getItem("categ_id");
			
			String list_price = getItem("list_price");
			String cost_value = "0";
			int recordNumberProductCost =getItemInt("recordNumberProductCost");
			if(recordNumberProductCost==1){
				cost_value = getItem("cost_value");
			}
			
			int recordNumberCategory =getItemInt("recordNumberCategory");
			String product_categ_id="7751";
			String product_categ_name="Web Technology";
			if(recordNumberCategory==1){
				product_categ_id=getItem("product_categ_id");
				product_categ_name=getItem("product_categ_name");
				
			}
			

			CategoryOdoo categ = new CategoryOdoo(product_categ_id,product_categ_name,type);
		
		product.setPt_id(pt_id);	
		product.setName_template(name_template);
		product.setSupplier(pt_id,supp_id,product_name,product_code,sequence,supp_name);
		product.setCost_value(getItem("cost_value"));
		product.setIncome(accountincome_code);
		product.setExpense(accountexpense_code);
		product.setAccountasset_code(accountasset_code);
		product.setCost_value(cost_value);
		product.setList_price(list_price);
		product.setDescription_purchase(description_purchase);
		product.setDescription_sale(description_sale);
		product.setCategory(categ);		
		product.setWrite_date(write_date);		
		product.setType(type);		
		product.setCateg_id(categ_id);
		
		


	}
	

	public String getItem(String text){
		NodeList nl = document.getElementsByTagName(text);
		Node node_record = nl.item(0);
		String id = node_record.getTextContent();
		return id;
	}
	public int getItemInt(String text) {
		NodeList nl = document.getElementsByTagName(text);
		Node node_record = nl.item(0);
		String id = node_record.getTextContent();
		int value = Integer.parseInt(id);
		return value;
	}
	public ProductOdoo getProduct(){
		return product;
	}
}
