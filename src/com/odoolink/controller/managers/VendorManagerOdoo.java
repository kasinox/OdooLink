package com.odoolink.controller.managers;

import java.io.PrintWriter;
import java.sql.Connection;

import com.odoolink.controller.managers.postgresql.PostgreSQLManager;
import com.odoolink.controller.managers.postgresql.model.Query;
import com.odoolink.dbConnector.ConnectionManagerPostgreSQL;

public class VendorManagerOdoo extends ControllerManager {

	private static Connection conn = ConnectionManagerPostgreSQL.getInstance()
			.getConnection();
//	public void printVendorXML(PrintWriter writer){
//
////		String sql = "SELECT * FROM res_partner WHERE customer=false AND is_company = true ";
//		String sqlf="id, name, street,street2,city,zip,state_id";
//		String sql = "SELECT "+sqlf+" FROM res_partner WHERE supplier=true AND is_company = true ";
//		Query query = new Query(sql);
//		
//		PostgreSQLManager newPQ = new PostgreSQLManager();		
////		newPQ.printXMLByTableName(writer, tableName);
//		newPQ.printXMLBySQLContact(writer, query);
//	}
//	
//	public void printVendorXML(PrintWriter writer,int id){
//
//		String sql = "SELECT * FROM res_partner WHERE customer=false AND is_company = true ";
//		Query query = new Query(sql);
//		
//		PostgreSQLManager newPQ = new PostgreSQLManager();		
////		newPQ.printXMLByTableName(writer, tableName);
//		newPQ.printXMLBySQL(writer, query);
//	}
	@Override
	public void printMethodDefault(PrintWriter writer, String searchField, String searchFieldValue) {
		// TODO Auto-generated method stub
		System.out.println("custMan processing");
		System.out.println("searchField"+searchField);
		System.out.println("SearchValue"+searchFieldValue);
		
		System.out.println(searchField.equals(new String("all")));

		String sqlf = "id, name, street,street2,city,zip,state_id";
		String sql = "SELECT " + sqlf + " FROM res_partner WHERE id=" + searchFieldValue;
		Query query = new Query(sql);

		PostgreSQLManager newPQ = new PostgreSQLManager();
		newPQ.printXMLBySQLContact(writer, query,"Supplier");

	}

	@Override
	public void PrintMethodSuper(PrintWriter writer) {
		// TODO Auto-generated method stub
		System.out.println("superPrinter");
		String sqlf = "id, name";
		
		String sql = "SELECT " + sqlf + " FROM res_partner WHERE customer=false AND is_company = true";

		Query query = new Query(sql);

		PostgreSQLManager newPQ = new PostgreSQLManager();
		newPQ.printXMLBySQLContact(writer, query,"Supplier");

	}
	
}
