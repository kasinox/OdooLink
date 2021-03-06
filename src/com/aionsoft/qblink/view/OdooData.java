package com.aionsoft.qblink.view;

import java.util.ArrayList;

import org.w3c.dom.Document;

import com.aionsoft.qblink.model.odoo.CategoryOdoo;
import com.aionsoft.qblink.model.odoo.CustomerOdoo;
import com.aionsoft.qblink.model.odoo.ProductOdoo;
import com.aionsoft.qblink.model.odoo.SaleOrderLineOdoo;
import com.aionsoft.qblink.model.odoo.SaleOrderOdoo;
import com.aionsoft.qblink.model.odoo.VendorOdoo;

public class OdooData {
	
	ArrayList<SaleOrderLineOdoo> soLines = new ArrayList<>();
	ArrayList<CustomerOdoo> customers = new ArrayList<>();
	ArrayList<SaleOrderOdoo> saleOrders= new ArrayList<>();
	ArrayList<VendorOdoo> vendors = new ArrayList<>();
	ArrayList<ProductOdoo> products = new ArrayList<>();
	ArrayList<CategoryOdoo> categories = new ArrayList<>();

	public ArrayList<SaleOrderLineOdoo> getSoLines() {
		return soLines;
	}

	public ArrayList<CustomerOdoo> getCustomers() {
		return customers;
	}
	public ArrayList<SaleOrderOdoo> getSaleOrders() {
		return saleOrders;
	}
	public ArrayList<VendorOdoo> getVendors() {
		return vendors;
	}
	public ArrayList<ProductOdoo> getProducts() {
		return products;
	}
	public ArrayList<CategoryOdoo> getCategories() {
		return categories;
	}

	public void setSoLines(ArrayList<SaleOrderLineOdoo> soLines) {
		this.soLines = soLines;
	}

	public void setCustomers(ArrayList<CustomerOdoo> customers) {
		this.customers = customers;
	}
	public void setSaleOrders(ArrayList<SaleOrderOdoo> saleOrders) {
		this.saleOrders = saleOrders;
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

}
