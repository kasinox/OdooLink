package com.alltekusa.qbLink.Viewer.Console.cmd;

import java.sql.SQLException;
import java.util.ArrayList;

import com.alltekusa.qbLink.Odoo.Model.CategoryOdoo;
import com.alltekusa.qbLink.Odoo.Model.CustomerOdoo;
import com.alltekusa.qbLink.Odoo.Model.ProductOdoo;
import com.alltekusa.qbLink.Odoo.Model.VendorOdoo;
import com.alltekusa.qbLink.Quickbooks.Model.Product;
import com.alltekusa.qbLink.Quickbooks.Model.ProductCategory;
import com.alltekusa.qbLink.Quickbooks.Model.Vendor;
import com.alltekusa.qbLink.Quickbooks.Process.Helper.ProcessorCategoryQbooks;
import com.alltekusa.qbLink.Quickbooks.Process.Helper.ProcessorCustomerQbooks;
import com.alltekusa.qbLink.Quickbooks.Process.Helper.ProcessorProductQbooks;
import com.alltekusa.qbLink.Quickbooks.Process.Helper.ProcessorVendorQbooks;

public class QBExist {

	public QBExist() {

	}

	public OdooData exist(OdooData source) throws SQLException {
		OdooData data = source;
		ArrayList<ProductOdoo> productList = data.getProducts();
		ArrayList<VendorOdoo> vendorList = data.getVendors();
		ArrayList<CustomerOdoo> customerList = data.getCustomers();
		ArrayList<CategoryOdoo> categoryList = data.getCategories();
		
		productList=existProducts(productList);
		vendorList = existVendor(vendorList);
		customerList = existCustomer(customerList);
		categoryList=existCategories(categoryList);
		
		data.setCategories(categoryList);
		data.setCustomers(customerList);
		data.setVendors(vendorList);
		data.setProducts(productList);
		
		return data;
	}
//	public OdooData existProduct(OdooData source)
	public OdooData existCustomerSync(OdooData source) throws SQLException {
		OdooData data = source;
		ArrayList<CustomerOdoo> customerList = data.getCustomers();
		customerList = existCustomer(customerList);
		data.setCustomers(customerList);
		
		return data;
	}

	private ArrayList<ProductOdoo> existProducts(ArrayList<ProductOdoo> products) throws SQLException {
		
		System.out.println("Exist Product->");

		ProcessorProductQbooks ppq = new ProcessorProductQbooks();
		ArrayList<ProductOdoo> productList = new ArrayList<>();

		int size = products.size();

		for (int i = 0; i < size; i++) {
			ProductOdoo productOdoo = products.get(i);

			boolean insert = false;
			String id = products.get(i).getPt_id();
			String name = products.get(i).getName_template();

			boolean existProductId = ppq.existProduct((id));
			boolean existProductName = ppq.existProductName(name);
			System.out.println("->\t"+name+","+existProductId+","+existProductName);
			if (!existProductId && !existProductName) {
				productList.add(productOdoo);
			}

		}
		
		for (int j=0;j<productList.size();j++){
			System.out.println(productList.get(j).getName_template());
		}

		System.out.println("\tEND OF checkExist Product");
		
//		System.exit(0);
		return productList;
	}

	private ArrayList<CustomerOdoo> existCustomer(ArrayList<CustomerOdoo> customers) {
		
		System.out.println("Exist Customer->");
		ArrayList<CustomerOdoo> customerList = new ArrayList<>();

		ProcessorCustomerQbooks pcq = new ProcessorCustomerQbooks();
		int size = customers.size();
		for (int i = 0; i < size; i++) {
			CustomerOdoo customer = customers.get(i);
			String name = customers.get(i).getPartner_name();
			boolean existCustomerId = pcq.existCustomer(customers.get(i).getCustomer_id());
			boolean existCustomerName = pcq.existCustomerName(customers.get(i).getPartner_name());
			System.out.println("->\t"+name+","+existCustomerId+","+existCustomerName);

			if (!existCustomerId && !existCustomerName) {
				customerList.add(customer);
			}

		}

		for (int j=0;j<customerList.size();j++){
			System.out.println(customerList.get(j).getPartner_name());
		}

		System.out.println("\tEND OF CheckExist CUSTOMER");
		return customerList;

	}

	private ArrayList<VendorOdoo> existVendor(ArrayList<VendorOdoo> vendors) throws SQLException {
		
		System.out.println("Exist Vendor->");
		ArrayList<VendorOdoo> vendorList = new ArrayList<>();
		ProcessorVendorQbooks pvq = new ProcessorVendorQbooks();

		int size = vendors.size();
		for (int i = 0; i < size; i++) {
			VendorOdoo vendor = vendors.get(i);

			boolean existVendorId = pvq.existVendor(vendors.get(i).getVendor_id());
			boolean existVendorName = pvq.existVendorName(vendors.get(i).getPartner_name());

			String name = vendors.get(i).getPartner_name();
			System.out.println("->\t"+name+","+existVendorId+","+existVendorName);

			if (!existVendorId && !existVendorName) {
				vendorList.add(vendor);
			}

		}
		System.out.println("\tEND OF checkExist VENDOR");
		return vendorList;

	}

	private ArrayList<CategoryOdoo> existCategories(ArrayList<CategoryOdoo> categories) throws SQLException {
		
		System.out.println("Exist Categories->");
		// TODO Auto-generated method stub
		ArrayList<CategoryOdoo> categoryList = new ArrayList<>();
		ProcessorCategoryQbooks pcq = new ProcessorCategoryQbooks();
		int size = categories.size();
		for (int i = 0; i < categories.size(); i++) {
			CategoryOdoo categoryOdoo = categories.get(i);
			boolean existCategoryId = pcq.existCategory(categories.get(i).getProduct_category_id());
			boolean existCategoryName = pcq.existCategoryName(categories.get(i).getProduct_categ_name());
			String name = categories.get(i).getProduct_categ_name();

			System.out.println("->\t"+name+","+existCategoryId+","+existCategoryName);

			
			if (!existCategoryId && !existCategoryName) {
				categoryList.add(categoryOdoo);
			}

		}
		System.out.println("\tEND OF CheckEXIST CATEGORY");

		return categoryList;
	}

}
