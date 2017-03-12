package com.alltekusa.qbLink.Viewer.Console.cmd;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.w3c.dom.Document;

import com.aionsoft.qblink.controller.database.ConnectionManager;
import com.aionsoft.qblink.controller.quickbooks.CustomerManager;
import com.aionsoft.qblink.controller.quickbooks.ProductManager;
import com.aionsoft.qblink.helper.ExistInOdoo;
import com.aionsoft.qblink.helper.GetURI;
import com.aionsoft.qblink.helper.Processor;
import com.aionsoft.qblink.helper.ReadFileByLine;
import com.aionsoft.qblink.model.odoo.CategoryOdoo;
import com.aionsoft.qblink.model.odoo.CustomerOdoo;
import com.aionsoft.qblink.model.odoo.ProductOdoo;
import com.aionsoft.qblink.model.odoo.SaleOrderLineOdoo;
import com.aionsoft.qblink.model.odoo.SaleOrderOdoo;
import com.aionsoft.qblink.model.odoo.VendorOdoo;
import com.aionsoft.qblink.model.quickbooks.Customer;
import com.aionsoft.qblink.model.quickbooks.Product;
import com.aionsoft.qblink.model.quickbooks.ProductCategory;
import com.aionsoft.qblink.model.quickbooks.SaleOrder;
import com.aionsoft.qblink.model.quickbooks.Vendor;
import com.aionsoft.qblink.processor.Generator;
import com.aionsoft.qblink.processor.ProcessorCategoryQbooks;
import com.aionsoft.qblink.processor.ProcessorCustomerQbooks;
import com.aionsoft.qblink.processor.ProcessorProductQbooks;
import com.aionsoft.qblink.processor.ProcessorSaleOrderLineQbooks;
import com.aionsoft.qblink.processor.ProcessorSaleOrderQbooks;
import com.aionsoft.qblink.processor.ProcessorVendorQbooks;
import com.alltekusa.qbLink.Helper.Process.CustomerProcessor;
import com.alltekusa.qbLink.Helper.Process.SaleOrderProcessor;
import com.alltekusa.qbLink.Report.Model.ContractReport;

public class MainCommand {
	private static Connection conn = ConnectionManager.getInstance().getConnection();	
	public static void main(String[] args) throws SQLException{
//		String string = "84256";
//		"SELECT * from invoiceline where refnumber='90115'"
	
		String string = "84731";
		ArrayList<String> ids = new ArrayList<String>(Arrays.asList(string.split(",")));

//		ArrayList<String> ids = new ReadFileByLine("11-11-16-Invoice.txt").getData();
		

		System.out.println(ids);
//		System.exit(0);
		long lStartTime = System.currentTimeMillis();
		
//		boolean add = new MainCommand().addCustomer(ids);
		boolean add = new MainCommand().addBatchSalesOrder(string);
//		boolean add = new MainCommand().addSalesOrder(string);
//		boolean add = new MainCommand().createContractFromInvoice(ids);
	
		//some tasks
		long lEndTime = System.currentTimeMillis();

		long difference = lEndTime - lStartTime;

		System.out.println("Elapsed seconds: " + difference/1000);
		System.out.println("Elapsed Minutes: " + difference/1000/60);
//		
	}
	
	public MainCommand(){
		
	}
	
//	public boolean createContract(ArrayList<String> id) throws SQLException{
//		ArrayList<String> ids = new ArrayList<String>();
//		ids.add(id);
//
//		boolean add = new ContractReport().addInvoice(id)
//		return add;
//		
//	}
	public boolean createContractFromInvoice(ArrayList<String> id){
		boolean add = new ContractReport().addInvoice(id);
		
		return add;
		
		
	}
	public boolean addSalesOrder(String id) throws SQLException{
		ArrayList<String> ids = new ArrayList<String>();
		ids.add(id);
		boolean add = new QBAdd().addSaleOrder(ids);
		return add;
	}
	
	public boolean addCustomer(ArrayList<String> customerList) throws SQLException{
		boolean add = new QBAdd().addCustomer(customerList);	
		return add;
	}
	
	public boolean addBatchSalesOrder(String string) throws SQLException{
		ArrayList<String> ids = new ArrayList<String>(Arrays.asList(string.split(",")));

		boolean add = new QBAdd().addSaleOrder(ids);
		return true;	
	}
	
	public boolean addItems(ArrayList<String> ids) throws SQLException{
		
		
		boolean insert = new QBAdd().addItems(ids);
		return insert;
	}
	
	public boolean updateSalesOrder(ArrayList<String> ids) {
		
		boolean update = updateRecords(ids);
		return update;
	}
	

	public boolean updateSaleOrder(String id){
		ArrayList<String> ids = new ArrayList<>();
		ids.add(id);
		
		boolean update = updateRecords(ids);
		return update;
	}
	
	private boolean updateRecords(ArrayList<String> ids) {
		
		boolean update = updateRecords(ids);
		
		// TODO Auto-generated method stub
		return update;
	}
	
	public void RepairRecords() throws SQLException{
		
		ReadFileByLine newFile = new ReadFileByLine("saleorder_report.csv");
		ArrayList<String> test = newFile.getData();
		
		for(int i=0;i<test.size();i++){
			System.out.println(i+","+test.get(i));

		}
		
		ArrayList<String> listSO = new MainCommand().checkBatchSalesOrder(test);
		//get a list of sales orders in quickbase to add to Odoo
//		addBatchSalesOrder(listSO);
	}

	public ArrayList<String> checkBatchSalesOrder(ArrayList<String> ids) throws SQLException{
		ArrayList<String> misedSalesOrders = new ArrayList<>();
		ProcessorSaleOrderQbooks psq = new ProcessorSaleOrderQbooks();
		
		for(int i=0;i<ids.size();i++){
			boolean check = psq.existRefNumber(ids.get(i));
			System.out.println(i+","+ids.get(i)+","+check);
			if(check==false){
				misedSalesOrders.add(ids.get(i));
			}
		}
		
		for(int j=0;j<misedSalesOrders.size();j++){
			System.out.println(j+","+misedSalesOrders.get(j));
		}
		return misedSalesOrders;
	}

	

	private static void showPercentage(int size, int i) {
		double a = i;
		double b = size;
		
		double rate = a/b;
		System.out.printf("%.2f",rate*100);
		System.out.print("%");
		System.out.println("");
	}
	

}
