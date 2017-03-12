package com.alltekusa.qbLink.Viewer.Console.cmd;

import java.sql.SQLException;
import java.util.ArrayList;

import org.w3c.dom.Document;

import com.aionsoft.qblink.model.Odoo.CategoryOdoo;
import com.aionsoft.qblink.model.Odoo.CustomerOdoo;
import com.aionsoft.qblink.model.Odoo.ProductOdoo;
import com.aionsoft.qblink.model.Odoo.SaleOrderLineOdoo;
import com.aionsoft.qblink.model.Odoo.SaleOrderOdoo;
import com.aionsoft.qblink.model.Odoo.VendorOdoo;
import com.alltekusa.qbLink.Helper.ExistInOdoo;
import com.alltekusa.qbLink.Helper.GetURI;
import com.alltekusa.qbLink.Helper.Processor;
import com.alltekusa.qbLink.Helper.Process.SaleOrderProcessor;

public class QBExtract {
	
	public QBExtract(){
		
	}
	public OdooData extractOdooData(ArrayList<String> ids) throws SQLException{
		Processor cpu = new Processor();
		OdooData data = new OdooData();
		
		ArrayList<String> so_ids = ids;
		
		
//		ArrayList<SaleOrderLineOdoo> soLines = new ArrayList<>();
		ArrayList<CustomerOdoo> customers = new ArrayList<>();
		ArrayList<SaleOrderOdoo> saleOrders= new ArrayList<>();
		ArrayList<VendorOdoo> vendors = new ArrayList<>();
		ArrayList<ProductOdoo> products = new ArrayList<>();
		ArrayList<CategoryOdoo> categories = new ArrayList<>();
		
		
		ArrayList<Boolean> odooFileCheckV1 = new ArrayList<>();
		ArrayList<Boolean> odooFileCheckV2 = new ArrayList<>();
		ArrayList<Boolean> odooFileCheckV3 = new ArrayList<>();
		
		boolean flagCheckV1 = true;
		boolean flagCheckV2 = true;
		
		ArrayList<Document> sale_orders = new ArrayList<>();

		
		try {

			ArrayList<String> uris = new ArrayList<String>();
			System.out.println("Get URIs");
			uris = new GetURI().saleOrder(so_ids);
			System.out.println("Get sale orders");
			sale_orders = cpu.getDocument(uris);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			e1.getErrorCode();
			e1.getMessage();

		}

			/***
			 * Check items for completion LVL1 Check
			 */
			levelOneCheck(sale_orders, odooFileCheckV1, flagCheckV1);
			/***
			 * Check items for completion LVL2 Check
			 */
			levelTwoCheck(sale_orders, odooFileCheckV2, flagCheckV2);
			
			/***
			 * Extract Items from Sales Orders
			 * 
			 */
			
			System.out.println("Running Sales Orders");
			for (int i = 0; i < sale_orders.size(); i++) {
				
				SaleOrderProcessor sop = new SaleOrderProcessor(sale_orders.get(i));
				SaleOrderOdoo saleOrder = sop.getSaleOrderOdoo();
				
				
				ArrayList<ProductOdoo> productList = sop.extractProductsOdoo();
				products.addAll(productList);
				System.out.println("SO#" + saleOrder.getSo_number());
				printProduct(productList);

				customersListAdd(customers, sop);
				categories.addAll(sop.getCategoryOdoo());
				vendors.addAll(sop.getVendorsOdoo());
				saleOrders.add(saleOrder);
			}

//			System.exit(0);
			/***
			 * shorten the list with unique items per list
			 * 
			 */

			System.out.println("refining CustomerList");

			ArrayList<CustomerOdoo> refinedCustomers = new ArrayList<>();
			refinedCustomers=refineCustomer(customers);



			System.out.println("refining VendorList");
			ArrayList<VendorOdoo> refinedVendors = new ArrayList<>();
			refinedVendors = refineVendors(vendors);

			System.out.println("refining Categories");
			ArrayList<CategoryOdoo> refinedCategories = new ArrayList<>();
			refinedCategories=refineCategories(categories);


			System.out.println("refining ProductList");
			ArrayList<ProductOdoo> refinedProducts = new ArrayList<>();
			refinedProducts = refinedProductList(products);
			
			data.setCategories(refinedCategories);
			
			data.setCustomers(refinedCustomers);
			data.setProducts(refinedProducts);
			data.setSaleOrders(saleOrders);
//			data.setSoLines(saleOrders.get);
			data.setVendors(refinedVendors);
			
			
		return data;
	}
	
	private static void customersListAdd(ArrayList<CustomerOdoo> customers, SaleOrderProcessor sop) {
		boolean add = true;
		for (int j = 0; j < customers.size(); j++) {
			CustomerOdoo currentCustomer = customers.get(j);
			String current_id = currentCustomer.getCustomer_id();

			if (current_id.equals(sop.getCustomerOdoo().getCustomer_id()))
				add = false;

		}

		if (add == true)
			customers.add(sop.getCustomerOdoo());
	}
	
	private ArrayList<VendorOdoo> refineVendors(ArrayList<VendorOdoo> vendors) {
		ArrayList<VendorOdoo> refinedVendors = new ArrayList<>();
		
		for (int i = 0; i < vendors.size(); i++) {
			VendorOdoo vendor = vendors.get(i);
			String id = vendor.getVendor_id();

			boolean check = true;
			for (int j = 0; j < refinedVendors.size(); j++) {
				String id2 = refinedVendors.get(j).getVendor_id();
				if (id.equals(id2))
					check = false;
//				System.out.println(id + "," + id2);
			}

			if (check == true)
				refinedVendors.add(vendor);

		}
		return refinedVendors;
	}

	private ArrayList<CustomerOdoo> refineCustomer(ArrayList<CustomerOdoo> customers) {
		ArrayList<CustomerOdoo> refinedCustomers = new ArrayList<>();
		
		for (int i = 0; i < customers.size(); i++) {
			CustomerOdoo customer = customers.get(i);
			String id = customer.getCustomer_id();

			boolean check = true;
			for (int j = 0; j < refinedCustomers.size(); j++) {
				String id2 = refinedCustomers.get(j).getCustomer_id();
				if (id.equals(id2))
					check = false;
				// System.out.println(id+","+id2);
			}
			if (check == true)
				refinedCustomers.add(customer);
		}
		
		return refinedCustomers;
	}

	private ArrayList<CategoryOdoo> refineCategories(ArrayList<CategoryOdoo> categories) {
		
		ArrayList<CategoryOdoo> refinedCategories = new ArrayList<>();
		for (int i = 0; i < categories.size(); i++) {

			CategoryOdoo category = categories.get(i);
			String id = category.getProduct_category_id();

			boolean check = true;
			for (int j = 0; j < refinedCategories.size(); j++) {
				String id2 = refinedCategories.get(j).getProduct_category_id();
				if (id.equals(id2))
					check = false;
				// System.out.println(id+","+id2);
			}
			if (check == true)
				refinedCategories.add(category);
		}
		return refinedCategories;
	}

	private ArrayList<ProductOdoo> refinedProductList(ArrayList<ProductOdoo> products) {
		ArrayList<ProductOdoo> refinedProducts = new ArrayList<>();
		for (int i = 0; i < products.size(); i++) {
			String id = products.get(i).getPt_id();

			boolean check = true;
			for (int j = 0; j < refinedProducts.size(); j++) {
				String id2 = refinedProducts.get(j).getPt_id();
				if (id.equals(id2))
					check = false;
				// System.out.println(id+","+id2);
			}
			if (check == true)
				refinedProducts.add(products.get(i));

		}
		return refinedProducts;
	}
	private static void levelTwoCheck(ArrayList<Document> sale_orders, ArrayList<Boolean> odooFileCheckV2,
			boolean flagCheckV2) {
		for (int i = 0; i < sale_orders.size(); i++) {
			boolean odooFileCheckLVL2 = new ExistInOdoo().OdooFileValidationLVL2(sale_orders.get(i));
			odooFileCheckV2.add(odooFileCheckLVL2);
		}

		if (flagCheckV2 == false) {
			System.out.println("System Exit V2");
			System.exit(0);
		}
	}

	private static void levelOneCheck(ArrayList<Document> sale_orders, ArrayList<Boolean> odooFileCheckV1,
			boolean flagCheckV1) {
		for (int i = 0; i < sale_orders.size(); i++) {
			boolean odooFilecheck = new ExistInOdoo().OdooFileValidationLVL1(sale_orders.get(i));
			if (odooFilecheck == false) {
				flagCheckV1 = false;
			}
			odooFileCheckV1.add(odooFilecheck);
		}

		if (flagCheckV1 == false) {
			System.out.println("System Exit V1");
			System.exit(0);
		}
	}
	private static void printRefinedItems(ArrayList<CustomerOdoo> customers, ArrayList<ProductOdoo> refinedProducts,
			ArrayList<VendorOdoo> refinedVendors, ArrayList<CustomerOdoo> refinedCustomers,
			ArrayList<CategoryOdoo> refinedCategories) {
		for (int i = 0; i < refinedCustomers.size(); i++) {
			System.out.println(i + "\tcustomer:" + customers.get(i).getPartner_name());
		}
		for (int i = 0; i < refinedProducts.size(); i++) {
			System.out.println(i + "\tproduct:" + refinedProducts.get(i).getName_template());
		}

		for (int i = 0; i < refinedCategories.size(); i++) {
			System.out.println(i + "\tcategory:" + refinedCategories.get(i).getProduct_categ_name());
		}
		for (int i = 0; i < refinedVendors.size(); i++) {
			System.out.println(i + "\tvendor:" + refinedVendors.get(i).getPartner_name());
		}
	}

	private static void printProduct(ArrayList<ProductOdoo> productList) {
		for (int j = 0; j < productList.size(); j++) {
			System.out.println("\t" + j + ":" + productList.get(j).getName_template());
		}
	}
}
