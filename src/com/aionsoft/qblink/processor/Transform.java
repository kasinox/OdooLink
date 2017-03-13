package com.aionsoft.qblink.processor;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;



import org.w3c.dom.NodeList;

import com.aionsoft.qblink.configuration.quickbooks.CurrencyCode;
import com.aionsoft.qblink.configuration.quickbooks.Incoterm;
import com.aionsoft.qblink.configuration.quickbooks.IncotermCode;
import com.aionsoft.qblink.configuration.quickbooks.PaymentTermCode;
import com.aionsoft.qblink.configuration.quickbooks.SalesRepCode;
import com.aionsoft.qblink.model.base.Cell;
import com.aionsoft.qblink.model.base.Column;
import com.aionsoft.qblink.model.base.Row;
import com.aionsoft.qblink.model.odoo.CategoryOdoo;
import com.aionsoft.qblink.model.odoo.CustomerOdoo;
import com.aionsoft.qblink.model.odoo.ProductOdoo;
import com.aionsoft.qblink.model.odoo.SaleOrderLineOdoo;
import com.aionsoft.qblink.model.odoo.SaleOrderOdoo;
import com.aionsoft.qblink.model.odoo.VendorOdoo;
import com.aionsoft.qblink.model.odoo.ProductOdoo.ProductType;
import com.aionsoft.qblink.model.quickbooks.Customer;
import com.aionsoft.qblink.model.quickbooks.Product;
import com.aionsoft.qblink.model.quickbooks.ProductCategory;
import com.aionsoft.qblink.model.quickbooks.SaleOrder;
import com.aionsoft.qblink.model.quickbooks.SaleOrderLine;
import com.aionsoft.qblink.model.quickbooks.Vendor;

public class Transform {
	
	String defaultSupplier = "21806"; //VENDOR TBD
	
	public Transform(){
		

	}

	public Customer getCustomer(CustomerOdoo customerOdoo){
		
		;
		Customer customer = new Customer();
		
		Row row = new Row();
		for (int i = 0; i < customer.getColumnSize(); i++) {

			Column col = customer.getColumn(i);
			if (col.isInsert() == true) {
				String field = col.getColumnName();
			
				Cell cel = new Cell();
				String value="";
				if(field.equals("name")){
					value = customerOdoo.getPartner_name();
				}
				else if(field.equals("accountNumber")){
					value= customerOdoo.getCustomer_id();
				}
				
				else if(field.equals("companyName")){
					value = customerOdoo.getPartner_name();
				}
				
				else if(field.equals("BillAddressAddr1")){
					value = customerOdoo.getPartner_name();
					/**
					 * TODO:
					 * Need to fix this character length issue
					 */
					if(value.length()>31){
						value=value.substring(0,31);
					}
				}
				else if(field.equals("BillAddressAddr2")){
					value = customerOdoo.getPartner_street();
					
					/**
					 * TODO:
					 * Need to fix this character length issue
					 */
					if(value.length()>31){
						value=value.substring(0,31);
					}
				}
				else if(field.equals("BillAddressAddr3")){
					value = customerOdoo.getPartner_street2();
					/**
					 * TODO:
					 * Need to fix this character length issue
					 */
					if(value.length()>31){
						value=value.substring(0,31);
					}
					
				}
				else if(field.equals("BillAddressCity")){
					value = customerOdoo.getPartner_city();
				}
				else if(field.equals("Phone")){
					value = "";
				}
				else if(field.equals("Fax")){
					value = "";
				}
				else if(field.equals("Email")){
					value = "";
				}
			
				if(value.equals("")||value==null){
					value="";
				}
				cel = new Cell(value, col);		
				row.addCell(cel);
			}

		}
		customer.addRows(row);
		System.out.println("TRANSFORM CUSTOMER:"+customerOdoo.getPartner_name()+"\tDONE");

		return customer;

	}

	public ProductCategory getCategory(CategoryOdoo categoryOdoo) {
		
			ProductCategory category = new ProductCategory();
			
			Row row = new Row();
			for (int i = 0; i < category.getColumnSize(); i++) {

				Column col = category.getColumn(i);
				if (col.isInsert() == true) {
					String field = col.getColumnName();
				
					Cell cel = new Cell();
					String value="";
					if(field.equals("Name")){
						value = categoryOdoo.getProduct_categ_name();
						category.setCategory_name(value);
					}
					
					
					if(field.equals("customFieldcateg_id")){
						value = categoryOdoo.getProduct_category_id();
						category.setCategory_id(value);
					}
					
					
					if(value.equals("")||value==null){
						value="";
					}
					cel = new Cell(value, col);		
					row.addCell(cel);
				}

			}
			category.addRows(row);
		category.setType(categoryOdoo.getProduct_categ_type());	
		System.out.println("TRANSFORM CATEGORY:"+categoryOdoo.getProduct_categ_name()+"\tDONE");

		return category;
	}

	public Product getProduct(ProductOdoo productOdoo) {
		
		Product product = new Product();
		
		Row row = new Row();
		for (int i = 0; i < product.getColumnSize(); i++) {

			Column col = product.getColumn(i);
			if (col.isInsert() == true) {
				String field = col.getColumnName();
			
				Cell cel = new Cell();
				String value="";
				if(field.equals("Name")){
					value = productOdoo.getName_template();
					value = value.replace(":", "");
				}
				if(field.equals("IsActive")){
					value = "TRUE";
				}

				else if(field.equals("SalesDesc")){
					value = productOdoo.getDescription_sale();
				}
				else if(field.equals("SalesPrice")){
					value = productOdoo.getList_price();
					
				}
				else if(field.equals("PurchaseDesc")){
					value = productOdoo.getDescription_purchase();
				}
				else if(field.equals("PurchaseCost")){
					value = productOdoo.getCost_value();
				}
				else if(field.equals("ManufacturerPartNumber")){
					value = productOdoo.getSupplier().getProduct_name();
					if(value==null||value.equals("null")){
						value=productOdoo.getName_template();
					}

				}
				else if(field.equals("IncomeAccountRefListID")){
					value = productOdoo.getIncome();
				}
				else if(field.equals("COGSAccountRefListID")){
					value = productOdoo.getExpense();
				}
				else if(field.equals("AssetAccountRefListID")){
					value = "1120";
				}
				else if(field.equals("CustomFieldodoo_id")){

					value = productOdoo.getPt_id();
					if(value.equals("null")||value==null){
						value="168236";
					}
				}
				else if(field.equals("PrefVendorRefListID")){
					
					value = productOdoo.getSupplier().getSupp_id();
					if(value==null||value.equals("null")){
						value=defaultSupplier;
					}
					
				}
				else if(field.equals("ParentRefListID")){
					value = productOdoo.getCategory().getProduct_category_id();
				}
				else if(field.equals("PrefVendorRefFullName")){
					value = productOdoo.getCategory().getProduct_categ_name();
				}

	
				if(value.equals("")||value==null){
					value="";
				}
				cel = new Cell(value, col);		
				row.addCell(cel);
			}

		}
		product.addRows(row);
		
		String productName = productOdoo.getName_template();
		
		
		ProductType productType = productOdoo.getType();
		String productParentRefListID = productOdoo.getCateg_id();
		
		product.setParentRefListID(productParentRefListID);
		product.setProductType(productType);
		product.setProductName(productName);

		System.out.println("TRANSFORM PRODUCT:"+productOdoo.getName_template()+"\tDONE");

		return product;
	}

	public Vendor getVendor(VendorOdoo vendorOdoo) {
		
		Vendor vendor = new Vendor();
		
		Row row = new Row();
		for (int i = 0; i < vendor.getColumnSize(); i++) {

			Column col = vendor.getColumn(i);
			if (col.isInsert() == true) {
				String field = col.getColumnName();
			
				Cell cel = new Cell();
				String value="";
				if(field.equals("name")){
					value = vendorOdoo.getPartner_name();
				}
				else if(field.equals("accountNumber")){
					value= vendorOdoo.getVendor_id();
				}
				
				else if(field.equals("companyName")){
					value = vendorOdoo.getPartner_name();
				}
				
				else if(field.equals("VendorAddressAddr1")){
					value = vendorOdoo.getPartner_name();
				}
				else if(field.equals("VendorAddressAddr2")){
					value = vendorOdoo.getPartner_street();
				}
				else if(field.equals("VendorAddressAddr3")){
					value = vendorOdoo.getPartner_street2();
				}
				else if(field.equals("VendorAddressCity")){
					value = vendorOdoo.getPartner_city();
				}
				
				else if(field.equals("Phone")){
					if(!(vendorOdoo.getPhone()==null)){
						value = vendorOdoo.getPhone();
					}
				}
				else if(field.equals("Fax")){
					if(!(vendorOdoo.getFax()==null)){
						value = vendorOdoo.getFax();
					}
					
				}
				else if(field.equals("Email")){
					if(!(vendorOdoo.getEmail()==null)){
						value = vendorOdoo.getEmail();
					}
				}
			
				if(value.equals("")||value==null){
					value="";
				}
				cel = new Cell(value, col);		
				row.addCell(cel);
			}

		}
		vendor.addRows(row);
		System.out.println("TRANSFORM VENDOR:"+vendorOdoo.getPartner_name()+"\tDONE");

		return vendor;

	}
	

	public SaleOrderLine getSaleOrderLine(SaleOrderOdoo so_odoo) {
		DecimalFormat df = new DecimalFormat("#.00"); 
		
		ArrayList<SaleOrderLineOdoo> soLineOdoo = so_odoo.getLines();
		SaleOrderLine soLine = new SaleOrderLine();
		CustomerOdoo customer = so_odoo.getCustomer();
		String pricelist = so_odoo.getPricelist_id();
		double rate = new CurrencyCode().getCurrencyRate(pricelist);
		
		
		int soLineOdooSize = soLineOdoo.size();
		
		for (int j=0;j<soLineOdooSize;j++){
		SaleOrderLineOdoo line = soLineOdoo.get(j);

		Row row = new Row();
		for (int i = 0; i < soLine.getColumnSize(); i++) {

			Column col = soLine.getColumn(i);
			if (col.isInsert() == true) {
				String field = col.getColumnName();
			
				Cell cel = new Cell();

				String value="";
				if(field.equals("CustomerRefListID")){
					value = customer.getCustomer_id();
				}
				else if(field.equals("TemplateRefListID")){
					value = "8000000D-1455041288";
				}
				else if(field.equals("RefNumber")){
					value= so_odoo.getSo_number();
				}
				else if(field.equals("SalesOrderLineItemRefListID")){
					value = line.getProduct_id();
					if(value.equals("null")||value==null){
						value="168236";
					}
				}
				else if(field.equals("SalesOrderLineDesc")){
					value = line.getSo_desc();
					if(value.equals("")||value==null){
						value="TBD";
					}
				}
				else if(field.equals("SalesOrderLineQuantity")){
					
					value = ""+Integer.parseInt(line.getQty());
					
					if(value.equals("")||value==null){
						value="0";
					}
				}
				else if(field.equals("SalesOrderLineRate")){
					value = line.getPrice();
					double price=  Double.parseDouble(value)/rate;
					value = ""+String.format( "%.2f", price );
				}
				else if(field.equals("CustomFieldSalesOrderLineOther1")){
					value = line.getSequence();
				}
					
				else if(field.equals("SalesOrderLineSalesTaxCodeRefListID")){
					value = "80000002-1451927059";
				}
				if(value.equals("")||value==null){
					value="";
				}
				cel = new Cell(value, col);		
				row.addCell(cel);
			}

		}
		soLine.addRows(row);
		}

		return soLine;
	

	}

	public SaleOrder getSaleOrder(SaleOrderOdoo so_odoo) {
		
		SaleOrder so = new SaleOrder();
		
		Row row = new Row();
		for (int i = 0; i < so.getColumnSize(); i++) {

			Column col = so.getColumn(i);
			if (col.isInsert() == true) {
				String field = col.getColumnName();
			
				Cell cel = new Cell();

				String value="";
				if(value.equals("")||value==null){
					value="";
				}
				if(field.equals("CustomerRefListID")){
					so.setCustomerRefListID(value);
				}
				else if(field.equals("TemplateRefListID")){
					so.setTemplateRefListID(value);
				}
				else if(field.equals("RefNumber")){
					value = so_odoo.getSo_number();
					so.setRefNumber(value);
				}
				else if(field.equals("PONumber")){
					value = so_odoo.getX_contract();
					so.setPONumber(value);
				}
				else if(field.equals("BillAddressAddr1")){
					value = so_odoo.getCustomer().getInvoice_name();
					so.setBillAddressAddr1(value);
				}
				else if(field.equals("BillAddressAddr2")){
					value = so_odoo.getCustomer().getInvoice_street();
					so.setBillAddressAddr2(value);
				}
				else if(field.equals("BillAddressAddr3")){
					value = so_odoo.getCustomer().getInvoice_street2();
					so.setBillAddressAddr3(value);
				}
				else if(field.equals("BillAddressCity")){
					value = so_odoo.getCustomer().getInvoice_city();
					so.setBillAddressCity(value);
				}
				else if(field.equals("BillAddressPostalCode")){
					value = so_odoo.getCustomer().getInvoice_zip();
					so.setBillAddressPostalCode(value);
				}
				else if(field.equals("ShipAddressPostalCode")){
					value = so_odoo.getCustomer().getShipping_zip();
					so.setShipAddressPostalCode(value);
				}
				
				else if(field.equals("ShipAddressAddr1")){
					value = so_odoo.getCustomer().getShipping_name();
					so.setShipAddressAddr1(value);
				}
				else if(field.equals("ShipAddressAddr2")){
					value = so_odoo.getCustomer().getShipping_street();
					so.setShipAddressAddr2(value);
				}
				else if(field.equals("ShipAddressAddr3")){
					value = so_odoo.getCustomer().getShipping_street2();
					so.setShipAddressAddr3(value);
				}
				else if(field.equals("ShipAddressCity")){
					value = value = so_odoo.getCustomer().getShipping_city();
					so.setShipAddressCity(value);
				}
				else if(field.equals("SalesRepRefListID")){
					value = so_odoo.getUser_id();
					value = new SalesRepCode().getRepName(value);
					so.setSalesRepRefListID(value);

				}
				else if(field.equals("TermsRefFullName")){
					value = so_odoo.getPayment_term();
					value = new PaymentTermCode().getPaymentTerm(value);
					so.setTermsRefFullName(value);
					
				}
				
				else if(field.equals("FOB")){
					value = so_odoo.getX_type();
					so.setFOB(value);

				}
				else if(field.equals("SalesOrderLineSalesTaxCodeRefListID")){
					value = "80000002-1451927059";
				}			
			

				cel = new Cell(value, col);		
				row.addCell(cel);
			}
		}
		so.addRows(row);
		
		SaleOrderLine soLine = new Transform().getSaleOrderLine(so_odoo);
		so.setSoLines(soLine);
		System.out.println("TRANSFORM SALE ORDER:"+so_odoo.getSo_number()+"\tDONE");

		return so;
	}


}
