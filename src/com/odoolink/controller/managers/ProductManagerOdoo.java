package com.odoolink.controller.managers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.odoolink.controller.managers.postgresql.PostgreSQLManager;
import com.odoolink.controller.managers.postgresql.model.Query;

import com.odoolink.dbConnector.ConnectionManagerPostgreSQL;






public class ProductManagerOdoo extends ControllerManager{

	private static Connection conn = ConnectionManagerPostgreSQL.getInstance()
			.getConnection();


	@Override
	public void printMethodDefault(PrintWriter writer, String searchField, String searchFieldValue) {
		// TODO Auto-generated method stub
		String tableName="sale_order";
		String talbeName="product_product";
		String sql1 = "SELECT pp.name_template,pp.id as pp_id,pp.product_tmpl_id as pt_id,pt.description_sale,pt.description_purchase,pt.list_price,pt.write_date,pt.type,pt.active,pt.categ_id "
				+ "FROM product_product as pp "
				+ "JOIN product_template as pt "
				+ "on pp.product_tmpl_id =pt.id WHERE pp.id="+searchFieldValue;
				
				
		System.out.println(sql1);			
		Query q = new Query(sql1);

		PostgreSQLManager newPQ = new PostgreSQLManager();	
		
		newPQ.printXMLBySQLProduct(writer, q);
	}

	@Override
	public void PrintMethodSuper(PrintWriter writer) {
		// TODO Auto-generated method stub
		System.out.println("superPrinter");
//		String tableName="sale_order";
		String talbeName="product_product";
		String sql1 = "SELECT pp.name_template,pp.id,pp.product_tmpl_id as pt_id,pt.description_sale,pt.description_purchase,pt.list_price,pt.write_date,pt. "
				+ "FROM product_product as pp "
				+ "JOIN product_template as pt "
				+ "on pp.product_tmpl_id =pt.id"
				+ "	ORDER BY pp.name_template, pt.description_sale LIMIT 20000";
		String sql2 = "";
		
		Query q = new Query(sql1);

		PostgreSQLManager newPQ = new PostgreSQLManager();	
		
		newPQ.printXMLBySQLProduct(writer, q);

	}

}
