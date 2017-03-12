package com.alltekusa.qbLink.Quickbooks.Process.Helper;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;



import org.w3c.dom.NodeList;

import com.aionsoft.qblink.model.Odoo.CategoryOdoo;
import com.aionsoft.qblink.model.Odoo.CustomerOdoo;
import com.aionsoft.qblink.model.Odoo.ProductOdoo;
import com.aionsoft.qblink.model.Odoo.SaleOrderLineOdoo;
import com.aionsoft.qblink.model.Odoo.SaleOrderOdoo;
import com.aionsoft.qblink.model.Odoo.VendorOdoo;
import com.aionsoft.qblink.model.Odoo.ProductOdoo.ProductType;
import com.alltekusa.qbLink.Configuration.CurrencyCode;
import com.alltekusa.qbLink.Configuration.Incoterm;
import com.alltekusa.qbLink.Configuration.IncotermCode;
import com.alltekusa.qbLink.Configuration.PaymentTermCode;
import com.alltekusa.qbLink.Configuration.SalesRepCode;
import com.alltekusa.qbLink.Model.Base.Cell;
import com.alltekusa.qbLink.Model.Base.Column;
import com.alltekusa.qbLink.Model.Base.Row;
import com.alltekusa.qbLink.Quickbooks.Model.Customer;
import com.alltekusa.qbLink.Quickbooks.Model.Product;
import com.alltekusa.qbLink.Quickbooks.Model.ProductCategory;
import com.alltekusa.qbLink.Quickbooks.Model.SaleOrder;
import com.alltekusa.qbLink.Quickbooks.Model.SaleOrderLine;
import com.alltekusa.qbLink.Quickbooks.Model.Vendor;

public class Transform {
	
	String defaultSupplier = "21806"; //VENDOR TBD
	
	public Transform(){
		
//		System.out.println("============TRANSFORM");
		
	}

	public Customer getCustomer(CustomerOdoo customerOdoo){
		
		;
		Customer customer = new Customer();
		
		Row row = new Row();
//		System.out.println("size:"+customer.getColumnSize());
		for (int i = 0; i < customer.getColumnSize(); i++) {

			Column col = customer.getColumn(i);
			if (col.isInsert() == true) {
				String field = col.getColumnName();
			
				Cell cel = new Cell();
//				System.out.println();
//				System.out.println("fieldName"+field);
				String value="";
				if(field.equals("name")){
					value = customerOdoo.getPartner_name();
				}
				else if(field.equals("accountNumber")){
//					System.out.println(customerOdoo.getCustomer_id());
					value= customerOdoo.getCustomer_id();
//					System.exit(0);
				}
				
				else if(field.equals("companyName")){
					value = customerOdoo.getPartner_name();
				}
				
				else if(field.equals("BillAddressAddr1")){
					value = customerOdoo.getPartner_name();
					if(value.length()>31){
						value=value.substring(0,31);
					}
				}
				else if(field.equals("BillAddressAddr2")){
					value = customerOdoo.getPartner_street();
					if(value.length()>31){
						value=value.substring(0,31);
					}
				}
				else if(field.equals("BillAddressAddr3")){
					value = customerOdoo.getPartner_street2();
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
		
//		System.out.println("TRANSFORM CATEGORY");
			ProductCategory category = new ProductCategory();
			
			Row row = new Row();
			for (int i = 0; i < category.getColumnSize(); i++) {

				Column col = category.getColumn(i);
				if (col.isInsert() == true) {
					String field = col.getColumnName();
				
					Cell cel = new Cell();
//					System.out.println();
//					System.out.println("fieldName"+field);
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
		
//		System.out.println("TRANSFORM PRODUCT");
		Product product = new Product();
		
		Row row = new Row();
		for (int i = 0; i < product.getColumnSize(); i++) {

			Column col = product.getColumn(i);
			if (col.isInsert() == true) {
				String field = col.getColumnName();
			
				Cell cel = new Cell();
//				System.out.println();
//				System.out.println("fieldName"+field);
				String value="";
				if(field.equals("Name")){
//					System.out.println("\tname"+productOdoo.getName_template());
					value = productOdoo.getName_template();
					value = value.replace(":", "");
				}
				if(field.equals("IsActive")){
//					System.out.println("\tname"+productOdoo.getName_template());
					value = "TRUE";
				}

				else if(field.equals("SalesDesc")){
//					System.out.println("\tSalesDesc"+productOdoo.getDescription_sale());

					value = productOdoo.getDescription_sale();
//					value="xxx";
				}
				else if(field.equals("SalesPrice")){
//					System.out.println("SalesPrice"+productOdoo.getList_price());
					value = productOdoo.getList_price();
					
				}
				else if(field.equals("PurchaseDesc")){
//					System.out.println("PurchaseDesc"+productOdoo.getDescription_purchase());
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
//					System.out.println("ManufacturerPartNumber:"+productOdoo.getSupplier().getProduct_name());

					

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
//					System.out.println("CustomFieldodoo_id:"+productOdoo.getPt_id());

					value = productOdoo.getPt_id();
					if(value.equals("null")||value==null){
						value="168236";
					}
				}
//				else if(field.equals("IsActive")){
//					value = productOdoo.get
//				}
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
//		System.out.println(product.printProductValue());
		
		String productName = productOdoo.getName_template();
		
		
		ProductType productType = productOdoo.getType();
//		System.out.println("Product TYPE:-----------"+productType);
		String productParentRefListID = productOdoo.getCateg_id();
		
		product.setParentRefListID(productParentRefListID);
		product.setProductType(productType);
		product.setProductName(productName);
//		System.out.println(productName);
//		System.out.println(productType.toString());
//		System.out.println(productParentRefListID);
//		System.exit(0);
		System.out.println("TRANSFORM PRODUCT:"+productOdoo.getName_template()+"\tDONE");

		return product;
	}

	public Vendor getVendor(VendorOdoo vendorOdoo) {
		
//		System.out.println("TRANSFORM VENDOR");
		Vendor vendor = new Vendor();
		
		Row row = new Row();
//		System.out.println("size:"+customer.getColumnSize());
		for (int i = 0; i < vendor.getColumnSize(); i++) {

			Column col = vendor.getColumn(i);
			if (col.isInsert() == true) {
				String field = col.getColumnName();
			
				Cell cel = new Cell();
//				System.out.println();
//				System.out.println("fieldName"+field);
				String value="";
				if(field.equals("name")){
					value = vendorOdoo.getPartner_name();
				}
				else if(field.equals("accountNumber")){
//					System.out.println(customerOdoo.getCustomer_id());
					value= vendorOdoo.getVendor_id();
//					System.exit(0);
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
//					System.out.println("Phone"+vendorOdoo.getPhone());
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
//		System.out.println("TRANSFORM SALE ORDER LINE");
		
		ArrayList<SaleOrderLineOdoo> soLineOdoo = so_odoo.getLines();
		SaleOrderLine soLine = new SaleOrderLine();
		CustomerOdoo customer = so_odoo.getCustomer();
		String pricelist = so_odoo.getPricelist_id();
		double rate = new CurrencyCode().getCurrencyRate(pricelist);
//		System.out.println(pricelist);
		
//		System.exit(0);
		
		int soLineOdooSize = soLineOdoo.size();
		
		for (int j=0;j<soLineOdooSize;j++){
		SaleOrderLineOdoo line = soLineOdoo.get(j);

		Row row = new Row();
//		System.out.println("size:"+customer.getColumnSize());
		for (int i = 0; i < soLine.getColumnSize(); i++) {

			Column col = soLine.getColumn(i);
			if (col.isInsert() == true) {
				String field = col.getColumnName();
			
				Cell cel = new Cell();

				String value="";
				if(field.equals("CustomerRefListID")){
//					value = "80000089-1475820160";
					value = customer.getCustomer_id();
				}
				else if(field.equals("TemplateRefListID")){
					value = "8000000D-1455041288";
				}
				else if(field.equals("RefNumber")){
					value= so_odoo.getSo_number();
//					value = soLineOdoo.get
				}
				else if(field.equals("SalesOrderLineItemRefListID")){
//					value = 
					value = line.getProduct_id();
					if(value.equals("null")||value==null){
						value="168236";
					}
				}
				else if(field.equals("SalesOrderLineDesc")){
//					value = 
					value = line.getSo_desc();
					if(value.equals("")||value==null){
						value="TBD";
					}
				}
				else if(field.equals("SalesOrderLineQuantity")){
//					value = 
					
					value = ""+Integer.parseInt(line.getQty());
					
					if(value.equals("")||value==null){
						value="0";
					}
				}
				else if(field.equals("SalesOrderLineRate")){
//					value = 
					value = line.getPrice();
					double price=  Double.parseDouble(value)/rate;
					value = ""+String.format( "%.2f", price );
//					System.out.println(value);
//					System.exit(0);
				}
				else if(field.equals("CustomFieldSalesOrderLineOther1")){
//					value = 
					value = line.getSequence();
//					System.out.println(value);
//					System.exit(0);
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
		
//		System.out.println("TRANSFORM SALE ORDER");
//		SaleOrderLine soLine = new SaleOrderLine();
		SaleOrder so = new SaleOrder();
		
		Row row = new Row();
//		System.out.println("size:"+customer.getColumnSize());
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
//					value = "80000089-1475820160";
					so.setCustomerRefListID(value);
				}
				else if(field.equals("TemplateRefListID")){
//					value = "8000000D-1455041288";
					so.setTemplateRefListID(value);
				}
				else if(field.equals("RefNumber")){
					value = so_odoo.getSo_number();
					so.setRefNumber(value);
				}
				else if(field.equals("PONumber")){
					value = so_odoo.getX_contract();
					so.setPONumber(value);
					System.out.println(so.getPONumber());
				}
				else if(field.equals("BillAddressAddr1")){
					value = so_odoo.getCustomer().getInvoice_name();
					so.setBillAddressAddr1(value);
//					value = so_odoo.getCustomer().getInvoice_street();
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
//				else if(field.equals("DueDate")){
//					value = "Line 11";
//				}
				else if(field.equals("SalesRepRefListID")){
					value = so_odoo.getUser_id();
					value = new SalesRepCode().getRepName(value);
//					System.out.println(value);
					so.setSalesRepRefListID(value);
//					System.out.println(so.getSalesRepRefListID());

				}
				else if(field.equals("TermsRefFullName")){
					value = so_odoo.getPayment_term();
//					System.out.println(value);
					value = new PaymentTermCode().getPaymentTerm(value);
//					System.out.println(value);
					so.setTermsRefFullName(value);
//					System.out.println(so.getTermsRefFullName());
//					System.exit(0);

					
				}
				
				else if(field.equals("FOB")){
					//order type machine / parts or service
					value = so_odoo.getX_type();
//					System.out.println(value);
//					value = new IncotermCode().getIncoterm(value);
//					System.out.println(value);
//					so.setFOB(value);
//					System.out.println(so.getFOB());
//					System.exit(0);
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
