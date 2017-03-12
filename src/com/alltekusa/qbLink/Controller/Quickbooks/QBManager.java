package com.alltekusa.qbLink.Controller.Quickbooks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import com.alltekusa.qbLink.Controller.Database.ConnectionManager;
import com.alltekusa.qbLink.Helper.Processor;
import com.alltekusa.qbLink.Model.Base.Column;
import com.alltekusa.qbLink.Model.Base.Table;
import com.alltekusa.qbLink.Quickbooks.Model.Customer;

public class QBManager {

	private static Connection conn = ConnectionManager.getInstance().getConnection();
	public final static Logger LOGGER = Logger.getLogger(QBManager.class.getName());
	
	public Table getColumnsByTableName(String tableName) {

		// String tableName = table.getTableName();
		Table newTable = new Table(tableName);
		String sql = "select * from "+ tableName;
		ResultSet rs = null;

		try (PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);) {

			rs = stmt.executeQuery();
			//getcolumn name
			ResultSetMetaData rsmd = rs.getMetaData();

			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				String name = rsmd.getColumnName(i);
				String datatype = rsmd.getColumnTypeName(i);
				Column col = new Column(name, datatype);
				newTable.addColumn(col);
			}
			
//			rs.last();
//			System.out.println(rs.getRow() + " record returned");
//			rs.beforeFirst();
//			// System.out.println(rs.getRow());
//			System.out.println("\n\tIterating through columns\n\tReturning Values....");
//			while (rs.next()) {
//				Column bean = new Column();
//				bean.setColumnName(rs.getString("column_name"));
//				bean.setDataType(rs.getString("data_type"));
//				// System.out.println(bean.getColumnName()+"\t"+bean.getDataType());
//				newTable.addColumns(bean);
//			}

			return newTable;

		} catch (SQLException e) {
			System.out.println("error");
			System.err.println(e);
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			;
		}

	}
	
	public boolean insert(String sql){
		
		try (PreparedStatement stmt = conn.prepareStatement(sql);

		) {
			
			int affected = stmt.executeUpdate();
			if (affected == 1) {
				
				System.out.println("insert successful");
				return true;
			} else {
				System.err.print("no rows affected");
				return false;
			}

		} catch (SQLException e) {
			LOGGER.warning("Exception Occured\n"
					+e.getErrorCode()+"\n"
					+e.getMessage()+"\n"
					+e.getSQLState());
			return false;
		}

	}
	public static boolean update(String sql) throws SQLException {

		

		try (PreparedStatement stmt = conn.prepareStatement(sql);

		) {
			
			int affected = stmt.executeUpdate();

			if (affected == 1) {
				System.out.println("update successful");
			} else {
				System.out.println("update failed");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("error");
			System.err.println(e.getErrorCode());
			System.err.println(e.getMessage());
			System.err.println(e.getSQLState());

			return false;
		}
		return true;
	}
	public static boolean exist(String sql){
		
		ResultSet rs = null;
		try (
//				PreparedStatement stmt = conn.prepareStatement(sql);
				Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//				rs = stmt.executeQuery("SELECT * FROM customer WHERE name='jason'");	

		) {
			rs = stmt.executeQuery(sql);
//			rs.beforeFirst();
			if (rs.next()) {
//				String name = rs.getString("name");
//				System.out.println("return true\t"+name);
				return true;
			} else {
//				System.out.println("return false");
				return false;
			}

		} catch (SQLException e) {
			System.out.println("Exception Occured");
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
			return true;
		}
	}
	
	public static void main(String args[]){
		
		QBManager qb = new QBManager();
		
//		Table table = qb.getColumnsByTableName("Customer");
//		System.out.println(table);
//		
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String sql= "select * from customer where name='test'";
		System.out.println(qb.exist(sql));
	}
	
}
