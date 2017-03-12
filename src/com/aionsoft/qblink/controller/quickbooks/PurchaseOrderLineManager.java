package com.aionsoft.qblink.controller.quickbooks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSeparatorUI;

import com.aionsoft.qblink.controller.database.ConnectionManager;
import com.aionsoft.qblink.model.quickbooks.PurchaseOrder;
import com.aionsoft.qblink.model.quickbooks.PurchaseOrderLine;
import com.aionsoft.qblink.model.quickbooks.SaleOrderLine;

public class PurchaseOrderLineManager {
	
	private static Connection conn = ConnectionManager.getInstance().getConnection();
	
//	public ArrayList<PurchaseOrderLine> getPurchaseOrderLineBySaleOrderNumber(String soNumber) {
//		
//		ArrayList<PurchaseOrderLine> poLineList = new ArrayList<>();
//
//		// TODO Auto-generated method stub
//		String sql = "SELECT * FROM purchaseOrderLine WHERE customfieldother1='" + soNumber + "'";
//		ResultSet rs = null;
//		try (
//				// PreparedStatement stmt = conn.prepareStatement(sql);
//				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//
//		) {
//			rs = stmt.executeQuery(sql);
//			// rs.beforeFirst();
//			int size = 0;
//			if (rs.next()) {
//				rs.last();
//				size=rs.getRow();
//				rs.beforeFirst();
//				
//				while(rs.next()){
//					String refNumber = rs.getString("RefNumber");
//					String customFieldOther1 =rs.getString("customFieldOther1");
//					String vendorName = rs.getString("vendorRefFullName");
//					String expectedDate = rs.getString("expectedDate");
//					String shipmethodRefFullName = rs.getString("shipmethodRefFullName");
//					String name = rs.getString("PurchaseOrderLineItemRefFullName");
//					String id = rs.getString("PurchaseOrderLineItemRefListId");
//					String qty = rs.getString("PurchaseOrderLineQuantity");
//					String rate = rs.getString("PurchaseOrderLineRate");
//					String amount = rs.getString("PurchaseOrderLineAmount");
//					boolean received = rs.getBoolean("IsFullyReceived");
//					
////					String status = rs.getString("CustomFieldPurchaseOrderLineStatus");
////					String txnId= rs.getString("purchaseOrderlineTxnLineID");
//					String status="1";
//					String txnId="1";
//					int row = rs.getRow();
//					PurchaseOrderLine POLine = new PurchaseOrderLine(refNumber,customFieldOther1,vendorName,expectedDate,
//							shipmethodRefFullName,name,id,qty,rate,amount, row,status,txnId,received,receivedQty);
//					System.out.println(POLine);
//					poLineList.add(POLine);
//
//					// System.out.println("return true\t"+name);
//				}
//				
//				return poLineList;
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
	
	public ArrayList<PurchaseOrderLine> getPurchaseOrderLineByPurchaseOrderNumber(String refNumber) {
		// TODO Auto-generated method stub
		
		ArrayList<PurchaseOrderLine> poLineList = new ArrayList<>();
		String sql = "SELECT "
				+ "customFieldOther1,"
				+ "vendorRefFullName,"
				+ "expectedDate,"
				+ "shipmethodRefFullName,"
				+ "PurchaseOrderLineItemRefFullName,"
				+ "PurchaseOrderLineItemRefListId,"
				+ "PurchaseOrderLineQuantity,"
				+ "PurchaseOrderLineRate,"
				+ "PurchaseOrderLineAmount,"
				+ "CustomFieldPurchaseOrderLineStatus,"
				+ "purchaseOrderlineTxnLineID,PurchaseOrderLineReceivedQuantity,"
				+ "isFullyReceived"
				+ " FROM purchaseOrderLine WHERE refNumber='" + refNumber + "'";
		ResultSet rs = null;
		try (
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
					int row=rs.getRow();
					String customFieldOther1 =rs.getString("customFieldOther1");
					String vendorName = rs.getString("vendorRefFullName");
					String expectedDate = rs.getString("expectedDate");
					String shipmethodRefFullName = rs.getString("shipmethodRefFullName");
					String name = rs.getString("PurchaseOrderLineItemRefFullName");
					String id = rs.getString("PurchaseOrderLineItemRefListId");
					int qty = rs.getInt("PurchaseOrderLineQuantity");
					String rate = rs.getString("PurchaseOrderLineRate");
					String amount = rs.getString("PurchaseOrderLineAmount");					
					String status = rs.getString("CustomFieldPurchaseOrderLineStatus");
					String txnId= rs.getString("purchaseOrderlineTxnLineID");
					int receivedQty = rs.getInt("PurchaseOrderLineReceivedQuantity");
					int isFullyReceived = rs.getInt("isFullyReceived");
//					System.out.println("received:"+isFullyReceived);
					boolean received = false;
					if(isFullyReceived==1){
						received=true;
					}
					if(qty==receivedQty){
						received=true;
					}
					
					PurchaseOrderLine POLine = new PurchaseOrderLine(refNumber,customFieldOther1,vendorName,expectedDate,
							shipmethodRefFullName,name,id,qty,rate,amount,row,status,txnId,received,receivedQty);
					System.out.println(POLine);
					if(!(rate==null)){
						poLineList.add(POLine);

					}
					// System.out.println("return true\t"+name);
				}
				
				return poLineList;
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
	public boolean updatePOLineStatus(PurchaseOrderLine poLine) throws SQLException{
		QBManager manager = new QBManager();
		String sql = "UPDATE  PurchaseOrderLine set customfieldPurchaseOrderLineStatus='"+poLine.getStatus()+"' where PurchaseOrderLineTxnLineID='"+poLine.getTxnId()+"'";
		
		System.out.println(sql);
		boolean update = manager.update(sql);
		return update;
	}
	
}
