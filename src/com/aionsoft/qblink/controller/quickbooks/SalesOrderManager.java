package com.aionsoft.qblink.controller.quickbooks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.aionsoft.qblink.configuration.quickbooks.SalesRepCode;
import com.aionsoft.qblink.controller.database.ConnectionManager;
import com.aionsoft.qblink.model.quickbooks.SaleOrder;
import com.aionsoft.qblink.model.quickbooks.SaleOrderLine;
import com.alltekusa.qbLink.Model.Base.Row;

public class SalesOrderManager extends QBManager {
	private static Connection conn = ConnectionManager.getInstance().getConnection();	
	private String insertFieldNames;
	private String sqlMessage;
	public boolean insertSaleOrder(SaleOrder bean) {

		System.out.println("/t SaleOrderManager - InsertSaleOrder");
		String text="";//sqlfieldName
		String text2="";//values
		//insert field values
		int j=0;
		if(bean.getRows().get(0).getCells().size()>0){
//			System.out.println(x);
			Row row =bean.getRows().get(0);
			
			for(j=0;j<row.getCells().size();j++){
				boolean insert =row.getCells().get(j).getColumn().isInsert();
				if(insert){
					
					String name =row.getCells().get(j).getColumnName();
					String type=row.getCells().get(j).getColumn().getDataType();
					String value = row.getCells().get(j).getValue();
										
					
					
//					if(name.equals("IncomeAccountRefListID")||name.equals("COGSAccountRefListID")||name.equals("AssetAccountRefListID")){
//						String newValue = getRefListID(value);
//						value=newValue;
//					}
					if(name.equals("CustomerRefListID")){
						String newValue = getCustomerRefListID(value);
						value=newValue;
//						System.out.println("exit");
//						System.exit.(0);
					}
//					else if(name.equals("RefNumber")){
//						String newValue = getRefNumber(value);
//						value=newValue;
//					}
					
					System.out.println(name+","+value+","+type);
					text = text +"\""+name+"\", ";
					if(type.equals("String")){
						text2 = text2+"'"+value+"',";
					}else{
						text2 = text2+value+",";
					}
				}
				
				
			}
		}
		text = text+bean.getColumn(bean.getColumnSize()-1).getColumnName();

		text2 = text2+bean.getRows().get(0).getCells().get(j).getValue();
		System.out.println("\n"+text2);
		this.insertFieldNames=new String(text);
		this.sqlMessage=new String(text2);
		

		String sqltext = "INSERT into salesorder(" + insertFieldNames + ")"+"VALUES ("+sqlMessage+")";
		System.out.println(sqltext);

		
		
		boolean insert = new QBManager().insert(sqltext);

		return insert;
//		return false;
	}
	
	public boolean updateInvoiceAddress(SaleOrder bean){
		String RefNumber = bean.getRefNumber();
		String Addr1 = bean.getBillAddressAddr1();
		String Addr2 = bean.getBillAddressAddr2();
		String Addr3= bean.getBillAddressAddr3();
		String city = bean.getBillAddressCity();
		String zip = bean.getBillAddressPostalCode();
		
		String updateText = 
				"UPDATE  SalesOrder "
				+ "set BillAddressAddr1='"+Addr1+"', "
				+ " BillAddressAddr2='"+Addr2+"', "
				+ " BillAddressAddr3='"+Addr3+"' "
//				+ " BillAddressAddr4='"+city+"' "
//				+ " BillAddressAddr5='"+zip+"'  "
				+ "where RefNumber='"+RefNumber+"'";
		System.out.println(updateText);
		
		return new QBManager().insert(updateText);
		
		
	}
	public boolean updateShipAdress(SaleOrder bean){
		String RefNumber = bean.getRefNumber();
		String Addr1 = bean.getShipAddressAddr1();
		String Addr2 = bean.getShipAddressAddr2();
		String Addr3= bean.getShipAddressAddr3();
		String city = bean.getShipAddressCity();
		String zip = bean.getShipAddressPostalCode();
		
		String updateText = 
				"UPDATE  SalesOrder "
				+ "set ShipAddressAddr1='"+Addr1+"', "
				+ " ShipAddressAddr2='"+Addr2+"', "
				+ " ShipAddressAddr3='"+Addr3+"', "
//				+ " ShipAddressCity='"+city+"',  "
//				+ " ShipAddressPostalCode='"+zip+"'  "
				+ " ShipAddressCity='CHECK',  "
				+ " ShipAddressPostalCode='ADDRESS',  "
				+ " ShipAddressState='CONTRACT'"
				+ "where RefNumber='"+RefNumber+"'";
		System.out.println(updateText);
		
		return new QBManager().insert(updateText);
		
		
	}
	
	public boolean updatePONumber(SaleOrder bean){
		String RefNumber = bean.getRefNumber();
		String PONumber = bean.getPONumber();
		
		
		String updateText = "UPDATE  SalesOrder set PONumber='"+PONumber+"' where RefNumber='"+RefNumber+"'";
		System.out.println(updateText);
		
		return new QBManager().insert(updateText);
		
		
		
	}
	public boolean updateSalesRep(SaleOrder bean){
		
		String RefNumber = bean.getRefNumber();
		String SalesRepName = bean.getSalesRepRefListID();
		
		
		String updateText = "UPDATE  SalesOrder set SalesRepRefFullName='"+SalesRepName+"' where RefNumber='"+RefNumber+"'";
		System.out.println(updateText);
		
		return new QBManager().insert(updateText);
	}
	
	public boolean updatePaymentTerm(SaleOrder bean){
		
		String RefNumber = bean.getRefNumber();
		String paymentTerm = bean.getTermsRefFullName();
		
		String updateText = "UPDATE  SalesOrder set TermsRefFullName='"+paymentTerm+"' where RefNumber='"+RefNumber+"'";
		System.out.println(updateText);
		return new QBManager().insert(updateText);
	}
	public boolean updateIncoterm(SaleOrder bean){
		
		String RefNumber = bean.getRefNumber();
		String incoterm = bean.getFOB();
		
		String updateText = "UPDATE  SalesOrder set FOB='"+incoterm+"' where RefNumber='"+RefNumber+"'";
		System.out.println(updateText);
		return new QBManager().insert(updateText);
	}
	
	
	

//	private String getSalesRepName(String salesRepID) {
//		// TODO Auto-generated method stub
//		String sql = "SELECT ListID FROM SalesRep WHERE accountNumber='"+value+"'";
//		System.out.println(sql);
//		ResultSet rs = null;
//		try (
////				PreparedStatement stmt = conn.prepareStatement(sql);
//				Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
////				rs = stmt.executeQuery("SELECT * FROM customer WHERE name='jason'");	
//
//		) {
//			rs = stmt.executeQuery(sql);
////			rs.beforeFirst();
//			if (rs.next()) {
//				String name = rs.getString("ListID");
////				System.out.println("return true\t"+name);
//				return name;
//			} else {
//				System.out.println("return false");
//				return null;
//			}
//
//		} catch (SQLException e) {
//			System.out.println("Exception Occured");
//			System.out.println(e.getErrorCode());
//			System.out.println(e.getMessage());
//			System.out.println(e.getSQLState());
//			return null;
//		}
//	}

	private String getCustomerRefListID(String value) {
		// TODO Auto-generated method stub
		String sql = "SELECT ListID FROM customer WHERE accountNumber='"+value+"'";
		System.out.println(sql);
		ResultSet rs = null;
		try (
//				PreparedStatement stmt = conn.prepareStatement(sql);
				Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//				rs = stmt.executeQuery("SELECT * FROM customer WHERE name='jason'");	

		) {
			rs = stmt.executeQuery(sql);
//			rs.beforeFirst();
			if (rs.next()) {
				String name = rs.getString("ListID");
//				System.out.println("return true\t"+name);
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


	public boolean updateSaleOrder(SaleOrder so) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean existRefNumber(String id) {
		boolean existence = false;
		String sql1 = "SELECT * FROM salesOrder WHERE RefNumber='"+id+"'";
		ResultSet rs = null;
		existence = exist(sql1);
		return existence;
	}




	public SaleOrder getSaleOrderByRefNumber(String refNumber) {
		
		
		SaleOrder so = new SaleOrder();
		
		String sql = "SELECT "
				+ "refNumber,"
				+ "customerRefFullName,"
				+ "dueDate,"
				+ "Fob,"
				+ "shipdate,"
				+ "shipmethodRefFullName "
				+ "FROM SalesOrder WHERE RefNumber='" + refNumber + "'";
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
					
					String customerName = rs.getString("customerRefFullName");
					String dueDate = rs.getString("dueDate");
					String order_type = rs.getString("Fob");
					String shipDate = rs.getString("shipdate");
					String shipMethodRefFullName = rs.getString("shipmethodRefFullName");
					int row = rs.getRow();
					so = new SaleOrder(refNumber,customerName,dueDate,order_type,shipDate,shipMethodRefFullName);
					System.out.println(so);

					SalesOrderLineManager soLineManager = new SalesOrderLineManager();
					ArrayList<SaleOrderLine> soLineList = soLineManager.getSalesOrderLineByRefNumber(refNumber);
					so.setSaleOrderLine(soLineList);
				}
				
				return so;
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

public ArrayList<SaleOrder> getOpenSalesOrder(String sql) {
		ArrayList<SaleOrder> soList = new ArrayList<>();
		
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
				System.out.println("Number of Open Sales Order:"+size);
				
				while(rs.next()){
					
					String refNumber = rs.getString("refNumber");
					String customerName = rs.getString("customerRefFullName");
					String dueDate = rs.getString("dueDate");
					String order_type = rs.getString("Fob");
					String shipDate = rs.getString("shipdate");
					String shipMethodRefFullName = rs.getString("shipmethodRefFullName");
//					boolean isFullyInvoiced=rs.getBoolean("isFullyInvoiced");
					int row = rs.getRow();
					
					SaleOrder so = new SaleOrder(refNumber,customerName,dueDate,order_type,shipDate,shipMethodRefFullName);
					
					System.out.println(so);

					SalesOrderLineManager soLineManager = new SalesOrderLineManager();
					ArrayList<SaleOrderLine> soLineList = soLineManager.getSalesOrderLineByRefNumber(refNumber);
					so.setSaleOrderLine(soLineList);
					soList.add(so);
					
					System.out.println("Progress:"+row+"/"+size);
				}
				
				return soList;
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
public ArrayList<SaleOrder> getOpenPartsRMBSalesOrder() {
	ArrayList<SaleOrder> soList = new ArrayList<>();
	System.out.println("GET OPEN PARTS SALES ORDER");
	
	
	String sql = "SELECT "
			+ "refNumber,"
			+ "customerRefFullName,"
			+ "dueDate,"
			+ "Fob,"
			+ "shipdate,"
			+ "shipmethodRefFullName, "
			+ "isFullyInvoiced,"
			+ "isManuallyClosed "
			+ "FROM SalesOrder WHERE isFullyInvoiced=0 and isManuallyclosed=0 and fob='type_parts' "
			+ "and (termsRefFullName='RMB VAT' or termsRefFullName='RMB PREPAY')";
	
	soList=getOpenSalesOrder(sql);
	return soList;
}

public ArrayList<SaleOrder> getOpenAllPartsSalesOrder() {
	ArrayList<SaleOrder> soList = new ArrayList<>();
	System.out.println("GET ALL OPEN PARTS SALES ORDER");


	String sql = "SELECT "
			+ "refNumber,"
			+ "customerRefFullName,"
			+ "dueDate,"
			+ "Fob,"
			+ "shipdate,"
			+ "shipmethodRefFullName, "
			+ "isFullyInvoiced,"
			+ "isManuallyClosed "
			+ "FROM SalesOrder WHERE isFullyInvoiced=0 and isManuallyclosed=0 and fob='type_parts'";
	
	soList=getOpenSalesOrder(sql);
	return soList;
}


public ArrayList<SaleOrder> getOpenMACHINESalesOrder() {
	ArrayList<SaleOrder> soList = new ArrayList<>();
	System.out.println("GET OPEN MACHINE SALES ORDER");
	
	
	String sql = "SELECT "
			+ "refNumber,"
			+ "customerRefFullName,"
			+ "dueDate,"
			+ "Fob,"
			+ "shipdate,"
			+ "shipmethodRefFullName, "
			+ "isFullyInvoiced,"
			+ "isManuallyClosed "
			+ "FROM SalesOrder WHERE isFullyInvoiced=0 and isManuallyclosed=0 and fob='type_machine' and (shipmethodRefFullName is Null)";
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
			System.out.println("Number of Open Sales Order:"+size);
			
			while(rs.next()){
				
				String refNumber = rs.getString("refNumber");
				String customerName = rs.getString("customerRefFullName");
				String dueDate = rs.getString("dueDate");
				String order_type = rs.getString("Fob");
				String shipDate = rs.getString("shipdate");
				String shipMethodRefFullName = rs.getString("shipmethodRefFullName");
//				boolean isFullyInvoiced=rs.getBoolean("isFullyInvoiced");
				int row = rs.getRow();
				
				SaleOrder so = new SaleOrder(refNumber,customerName,dueDate,order_type,shipDate,shipMethodRefFullName);
				
				System.out.println(so);

				SalesOrderLineManager soLineManager = new SalesOrderLineManager();
				ArrayList<SaleOrderLine> soLineList = soLineManager.getSalesOrderLineByRefNumber(refNumber);
				so.setSaleOrderLine(soLineList);
				soList.add(so);
				
				System.out.println("Progress:"+row+"/"+size);
			}
			
			return soList;
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

public ArrayList<SaleOrder> getOpenRMBVATSalesOrder() {
	// TODO Auto-generated method stub
	return null;
}


}
