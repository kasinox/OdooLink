package com.aionsoft.qblink.view;

import java.util.ArrayList;

import com.aionsoft.qblink.model.quickbooks.Customer;
import com.aionsoft.qblink.model.quickbooks.Invoice;
import com.aionsoft.qblink.model.quickbooks.Product;
import com.aionsoft.qblink.model.quickbooks.ProductCategory;
import com.aionsoft.qblink.model.quickbooks.SaleOrder;
import com.aionsoft.qblink.model.quickbooks.Vendor;

public class QuickbooksData {
	ArrayList<SaleOrder> saleOrderQB = new ArrayList<>();
	ArrayList<Invoice> invoiceQB = new ArrayList<>();
	ArrayList<Customer> customerQB = new ArrayList<>();
	ArrayList<Vendor> vendorsQB = new ArrayList<>();
	ArrayList<Product> productsQB = new ArrayList<>();
	ArrayList<ProductCategory> productCategoryQB = new ArrayList<>();
	
	public QuickbooksData(ArrayList<SaleOrder> saleOrderQB, ArrayList<Customer> customerQB, ArrayList<Vendor> vendorsQB,
			ArrayList<Product> productsQB, ArrayList<ProductCategory> productCategoryQB) {
		super();
		this.saleOrderQB = saleOrderQB;
		this.customerQB = customerQB;
		this.vendorsQB = vendorsQB;
		this.productsQB = productsQB;
		this.productCategoryQB = productCategoryQB;
	}
	
	public QuickbooksData(){
		
	}
	
	
	
	
	public ArrayList<SaleOrder> getSaleOrderQB() {
		return saleOrderQB;
	}
	public ArrayList<Customer> getCustomerQB() {
		return customerQB;
	}
	public ArrayList<Vendor> getVendorsQB() {
		return vendorsQB;
	}
	public ArrayList<Product> getProductsQB() {
		return productsQB;
	}
	public ArrayList<ProductCategory> getProductCategoryQB() {
		return productCategoryQB;
	}
	public void setSaleOrderQB(ArrayList<SaleOrder> saleOrderQB) {
		this.saleOrderQB = saleOrderQB;
	}
	public void setCustomerQB(ArrayList<Customer> customerQB) {
		this.customerQB = customerQB;
	}
	public void setVendorsQB(ArrayList<Vendor> vendorsQB) {
		this.vendorsQB = vendorsQB;
	}
	public void setProductsQB(ArrayList<Product> productsQB) {
		this.productsQB = productsQB;
	}
	public void setProductCategoryQB(ArrayList<ProductCategory> productCategoryQB) {
		this.productCategoryQB = productCategoryQB;
	}
	
	

}
