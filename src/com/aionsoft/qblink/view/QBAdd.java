package com.aionsoft.qblink.view;

import java.sql.SQLException;
import java.util.ArrayList;

import com.aionsoft.qblink.model.odoo.CategoryOdoo;
import com.aionsoft.qblink.model.odoo.ProductOdoo;
import com.aionsoft.qblink.model.odoo.SaleOrderOdoo;
import com.aionsoft.qblink.model.odoo.VendorOdoo;
import com.aionsoft.qblink.model.quickbooks.Customer;
import com.aionsoft.qblink.model.quickbooks.Product;
import com.aionsoft.qblink.model.quickbooks.ProductCategory;
import com.aionsoft.qblink.model.quickbooks.SaleOrder;
import com.aionsoft.qblink.model.quickbooks.Vendor;
import com.aionsoft.qblink.processor.ProcessorCategoryQbooks;
import com.aionsoft.qblink.processor.ProcessorCustomerQbooks;
import com.aionsoft.qblink.processor.ProcessorProductQbooks;
import com.aionsoft.qblink.processor.ProcessorSaleOrderQbooks;
import com.aionsoft.qblink.processor.ProcessorVendorQbooks;

public class QBAdd {
	
	
	ArrayList<ProductCategory> categoryFailed = new ArrayList<>(); 
	ArrayList<Customer> customerFailed = new ArrayList<>();
	ArrayList<Product> productFailed = new ArrayList<>();
	ArrayList<Vendor> vendorFailed = new ArrayList<>();
	ArrayList<SaleOrder> saleOrderFailed = new ArrayList<>();

	public QBAdd() {

	}

	public boolean addSaleOrder(ArrayList<String> ids) throws SQLException {
		OdooData data = new QBExtract().extractOdooData(ids);
		data = new QBExist().exist(data);
		QuickbooksData qbData = new QBTransform().transform(data);
		
		System.out.println(qbData.getCustomerQB().size());
		System.out.println(qbData.getVendorsQB().size());	
		System.out.println(qbData.getProductCategoryQB().size());
		System.out.println(qbData.getProductsQB().size());
		System.out.println(qbData.getSaleOrderQB().size());
		
//		System.exit(0);
		boolean insertCustomer = insertCustomer(qbData);
		boolean insertVendor = insertVendors(qbData);
		boolean insertCategory = insertProductCategory(qbData);
		boolean insertProduct = insertProduct(qbData);

		
		boolean insert = insertSaleOrders(qbData);

		reportProduct();
		reportVendor();
		reportCategory();
		reportCustomer();
		reportSaleOrder();
		return true;
	}
	
	public boolean addItems(ArrayList<String> ids) throws SQLException{
		
		OdooData data = new QBExtract().extractOdooData(ids);
		data = new QBExist().exist(data);
		QuickbooksData qbData = new QBTransform().transform(data);
		boolean insertVendor = insertVendors(qbData);
		boolean insertCategory = insertProductCategory(qbData);
		boolean insertProduct = insertProduct(qbData);

		return true;
		
	}

	public boolean insertSaleOrders(QuickbooksData qbData) throws SQLException {

		ProcessorSaleOrderQbooks psq = new ProcessorSaleOrderQbooks();
		ArrayList<SaleOrder> saleOrderList = qbData.getSaleOrderQB();

		for (int i = 0; i < saleOrderList.size(); i++) {

			SaleOrder so = saleOrderList.get(i);
			boolean insert = psq.insertSaleOrder(so);
//			System.out.println(so.getSaleOrderLine().getRowSize());

			if (insert == false) {
				saleOrderFailed.add(so);
			}else{
				System.out.println(so.getRefNumber()+"\tDONE");
			}

		}
		return true;
	}

	public boolean insertProduct(QuickbooksData qbData) throws SQLException{
		ProcessorProductQbooks ppq = new ProcessorProductQbooks();
		
		ArrayList<Product> productList = qbData.getProductsQB();
		int size = productList.size();
		for (int i = 0; i < size; i++) {
			Product product = productList.get(i);

			boolean insert = ppq.insertProduct(product);

			if (insert == false) {
				productFailed.add(product);

			}
			
		}
		return true;		

	}

	public boolean insertVendors(QuickbooksData qbData) throws SQLException {
		ProcessorVendorQbooks pvq = new ProcessorVendorQbooks();

		ArrayList<Vendor> vendorList = qbData.getVendorsQB();

		for (int i = 0; i < vendorList.size(); i++) {

			Vendor vendor = vendorList.get(i);

			boolean insert = pvq.insertVendor(vendor);

			if (insert == false) {
				vendorFailed.add(vendor);
			}
		}

		return true;
	}

	public boolean insertCustomer(QuickbooksData qbData) throws SQLException {
		ProcessorCustomerQbooks pcq = new ProcessorCustomerQbooks();

		ArrayList<Customer> customerList = qbData.getCustomerQB();

		int size = customerList.size();
		for (int i = 0; i < size; i++) {
			Customer customer = customerList.get(i);

			boolean insert = pcq.insertCustomer(customer);

			if (insert == false) {
				customerFailed.add(customer);

			}
			
		}
		return true;
	}
	
	public boolean insertProductCategory(QuickbooksData qbData) throws SQLException{
		ProcessorCategoryQbooks pcq = new ProcessorCategoryQbooks();
		
		ArrayList<ProductCategory> categoryList = qbData.getProductCategoryQB();
		int size = categoryList.size();
		for (int i = 0; i < size; i++) {
			ProductCategory category = categoryList.get(i);

			boolean insert = pcq.insertCategory(category);

			if (insert == false) {
				categoryFailed.add(category);

			}
			
		}
		return true;

	}

	private void reportProduct() {

		System.out.println("\tLIST OF FAILED INSERTS FOR Product");
		System.out.println("\tNumbers:" + productFailed.size());
		for (int j = 0; j < productFailed.size(); j++) {
			System.out.println("\t" + productFailed.get(j).getProductName());
		}
	}

	private void reportVendor() {

		System.out.println("\tLIST OF FAILED INSERTS FOR VENDOR");
		System.out.println("\tNumbers:" + vendorFailed.size());
		for (int j = 0; j < vendorFailed.size(); j++) {
			System.out.println("\t" + vendorFailed.get(j).getName());
		}
	}

	private void reportCustomer() {

		System.out.println("\tLIST OF FAILED INSERTS FOR CUSTOMER");
		System.out.println("\tNumbers:" + customerFailed.size());
		for (int j = 0; j < customerFailed.size(); j++) {
			System.out.println("\t" + customerFailed.get(j).getName());
		}
	}

	private void reportSaleOrder() {
		System.out.println("\tLIST OF FAILED INSERTS FOR SALE ORDER");
		System.out.println("\tNumbers:" + saleOrderFailed.size());
		for (int j = 0; j < saleOrderFailed.size(); j++) {
			System.out.println("\t" + saleOrderFailed.get(j).getRefNumber());
		}

	}

	private void reportCategory() {
		System.out.println("\tLIST OF FAILED INSERTS FOR CATEGORY");
		System.out.println("\tNumbers:" + categoryFailed.size());
		for (int j = 0; j < categoryFailed.size(); j++) {
			System.out.println("\t" + categoryFailed.get(j).getCategory_name());
		}
	}

	public boolean addCustomer(ArrayList<String> ids) throws SQLException {
		
		/**
		 * Add Customer only to quickbooks give sales order Number
		 * 
		 */
		
		OdooData data = new QBExtract().extractOdooData(ids);
//		data = new QBExist().exist(data);
		data = new QBExist().existCustomerSync(data);
		QuickbooksData qbData = new QBTransform().transform(data);
		
		System.out.println(qbData.getCustomerQB().size());
		System.out.println(qbData.getVendorsQB().size());	
//		System.out.println(qbData.getProductCategoryQB().size());
//		System.out.println(qbData.getProductsQB().size());
//		System.out.println(qbData.getSaleOrderQB().size());
		
//		System.exit(0);
		boolean insertCustomer = insertCustomer(qbData);
//		boolean insertVendor = insertVendors(qbData);
//		boolean insertCategory = insertProductCategory(qbData);
//		boolean insertProduct = insertProduct(qbData);

		
//		boolean insert = insertSaleOrders(qbData);

//		reportProduct();
		reportVendor();
//		reportCategory();
		reportCustomer();
//		reportSaleOrder();
		return true;
	}
}
