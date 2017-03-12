package com.aionsoft.qblink.processor.odoo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.aionsoft.qblink.helper.GetURI;
import com.aionsoft.qblink.helper.Processor;
import com.aionsoft.qblink.helper.Sorter;
import com.aionsoft.qblink.model.odoo.CategoryOdoo;
import com.aionsoft.qblink.model.odoo.CustomerOdoo;
import com.aionsoft.qblink.model.odoo.ProductOdoo;
import com.aionsoft.qblink.model.odoo.SaleOrderLineOdoo;
import com.aionsoft.qblink.model.odoo.SaleOrderOdoo;
import com.aionsoft.qblink.model.odoo.VendorOdoo;

public class SaleOrderProcessor {

	String so_number;
	String origin;
	String amount_tax;
	String payment_term;
	String state;
	String pricelist_id;
	String write_date;
	String user_id;
	
	String incoterm;
	
	Document document;
	
	SaleOrderOdoo so= new SaleOrderOdoo();
	ArrayList<VendorOdoo> vendors = new ArrayList<>();	
	ArrayList<ProductOdoo> products = new ArrayList<>();
	ArrayList<CategoryOdoo> categories = new ArrayList<>();
	
	ArrayList<SaleOrderLineOdoo> soLine = new ArrayList<>();
	VendorOdoo vendor = new VendorOdoo();
	
	public SaleOrderProcessor(Document sale_order){
		document = sale_order;
		constructSaleOrderOdoo(document);
		
	}
	public SaleOrderProcessor(){
		
	}
	

	public void constructSaleOrderOdoo(Document sale_order) {
		
		so.setSo_number(getItem("so_number"));
		so.setOrigin(getItem("origin"));	
		so.setAmount_tax(getItem("amount_tax"));
		so.setPayment_term(getItem("payment_term"));
//		System.out.println(so.getPayment_term());
//		System.exit(0);
		so.setState(getItem("state"));
		so.setPricelist_id(getItem("pricelist_id"));
		so.setWrite_date(getItem("write_date"));
		so.setUser_id(getItem("user_id"));
		so.setIncoterm(getItem("incoterm"));
		so.setRecordNumberSaleOrder(getItemInt("recordNumberSaleOrder"));
		so.setRecordNumberSaleOrderLine(getItemInt("recordNumberSaleOrderLine"));
		so.setX_contract(getItem("x_contract"));
		so.setX_contract_signed(getItem("x_contract_signed"));
		so.setX_type(getItem("x_type"));
//		System.out.println(so.getX_contract());
//		System.out.println(so.getX_contract_signed());
//		System.out.println(so.getX_type());
//		System.exit(0);
		CustomerProcessor cp = new CustomerProcessor(sale_order);
		CustomerOdoo customer=cp.getCustomer();
		
//		System.out.println(so.getRecordNumberSaleOrderLine());s
		int lines = so.getRecordNumberSaleOrderLine();
		
		for(int i=lines-1;i>-1;i--){
			
			SaleOrderLineOdoo temp = new SaleOrderLineOdoo();
			
//			System.out.println("so_id_"+i);
			temp.setSo_id(getItem("so_id_"+i));
			temp.setQty(getItem("qty_"+i));
			temp.setSequence(getItem("sequence_"+i));
			temp.setOrder_id(getItem("order_id_"+i));
			temp.setPrice(getItem("price_"+i));
			temp.setDiscount(getItem("discount_"+i));
			temp.setSalesman_id(getItem("salesman_id_"+i));
			temp.setProduct_id(getItem("product_id_"+i));
			
			String description_sale = getItem("so_desc_"+i);
			
			if(description_sale.length()>4095){
				description_sale=description_sale.substring(0,4095);
			}
			if(description_sale.contains("\"")){
				description_sale=description_sale.replace("\"", " ");
			}
			temp.setSo_desc(description_sale);
			temp.setState(getItem("state_"+i));
			temp.setOrder_partner_id(getItem("order_partner_id_"+i));
			temp.setTemplateRefListID(getItem("TemplateRefListID_"+i));
			temp.setSaleOrderLineSalesTaxCodeRefListID(getItem("SalesOrderLineSalesTaxCodeRefListID_"+i));
			soLine.add(temp);
		}
		
		double tax = Double.parseDouble(so.getAmount_tax());
		if(tax>0){
			int i=lines;
			int j=lines-1;
			SaleOrderLineOdoo temp = new SaleOrderLineOdoo();
			
//			System.out.println("so_id_"+i);
			temp.setSo_id(getItem("so_id_"+j));
			temp.setQty("1");
			temp.setSequence(""+lines);
			temp.setOrder_id(getItem("order_id_"+j));
			temp.setPrice(""+tax);
//			temp.setDiscount(getItem("discount_"+i));
//			temp.setSalesman_id(getItem("salesman_id_"+i));
			temp.setProduct_id("134787");
			temp.setSo_desc("17% VAT");
//			temp.setState(getItem("state_"+i));
			temp.setOrder_partner_id(getItem("order_partner_id_"+j));
			temp.setTemplateRefListID(getItem("TemplateRefListID_"+j));
			temp.setSaleOrderLineSalesTaxCodeRefListID(getItem("SalesOrderLineSalesTaxCodeRefListID_"+j));
			soLine.add(temp);
//			System.out.println(temp.getProduct_id()+","+temp.getPrice());
//			System.exit(0);
		}
		
		so.setCustomer(customer);
////		System.out.println("before");
//		for(int i =0;i<soLine.size();i++){
////			System.out.println(soLine.get(i).getSequence());
//		}
		
		
		soLine = new Sorter().sort(soLine);
//		System.out.println("after");
//		for(int i =0;i<soLine.size();i++){
////			System.out.println(soLine.get(i).getSequence());
//		}
////		System.exit(0);
		so.setLines(soLine);
		
	}
	


	public SaleOrderOdoo getSaleOrderOdoo(){
		return so;
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

	public CustomerOdoo getCustomerOdoo() {
		
		// TODO Auto-generated method stub
		return so.getCustomer();
	}


	public ArrayList<ProductOdoo> extractProductsOdoo() throws SQLException {
//		
		Processor cpu = new Processor();
		for(int i=0;i<soLine.size();i++){
			SaleOrderLineOdoo line = soLine.get(i);
			String product_id = line.getProduct_id();
			if(product_id.equals("null")){
				product_id="168236";
			}
			String uri2 = new GetURI().product(product_id);
			Document doc = cpu.getDocument(uri2);
			ProductProcessor pp=new ProductProcessor(doc);
			ProductOdoo product = pp.getProduct();
			System.out.println(product);			
			products.add(product);			
		}
		return products;
	}

	public ArrayList<VendorOdoo> getVendorsOdoo() throws SQLException {
		// TODO Auto-generated method stub
		
		
		
		for(int i=0;i<products.size();i++){
			ProductOdoo product = products.get(i);
			String supp_id = product.getSupplier().getSupp_id();
			
			if(supp_id.equals("null")){
				supp_id="168236";
			}
			String uri2 = new GetURI().vendor(supp_id);
//			System.out.println(uri2);
			Processor cpu = new Processor();
			Document doc = cpu.getDocument(uri2);

			VendorProcessor vp=new VendorProcessor(doc);

			VendorOdoo vendor = vp.getVendor();
			vendors.add(vendor);
		}
		return vendors;
	}
	public  ArrayList<CategoryOdoo> getCategoryOdoo() {
		
		
		for(int i=0;i<products.size();i++){
			ProductOdoo product = products.get(i);
			String product_categ_id = product.getCategory().getProduct_category_id();
			String product_categ_name = product.getCategory().getProduct_categ_name();
			String product_categ_type = product.getCategory().getProduct_categ_type();
//			System.out.println(product_categ_id+","+product_categ_name);
//			if(supp_id.equals("null")){
//				supp_id="168236";
//			}
			CategoryOdoo category = new CategoryOdoo(product_categ_id, product_categ_name,product_categ_type);
			boolean add = true;
			for(int j=0;j<categories.size();j++){
				String currentProduct_categ_id = categories.get(j).getProduct_category_id();
				if(currentProduct_categ_id.equals(product_categ_id)) add=false;
				
			}
			if(add==true)categories.add(category);
		}
		// TODO Auto-generated method stub
		return categories;
	}
	public String getSo_number() {
		return so_number;
	}
	public String getOrigin() {
		return origin;
	}
	public String getAmount_tax() {
		return amount_tax;
	}
	public String getPayment_term() {
		return payment_term;
	}
	public String getState() {
		return state;
	}
	public String getPricelist_id() {
		return pricelist_id;
	}
	public String getWrite_date() {
		return write_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public String getIncoterm() {
		return incoterm;
	}
	public Document getDocument() {
		return document;
	}
	public SaleOrderOdoo getSo() {
		return so;
	}

	public ArrayList<ProductOdoo> getProducts() {
		return products;
	}
	public ArrayList<CategoryOdoo> getCategories() {
		return categories;
	}
	public ArrayList<SaleOrderLineOdoo> getSoLine() {
		return soLine;
	}
	public VendorOdoo getVendor() {
		return vendor;
	}
	public void setSo_number(String so_number) {
		this.so_number = so_number;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public void setAmount_tax(String amount_tax) {
		this.amount_tax = amount_tax;
	}
	public void setPayment_term(String payment_term) {
		this.payment_term = payment_term;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setPricelist_id(String pricelist_id) {
		this.pricelist_id = pricelist_id;
	}
	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public void setIncoterm(String incoterm) {
		this.incoterm = incoterm;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public void setSo(SaleOrderOdoo so) {
		this.so = so;
	}
	public void setVendors(ArrayList<VendorOdoo> vendors) {
		this.vendors = vendors;
	}
	public void setProducts(ArrayList<ProductOdoo> products) {
		this.products = products;
	}
	public void setCategories(ArrayList<CategoryOdoo> categories) {
		this.categories = categories;
	}
	public void setSoLine(ArrayList<SaleOrderLineOdoo> soLine) {
		this.soLine = soLine;
	}
	public void setVendor(VendorOdoo vendor) {
		this.vendor = vendor;
	}
	
	
	
//	SaleOrderLine
	
	
	//get customer information
	
	
//	public void document processor
	//get sale orderline information
	
	//get item information for products
	
	
//

}
