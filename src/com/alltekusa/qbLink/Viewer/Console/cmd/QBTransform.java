package com.alltekusa.qbLink.Viewer.Console.cmd;

import java.sql.SQLException;
import java.util.ArrayList;

import com.alltekusa.qbLink.Odoo.Model.CategoryOdoo;
import com.alltekusa.qbLink.Odoo.Model.CustomerOdoo;
import com.alltekusa.qbLink.Odoo.Model.ProductOdoo;
import com.alltekusa.qbLink.Odoo.Model.SaleOrderLineOdoo;
import com.alltekusa.qbLink.Odoo.Model.SaleOrderOdoo;
import com.alltekusa.qbLink.Odoo.Model.VendorOdoo;
import com.alltekusa.qbLink.Quickbooks.Model.Customer;
import com.alltekusa.qbLink.Quickbooks.Model.Product;
import com.alltekusa.qbLink.Quickbooks.Model.ProductCategory;
import com.alltekusa.qbLink.Quickbooks.Model.SaleOrder;
import com.alltekusa.qbLink.Quickbooks.Model.Vendor;
import com.alltekusa.qbLink.Quickbooks.Process.Helper.ProcessorCategoryQbooks;
import com.alltekusa.qbLink.Quickbooks.Process.Helper.ProcessorCustomerQbooks;
import com.alltekusa.qbLink.Quickbooks.Process.Helper.ProcessorProductQbooks;
import com.alltekusa.qbLink.Quickbooks.Process.Helper.ProcessorSaleOrderQbooks;
import com.alltekusa.qbLink.Quickbooks.Process.Helper.ProcessorVendorQbooks;

public class QBTransform {
	
	public QBTransform(){
		
	}
	
	public QuickbooksData transform(OdooData data) throws SQLException{

		QuickbooksData qbData = new QuickbooksData();
		
		ArrayList<CustomerOdoo> refinedCustomers = data.getCustomers();
		ArrayList<VendorOdoo> refinedVendors = data.getVendors();
		ArrayList<CategoryOdoo> refinedCategories = data.getCategories();
		ArrayList<ProductOdoo> refinedProducts = data.getProducts();
		ArrayList<SaleOrderOdoo> saleOrders = data.getSaleOrders();
		
		ArrayList<Customer> customerQB = new ArrayList<>();
		ArrayList<Vendor> vendorsQB = new ArrayList<>();
		ArrayList<ProductCategory> productCategoryQB = new ArrayList<>();
		ArrayList<Product> productsQB = new ArrayList<>();
		ArrayList<SaleOrder> saleOrderQB= new ArrayList<>();
		/**
		 * Print each of the lists
		 */

//		printRefinedItems(customers, refinedProducts, refinedVendors, refinedCustomers, refinedCategories);

		/***
		 * Quickbooks Seciton 
		 * 1. transform customerOdoo to custoemrQuickbooks 
		 * 2. transform categoryOdoo to productQuickbooks
		 * 3. transform vendorOdoo to vendorQuickbooks 
		 * 4. transform productOdoo to productQuickbooks 
		 * 5. transform saleOrderLineOdoo to saleOrderQuickbooks
		 * 6. add 1-5 to quickbooks
		 * 
		 */

		/***
		 * Section 1. Transform Customers
		 */

		
		
		System.out.println("\nTransforming Customers");
//		refinedCustomers = existCustomer(refinedCustomers);
		customerQB= transformCustomer(refinedCustomers);
//		insertCustomer(customerQB); 
		// done//
		/***
		 * Section 2. Transform product Categories
		 * 
		 */
		System.out.println("\nTransforming Vendors");
		vendorsQB = transformVendors(refinedVendors);
		/***
		 * Section 3. Transform products
		 */
		System.out.println("\nTransforming Categories");
		productCategoryQB = transformCategories(refinedCategories);
		
		
		/***
		 * // * Section 4. Transform products
		 */
		
		
		System.out.println("Transforming Products");
		productsQB = transformProducts(refinedProducts);
		
		
		/** 
		 * Section 5. Transform Sale Order Lines
		 * 
		 */
		
		System.out.println("Transforming Sale Order");
		saleOrderQB=transformSaleOrders(saleOrders);
		
		qbData.setCustomerQB(customerQB);
		qbData.setProductCategoryQB(productCategoryQB);
		qbData.setProductsQB(productsQB);
		qbData.setSaleOrderQB(saleOrderQB);
		qbData.setVendorsQB(vendorsQB);
		
//		reportCustomer();
//		reportVendor();
//		reportCategory();
//		reportProduct();
//		reportSaleOrder();

		System.out.println("END OF PROGRAM");

	return qbData;
}
	
	private ArrayList<Customer >transformCustomer(ArrayList<CustomerOdoo> customers) throws SQLException {
		ArrayList<Customer> customerList = new ArrayList<>();
		
		ProcessorCustomerQbooks pcq = new ProcessorCustomerQbooks();
		int size =customers.size();
		for (int i = 0; i < size; i++) {
			Customer customer = pcq.transformCustomer(customers.get(i));
			customerList.add(customer);
		}
		
		System.out.println("\tEND OF TRANSFORM CUSTOMER");
		return customerList;
	}
	
	private ArrayList<Product> transformProducts(ArrayList<ProductOdoo> products) throws SQLException {

	ProcessorProductQbooks ppq = new ProcessorProductQbooks();
	ArrayList<Product> qbProducts = new ArrayList<>();
	
	int size = products.size();
	
	for(int i=0;i<size;i++){
		ProductOdoo productOdoo = products.get(i);
		Product product = ppq.transformProduct(productOdoo);
		qbProducts.add(product);
//		boolean insert = false;
//		String id = products.get(i).getPt_id();
//		String name = products.get(i).getName_template();
//		
//		boolean existProductId = ppq.existProduct((id));
//		boolean existProductName = ppq.existProductName(name);
//		
////		System.out.println("here?"+existProductId+existProductName);
//		if (!existProductId && !existProductName) {
////			System.out.println("CHECK POINT 1");
//			insert = ppq.insertProduct(product);
//		} else {
//			
//			System.out.println("VENDOR AREALDY EXIST"+products.get(i).getName_template());
//			insert = true;
//		}
//		if (insert == false) {
//			productFailed.add(products.get(i));
//		}
////		showPercentage(size, i);

	}
	
	System.out.println("\tEND OF Transform Product");
	return qbProducts;
}
	private ArrayList<ProductCategory> transformCategories(ArrayList<CategoryOdoo> categories) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<ProductCategory> productCategoryList = new ArrayList<>();
		ProcessorCategoryQbooks pcq = new ProcessorCategoryQbooks();
		int size=categories.size();
		for(int i = 0;i<categories.size();i++){
			CategoryOdoo categoryOdoo = categories.get(i);
			ProductCategory category = pcq.transformCategory(categoryOdoo);			
			productCategoryList.add(category);

		}		
		System.out.println("\tEND OF TRANSFORM CATEGORY");
		
		return productCategoryList;
		
	}	
	
	private ArrayList<Vendor> transformVendors(ArrayList<VendorOdoo> vendors)throws SQLException{
		ArrayList<Vendor> qbVendor = new ArrayList<>();
		ProcessorVendorQbooks pvq = new ProcessorVendorQbooks();
		
		int size =vendors.size();
		for (int i = 0; i < size; i++) {
			Vendor vendor = pvq.transformCustomer(vendors.get(i));
			qbVendor.add(vendor);
//			insertVendor(vendors, pvq, size, i, vendor);

		}
		
		System.out.println("\tEND OF XForm VENDOR");
		return qbVendor;

		
	}
	private ArrayList<SaleOrder> transformSaleOrders(ArrayList<SaleOrderOdoo> saleOrders) throws SQLException{
		ArrayList<SaleOrder> saleOrderList = new ArrayList<>();
		
		ProcessorSaleOrderQbooks psq = new ProcessorSaleOrderQbooks();
		
		int size= saleOrders.size();
		System.out.println("Number of SaleOrders:"+size);
		ArrayList<SaleOrderLineOdoo> soLinesOdoo = new ArrayList<>();
		for(int i=0;i<size;i++){
			
			SaleOrderOdoo so_odoo = saleOrders.get(i);
			System.out.println("SaleOrder #:"+so_odoo.getSo_number());

				SaleOrder so = psq.transformSaleOrder(so_odoo);
				saleOrderList.add(so);
//				boolean insert = false;
//		
//				boolean existSaleOrder = true;
//					if (existSaleOrder) {
//						System.out.println("hello");
//						insert = psq.insertSaleOrder(so);
//					} else {
//						System.out.println("\tSaleOrder: "+so.getRefNumber()+" already exist");
//						insert = true;
//					}
//					if (insert == false) {
//						saleOrderFailed.add(so_odoo);
//					}
				}
		return saleOrderList;
	}

}
