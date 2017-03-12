package com.aionsoft.qblink.controller.quickbooks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.aionsoft.qblink.controller.database.ConnectionManager;
import com.aionsoft.qblink.helper.ReadFileByLine;

public class InvoiceManager extends QBManager {
	private static Connection conn = ConnectionManager.getInstance().getConnection();
	private String insertFieldNames;
	private String sqlMessage;
	
	private String getInvoice(String RefNumber) {
		// TODO Auto-generated method stub
				String sql = "SELECT * FROM invoiceLine WHERE RefNumber='" + RefNumber + "'";
				ResultSet rs = null;
				try (
						// PreparedStatement stmt = conn.prepareStatement(sql);
						Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				// rs = stmt.executeQuery("SELECT * FROM customer WHERE name='jason'");

				) {
					rs = stmt.executeQuery(sql);
					// rs.beforeFirst();
					int size = 0;
					if (rs.next()) {
						rs.last();
						size=rs.getRow();
						rs.beforeFirst();
						
						while(rs.next()){
							String refNumber = rs.getString("RefNumber");
							String name = rs.getString("InvoiceLineItemRefFullName");
							String id = rs.getString("InvoiceLineItemRefListID");
							String qty = rs.getString("InvoiceLineQuantity");
							String rate = rs.getString("InvoiceLineRate");
							String amount = rs.getString("InvoiceLineAmount");							
							System.out.println(refNumber+"\t"+rs.getRow()+"\t"+name+","+id+","+qty+","+rate+","+amount);
							// System.out.println("return true\t"+name);
						}
						
						return null;
					} else {
						System.out.println("return false");
						return null;
					}

				} catch (SQLException e) {
					System.out.println("Exception Occured");
					System.out.println(e.getErrorCode());
					System.out.println(e.getMessage());
					System.out.println(e.getSQLState());
					return null;
				}
	}
	
	public static void main(String args[]){
		ArrayList<String> ids = new ReadFileByLine("11-11-16-Invoice.txt").getData();
//		String id = new InvoiceManager().getInvoice("90115");
		String invoices = new InvoiceManager().getInvoices(ids);
//		System.out.println(id);
		
		
	}

	private String getInvoices(ArrayList<String> ids) {

		for(int i=0;i<ids.size();i++){
			String id = new InvoiceManager().getInvoice(ids.get(i));
			
		}
		return null;
	}
}
