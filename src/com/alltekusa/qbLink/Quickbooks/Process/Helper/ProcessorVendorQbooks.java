package com.alltekusa.qbLink.Quickbooks.Process.Helper;

import java.sql.SQLException;

import com.alltekusa.qbLink.Controller.Quickbooks.CustomerManager;
import com.alltekusa.qbLink.Controller.Quickbooks.VendorManager;
import com.alltekusa.qbLink.Odoo.Model.CustomerOdoo;
import com.alltekusa.qbLink.Odoo.Model.VendorOdoo;
import com.alltekusa.qbLink.Quickbooks.Model.Customer;
import com.alltekusa.qbLink.Quickbooks.Model.Vendor;

public class ProcessorVendorQbooks {

	
	public boolean insertVendor(Vendor vendor) throws SQLException {

		boolean insert = new VendorManager().insertVendor(vendor);
		if (insert) {
			return true;
		}
		System.out.println("Vendor Insert Failed");
		System.exit(0);
		return false;

	}
	
	public boolean existVendor(String id){
		
		boolean check = new VendorManager().existById(id);
		return  check;
	}
	public boolean existVendorName(String name){
		boolean check = new VendorManager().existByName(name);
		return  check;
	}

	public Vendor transformCustomer(VendorOdoo vendorOdoo) throws SQLException {

		Vendor vendor = new Transform().getVendor(vendorOdoo);
		return vendor;
	}
}
