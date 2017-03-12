package com.odoolink.controller.managers;

import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.odoolink.controller.managers.postgresql.PostgreSQLManager;
import com.odoolink.controller.managers.postgresql.model.Query;
import com.odoolink.controller.model.Cell;
import com.odoolink.controller.model.Column;
import com.odoolink.controller.model.Row;
import com.odoolink.controller.model.Table;

import com.odoolink.dbConnector.ConnectionManagerPostgreSQL;
import com.odoolink.utility.LineParser;

public class SOManagerOdoo extends ControllerManager {

	//select * from template where name='Sales Order with Rep'
	//note the name has to be an exact match
	
	//check if sales order number exist in system
	
	//manually update sales order into quickbooks
	
	//SELECT * from sale_order
	
	private static Connection conn = ConnectionManagerPostgreSQL.getInstance()
			.getConnection();
	
	@Override
	public void printMethodDefault(PrintWriter writer, String searchField, String searchFieldValue) {
		// TODO Auto-generated method stub
		System.out.println("custMan processing");
		System.out.println("searchField"+searchField);
		System.out.println("SearchValue"+searchFieldValue);
		
//		System.out.println(searchField.equals(new String("all")));

		String sql = "select name as so_number,origin,"
				+ "amount_tax,payment_term,state,pricelist_id,"
				+ "write_date,user_id,partner_id,partner_invoice_id,partner_shipping_id,amount_total,"
				+ "incoterm,id from sale_order WHERE id="+searchFieldValue;
		Query query = new Query(sql);

		PostgreSQLManager newPQ = new PostgreSQLManager();
		newPQ.printXMLBySQLSaleOrder(writer, query);

	}
	@Override
	public void printMethodName(PrintWriter writer, String searchField, String searchFieldValue) {
		// TODO Auto-generated method stub
		System.out.println("custMan processingMethodName");
		System.out.println("searchField"+searchField);
		System.out.println("SearchValue"+searchFieldValue);
		
//		System.out.println(searchField.equals(new String("all")));

		String sql = "select name as so_number,origin,"
				+ "amount_tax,payment_term,state,pricelist_id,x_contract,x_contract_signed,x_type,x_special_request,"
				+ "write_date,user_id,partner_id,partner_invoice_id,amount_total,"
				+ "partner_shipping_id,incoterm,id from sale_order WHERE name='"+searchFieldValue+"'";
//				+ "partner_shipping_id,incoterm from sale_order WHERE name='81409'";
		Query query = new Query(sql);

		PostgreSQLManager newPQ = new PostgreSQLManager();
		newPQ.printXMLBySQLSaleOrder(writer, query);

	}

	@Override
	public void PrintMethodSuper(PrintWriter writer) {
		// TODO Auto-generated method stub
		System.out.println("superPrinter");
		String sqlf = "id, name";
		
		String sql = "select name,origin,"
				+ "amount_tax,payment_term,state,pricelist_id,"
				+ "write_date,user_id,partner_invoice_id,amount_total,"
				+ "partner_shipping_id,incoterm,id from sale_order LIMIT 10000";
		Query query = new Query(sql);

		PostgreSQLManager newPQ = new PostgreSQLManager();
		newPQ.printXMLBySQLSaleOrder(writer, query);

	}
	@Override
	public void PrintMethodSpecial(PrintWriter writer){
		
		System.out.println("printSaleOrderToday");
		
		String sql = "SELECT name from sale_order "
				+ "WHERE date_order<(current_date+1) "
				+ "AND date_order>(current_date-15) "
				+ "AND (state<>'draft' "
				+ "AND state<>'sent'"
				+ "AND state<>'cancel')";
				
		Query query = new Query(sql);

		PostgreSQLManager newPQ = new PostgreSQLManager();
		newPQ.printXMLBySQLSaleOrder(writer, query);
	}
//	public void printSaleOrderXML(PrintWriter writer){
//		String tableName="sale_order";
//		String sql = "select id,name,origin,client_order_ref,"
//				+ "amount_tax,payment_term,note,state,pricelist_id,"
//				+ "write_date,user_id,partner_invoice_id,amount_total,"
//				+ "partner_shipping_id,incoterm from sale_order";
//		PostgreSQLManager newPQ = new PostgreSQLManager();		
////		newPQ.printXMLByTableName(writer, tableName);
//		Query q = new Query(sql);
//		
//		newPQ.printXMLBySQLSaleOrder(writer, q);
//	}
	public static void main(String args[]){
		
	}
}
