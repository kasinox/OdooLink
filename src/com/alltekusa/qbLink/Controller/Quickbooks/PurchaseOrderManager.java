package com.alltekusa.qbLink.Controller.Quickbooks;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.aionsoft.qblink.model.quickbooks.PurchaseOrder;
import com.aionsoft.qblink.model.quickbooks.PurchaseOrderLine;
import com.alltekusa.qbLink.Controller.Database.ConnectionManager;

public class PurchaseOrderManager {
	
	private static Connection conn = ConnectionManager.getInstance().getConnection();
	

	public ArrayList<PurchaseOrder> getPurchaseOrderBySaleOrderNumber(String soNumber) {
		
		ArrayList <PurchaseOrder> poList = new ArrayList<>();
		// TODO Auto-generated method stub
				String sql = "SELECT RefNumber,customFieldOther1,vendorRefFullName,expectedDate,shipmethodRefFullName FROM purchaseOrder WHERE customfieldother1='" + soNumber + "'";
//			String sql = "SELECT * FROM purchaseOrderline WHERE customfieldother1='" + soNumber + "'";
			System.out.println(sql);
				ResultSet rs = null;
				try (
						// PreparedStatement stmt = conn.prepareStatement(sql);
						Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

				) {
					rs = stmt.executeQuery(sql);
					// rs.beforeFirst();
					int size = 0;
//					System.out.println(rs.next());
					if (rs.next()) {
						rs.last();
						size=rs.getRow();
						rs.beforeFirst();
						
						while(rs.next()){
							String refNumber = rs.getString("RefNumber");
							String soRefNumber =rs.getString("customFieldOther1");
							String vendorName = rs.getString("vendorRefFullName");
							String expectedDate = rs.getString("expectedDate");
							String shipmethodRefFullName = rs.getString("shipmethodRefFullName");
//							Date shipDate = rs.getDate("shipDate");
							PurchaseOrder po = new PurchaseOrder();
							po.setPoRefNumber(refNumber);
							po.setSoRefNumber(soRefNumber);
							po.setVendorName(vendorName);
							po.setExpectedDate(expectedDate);
							po.setShipmethodRefFullName(shipmethodRefFullName);
							System.out.println(po);
							
							PurchaseOrderLineManager poLineManager = new PurchaseOrderLineManager();
							ArrayList<PurchaseOrderLine> poLine = poLineManager.getPurchaseOrderLineByPurchaseOrderNumber(refNumber);
							po.setPoLines(poLine);
							
							// System.out.println("return true\t"+name);
							
							poList.add(po);
						}
						
						return poList;
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


	

}
