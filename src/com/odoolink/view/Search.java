package com.odoolink.view;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odoolink.controller.managers.CustomerManagerOdoo;
import com.odoolink.controller.managers.ProductManagerOdoo;
import com.odoolink.controller.managers.SOManagerOdoo;

import com.odoolink.controller.managers.VendorManagerOdoo;
import com.odoolink.controller.managers.postgresql.PostgreSQLManager;
import com.odoolink.dbConnector.ConnectionManagerPostgreSQL;
import com.odoolink.dbConnector.DBType;

import com.odoolink.utility.LineParser;

/**
 * Servlet implementation class Search
 */
@WebServlet(description = "Servlet that passes the search parameter to the server", urlPatterns = { "/Search" })
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String USERNAME = "sid";
	private static final String PASSWORD = "password";
	private static final String P_CONN_STRING = "jdbc:postgresql://localhost:5432/odoo-php-test-1";

	/**
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see HttpServlet#HttpServlet()
	 */
	public Search() throws ClassNotFoundException {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Begin");
		ConnectionManagerPostgreSQL.getInstance().setDBType(DBType.PostgreSQL);
		String searchTable = "";
		String searchFieldName="";
		String searchFieldValue="";
		// System.out.println(request.getParameter("q"));
		if (!request.getParameter("table").equals(null)) {
			searchTable = request.getParameter("table");
		}
		if (!request.getParameter("field").equals(null)) {
			searchFieldName = request.getParameter("field");
		}
		if (!request.getParameter("value").equals(null)) {
			searchFieldValue = request.getParameter("value");
		}

		if (!searchTable.equals("")) {

//			PrintWriter writer = response.getWriter();
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(
				    response.getOutputStream(), StandardCharsets.UTF_8), true);
//			String encoding = "ISO-8859-15";
			 String encoding = "UTF-8";
			writer.println("<?xml version=\"1.0\" encoding=\"" + encoding + "\" ?>");
			writer.println("<query>");
			writer.println("<encoding>" + encoding + "</encoding>");
			writer.println("<searchTable>" + searchTable + "</searchTable>");
			writer.println("<searchField>"+searchFieldName+"</searchField>");
			writer.println("<searchValue>"+searchFieldValue+"</searchValue>");
			if (searchTable.equals("customer")) {
				printXMLCustomer(searchTable,searchFieldName, searchFieldValue,writer);
			} else if (searchTable.equals("items")) {
				printXMLItem(searchTable, searchFieldName, searchFieldValue,writer);
			} else if (searchTable.equals("sale_order")){
				printXMLSaleOrder(searchTable, searchFieldName, searchFieldValue,writer);
			} else if (searchTable.equals("product_supplierInfo")){				
				printXMLSupplierInfo(searchTable,searchFieldName, searchFieldValue,writer);
			} else if (searchTable.equals("vendors")){
				printXMLVendorInfo(searchTable,searchFieldName, searchFieldValue,writer);
			}
			writer.println("</query>");
		}

		// ConnectionManagerPostgreSQL.getInstance().close();
	}

	private void printXMLVendorInfo(String searchTable, String searchFieldName, String searchFieldValue, PrintWriter writer) {
		// TODO Auto-generated method stub
		VendorManagerOdoo Venman = new VendorManagerOdoo();
		Venman.printXML(writer, searchFieldName, searchFieldValue);
		
	}

	private void printXMLSupplierInfo(String searchTable, String searchFieldName, String searchFieldValue, PrintWriter writer) {
		// TODO Auto-generated method stub
		PostgreSQLManager pqman= new PostgreSQLManager();
		if(searchFieldName.equals("id")&&!searchFieldValue.equals("")){
			System.out.println("serachTable:"+searchTable+"\t id"+searchFieldName);
			int pt_id = Integer.parseInt(searchFieldValue);
			pqman.printXMLBySQLSupplier(writer, pt_id);
		}
		

		
		
	}



	private void printXMLSaleOrder(String serachTable, String searchFieldName, String searchFieldValue, PrintWriter writer) {
		// TODO Auto-generated method stub
		SOManagerOdoo SOman=new SOManagerOdoo();
		
		
		if(searchFieldName.equals("special")&&searchFieldValue.equals("today")){
			SOman.printXML(writer, searchFieldName,searchFieldValue);
		}else if (searchFieldName.equals("name")){
			SOman.printXML(writer, searchFieldName,searchFieldValue);
		}
		
	}

	private void printXMLItem(String searchTable, String searchFieldName, String searchFieldValue, PrintWriter writer) {
		ProductManagerOdoo ProdMan = new ProductManagerOdoo();
		
		ProdMan.printXML(writer, searchFieldName, searchFieldValue);

	}

	private void printXMLCustomer(String serachTable, String searchField, String searchFieldValue, PrintWriter writer) {

		CustomerManagerOdoo Cusman= new CustomerManagerOdoo();
//		Cusman.printMethodDefault(writer, searchField, searchFieldValue);
		Cusman.printXML(writer,searchField,searchFieldValue);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
