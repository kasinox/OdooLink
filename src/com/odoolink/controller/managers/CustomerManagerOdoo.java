package com.odoolink.controller.managers;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.odoolink.controller.managers.postgresql.PostgreSQLManager;
import com.odoolink.controller.managers.postgresql.model.Query;
import com.odoolink.dbConnector.ConnectionManagerPostgreSQL;

public class CustomerManagerOdoo extends ControllerManager {

	private static Connection conn = ConnectionManagerPostgreSQL.getInstance().getConnection();

	@Override
	public void printMethodDefault(PrintWriter writer, String searchField, String searchFieldValue) {
		// TODO Auto-generated method stub
		System.out.println("custMan processing");
		System.out.println("searchField"+searchField);
		System.out.println("SearchValue"+searchFieldValue);
		
		System.out.println(searchField.equals(new String("all")));

		String sqlf = "id, name, street,street2,city,zip,state_id,email,phone,mobile,fax,active";
		String sql = "SELECT " + sqlf + " FROM res_partner WHERE id=" + searchFieldValue;
		Query query = new Query(sql);

		PostgreSQLManager newPQ = new PostgreSQLManager();
		newPQ.printXMLBySQLContact(writer, query,"Customer");

	}

	@Override
	public void PrintMethodSuper(PrintWriter writer) {
		// TODO Auto-generated method stub
		System.out.println("superPrinter");
		String sqlf = "id, name";
		
		String sql = "SELECT " + sqlf + " FROM res_partner WHERE customer=true AND is_company = true";

		Query query = new Query(sql);

		PostgreSQLManager newPQ = new PostgreSQLManager();
		newPQ.printXMLBySQLContact(writer, query,"Customer");

	}
}
