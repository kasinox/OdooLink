package com.alltekusa.qbLink.Controller.Quickbooks;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.w3c.dom.Document;

import com.alltekusa.qbLink.Controller.Database.ConnectionManager;
import com.alltekusa.qbLink.Helper.DateConvertor;
import com.alltekusa.qbLink.Helper.Processor;
import com.alltekusa.qbLink.Model.Base.Row;
import com.alltekusa.qbLink.Quickbooks.Model.PurchaseOrderLine;
import com.alltekusa.qbLink.Quickbooks.Model.SaleOrderLine;

public class SalesOrderLineManager {
	private static Connection conn = ConnectionManager.getInstance().getConnection();
	private String insertFieldNames;
	private String sqlMessage;

	public boolean insertSaleOrderLine(SaleOrderLine bean) throws SQLException {
		System.out.println("SaleOrderLineManager-InsertSaleOrderLine\n");
		
		boolean success=false;
		ArrayList<String> sqlstatements = new ArrayList<>();
		// insert field values
		
		int j = 0;

		System.out.println("\tNumber of Cells:"+bean.getRows().get(0).getCells().size());
		
		
		if (bean.getRows().get(0).getCells().size() > 0) {
			// System.out.println(x);

			System.out.println("\tNumber of Rows:"+bean.getRowSize());

			for (int k = 0; k < bean.getRowSize(); k++) {
				Row row = bean.getRows().get(k);
				String text = "";// sqlfieldName
				String text2 = "";// values
				System.out.println("\tNumber of Cells:"+row.getCells().size());
					for (j = 0; j < row.getCells().size()-1; j++) {
						
						boolean insert = row.getCells().get(j).getColumn().isInsert();
						if (insert) {
							String name = row.getCells().get(j).getColumnName();
							String type = row.getCells().get(j).getColumn().getDataType();
							String value = row.getCells().get(j).getValue();
							System.out.println("-->"+name + "," + value + "," + type);
	
							if (name.equals("SalesOrderLineItemRefListID")) {
								System.out.println("Product ID:"+value);
								value = getProductID(value);
								System.out.println("Product ID:"+value);
							}
							if (name.equals("CustomerRefListID")) {
								System.out.println("Customer ID:"+value);
								value = getCustomerID(value);
								System.out.println("Customer ID:"+value);
							}
							if(value.contains("бу")){
								value=value.replace("бу", "DEG");
							}
							if(value.contains("'")){
								value=value.replace("'", "''");
								
							}
							if(value.contains("....")){
								value=value.replace("....", ".\n");
							}
							if(value.contains("...")){
								value=value.replace("....", "\n");
							}
	
							text = text + "\"" + name + "\", ";
							if (type.equals("String")) {
								text2 = text2 + "'" + value + "',";
							} else {
								text2 = text2 + value + ",";
							}
						}
	
					}
				text = text + "\""+bean.getColumn(bean.getColumnSize() - 1).getColumnName()+ "\"";

				text2 = text2 + "'"+bean.getRows().get(0).getCells().get(j).getValue()+ "'";
				System.out.println("\n" + text2);
				this.insertFieldNames = new String(text);
				this.sqlMessage = new String(text2);
				String sqltext = "";
				if (k == bean.getRowSize() - 1) {
					sqltext = "INSERT into salesorderline(" + insertFieldNames + ",\"FQSaveToCache\")" + "VALUES ("
							+ sqlMessage + ",0)";
				} else {
					sqltext = "INSERT into salesorderline(" + insertFieldNames + ",\"FQSaveToCache\")" + "VALUES ("
							+ sqlMessage + ",1)";

				}
				System.out.println(sqltext + "\n");
				sqlstatements.add(sqltext);
			}

			for (int i = 0; i < sqlstatements.size(); i++) {
				String text = sqlstatements.get(i);
				System.out.println(i + "\n" + text);
				success = new QBManager().insert(text);
			}

//			System.exit(0);
		}

		// 

		 return success;
//		return false;
	}

	private String getCustomerID(String value) {
		// TODO Auto-generated method stub
				String sql = "SELECT ListID FROM customer WHERE accountNumber='"+value+"'";
				System.out.println(sql);
				ResultSet rs = null;
				try (
//						PreparedStatement stmt = conn.prepareStatement(sql);
						Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//						rs = stmt.executeQuery("SELECT * FROM customer WHERE name='jason'");	

				) {
					rs = stmt.executeQuery(sql);
//					rs.beforeFirst();
					if (rs.next()) {
						String name = rs.getString("ListID");
//						System.out.println("return true\t"+name);
						return name;
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

	private String getProductID(String value) {
		// TODO Auto-generated method stub
				String sql = "SELECT ListID FROM item WHERE customfieldodoo_id='" + value + "'";
				ResultSet rs = null;
				try (
						// PreparedStatement stmt = conn.prepareStatement(sql);
						Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				// rs = stmt.executeQuery("SELECT * FROM customer WHERE name='jason'");

				) {
					rs = stmt.executeQuery(sql);
					// rs.beforeFirst();
					if (rs.next()) {
						String name = rs.getString("ListID");
						// System.out.println("return true\t"+name);
						return name;
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
	
	public ArrayList<SaleOrderLine> getSalesOrderLineByRefNumber(String soNumber) {
		// TODO Auto-generated method stub
		ArrayList<SaleOrderLine> soLineList = new ArrayList<>();
		
		String sql = "SELECT "
				+ "refNumber,"
				+ "SalesOrderLineItemRefFullName,"
				+ "SalesOrderLineItemRefListId,"
				+ "SalesOrderLineQuantity, "
				+ "SalesOrderLineRate,"
				+ "SalesOrderLineAmount,"
				+ "CustomfieldSalesOrderLineStatus, "
				+ "SalesOrderLineTxnLineID,"
				+ "CustomfieldSalesOrderLinePONumber, "
				+ "customFieldSalesorderlineShipdate "
				+ "FROM SalesOrderLine WHERE RefNumber='" + soNumber + "'";
		ResultSet rs = null;
		
		
		try (
				// PreparedStatement stmt = conn.prepareStatement(sql);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

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
					String txnID = rs.getString("SalesOrderLineTxnLineID");
					String name = rs.getString("SalesOrderLineItemRefFullName");
					String id = rs.getString("SalesOrderLineItemRefListId");
					String qty = rs.getString("SalesOrderLineQuantity");
					String rate = rs.getString("SalesOrderLineRate");
					String amount = rs.getString("SalesOrderLineAmount");
					String status = rs.getString("CustomfieldSalesOrderLineStatus");
					String poNumber = rs.getString("CustomfieldSalesOrderLinePONumber");
					String ShipDate = rs.getString("customFieldSalesorderlineShipdate");
//					System.out.println(ShipDate);
					int row = rs.getRow();
					SaleOrderLine soLine = new SaleOrderLine(refNumber,name,id,qty,rate,amount,row,status,txnID);
					soLine.setPONumber(poNumber);
					if(ShipDate!=null){
						String newShipDate = new DateConvertor(ShipDate).getNewDateString();
						Date date = java.sql.Date.valueOf(newShipDate);
//						System.out.println("shipdate:"+date);
						soLine.setShipdate(date);
					}
					System.out.println(soLine);
					soLineList.add(soLine);
					// System.out.println("return true\t"+name);
				}
				
				return soLineList;
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
	
	public boolean updateSOLineStatus(SaleOrderLine soLine) throws SQLException{
		QBManager manager = new QBManager();
		String sql = "UPDATE  SalesOrderLine set customfieldSalesOrderLinePONumber ='"+soLine.getPONumber()+"', customfieldSalesOrderLineStatus='"+soLine.getStatus()+"' where salesOrderLineTxnLineID='"+soLine.getTxnID()+"'";
		boolean update = manager.update(sql);
		return update;
	}


}
