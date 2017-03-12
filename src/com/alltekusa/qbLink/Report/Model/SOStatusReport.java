package com.alltekusa.qbLink.Report.Model;

import java.sql.SQLException;
import java.util.ArrayList;

import com.aionsoft.qblink.controller.quickbooks.ProductManager;
import com.aionsoft.qblink.controller.quickbooks.PurchaseOrderLineManager;
import com.aionsoft.qblink.controller.quickbooks.PurchaseOrderManager;
import com.aionsoft.qblink.controller.quickbooks.QBManager;
import com.aionsoft.qblink.controller.quickbooks.SalesOrderLineManager;
import com.aionsoft.qblink.controller.quickbooks.SalesOrderManager;
import com.aionsoft.qblink.model.quickbooks.PurchaseOrder;
import com.aionsoft.qblink.model.quickbooks.PurchaseOrderLine;
import com.aionsoft.qblink.model.quickbooks.SaleOrder;
import com.aionsoft.qblink.model.quickbooks.SaleOrderLine;
import com.alltekusa.qbLink.Model.Query.SQLUpdateMessage;

public class SOStatusReport {
	static ArrayList<SaleOrder> missingPO = new ArrayList<>();
	
	
public SOStatusReport(){
		
		System.out.println("SO Status REPORT");
	}

	public static ArrayList<SaleOrderLine> checkOrderStatus(SaleOrder so, ArrayList<PurchaseOrder> poList) throws SQLException{
		ArrayList<SaleOrderLine> soLineList = so.getSaleOrderLine();
		ArrayList<PurchaseOrderLine> poLineList = new ArrayList<>();

//		System.out.println(soLineList);
		
		System.out.println(poList.size());
		for(int k=0;k<poList.size();k++){
			
			PurchaseOrder po = poList.get(k);
			
			poLineList.addAll(po.getPoLines());

		}

		
		for (int i=0;i<soLineList.size();i++){
			SaleOrderLine soLine = soLineList.get(i);
			
			String lineName = soLine.getName();
			String qty = soLine.getQty();
			String id  = soLine.getId();
			String type = new ProductManager().getTypeByProductID(id);
			
			if(soLine.getStatus()==null){
				soLine.setStatus(type);
			}
			
			System.out.println(soLine);
			if(!soLine.getStatus().equals("RECEIVED")||!soLine.getStatus().equals("ItemService")){
				for(int j=0;j<poLineList.size();j++){
					
					PurchaseOrderLine poLine = poLineList.get(j);
					String poLineRef = poLine.getRefNumber();
					String poLineName =poLine.getName();
					int poLineQty = poLine.getQty();
					String poLineId = poLine.getId();
					String poLineStatus=poLine.getStatus();
					
//					if(poLineStatus==null){
////						poLine.setStatus(new ProductManager().getTypeByProductID(poLineId));
//					}
//					if()
					if(poLineId.equals(id)) {
//						poLine.setStatus(soLine.getRefNumber());
						soLine.setPONumber(poLineRef);	
						String poStatus = poLine.getShipmethodRefFullName();
						
						if(poStatus.equals("CONFIRMED")){
							soLine.setStatus("PO CONFIRMED");
//							
							if(poStatus.equals("PENDING CONFIRM")){
								soLine.setStatus("PO PENDING CONFIRM");

							}
//							soLine.sets
						}
						
					}
					
					
				}
			}
			
			
		}
		SalesOrderLineManager soLineManager = new SalesOrderLineManager();
//		PurchaseOrderLineManager poLineManager = new PurchaseOrderLineManager();
		for(int i=0;i<soLineList.size();i++){
			System.out.println(soLineList.get(i));
			String status = soLineList.get(i).getStatus();
			if(!status.equals("ItemService")||!(status==null)||!status.equals("ItemOtherCharge")||!status.equals("received")){
				soLineManager.updateSOLineStatus(soLineList.get(i));
				
			}
			

		}
		return soLineList;
	}
	
	public static ArrayList<SQLUpdateMessage> checkOrderStatus2(SaleOrder so, ArrayList<PurchaseOrder> poList)
			throws SQLException {
		ArrayList<SaleOrderLine> soLineList = so.getSaleOrderLine();
		ArrayList<PurchaseOrderLine> poLineList = new ArrayList<>();

		// System.out.println(soLineList);

		System.out.println(poList.size());
		ArrayList<SQLUpdateMessage> messageList = new ArrayList<>();
		for (int k = 0; k < poList.size(); k++) {

			PurchaseOrder po = poList.get(k);

			String poNumber = po.getPoRefNumber();

			poLineList = po.getPoLines();
			SQLUpdateMessage received = new SQLUpdateMessage("received");
			SQLUpdateMessage message = new SQLUpdateMessage(poNumber);
			ArrayList<String> conditions = new ArrayList<>();
			ArrayList<String> receivedConditions = new ArrayList<>();
			for (int i = 0; i < soLineList.size(); i++) {
				SaleOrderLine soLine = soLineList.get(i);

				String lineName = soLine.getName();
				String qty = soLine.getQty();
				String id = soLine.getId();
				String type = new ProductManager().getTypeByProductID(id);
				String soLineTxnId = soLine.getTxnID();
				soLine.setStatus(type);

				for (int j = 0; j < poLineList.size(); j++) {
					PurchaseOrderLine poLine = poLineList.get(j);
					String poLineRef = poLine.getRefNumber();
					String poLineName = poLine.getName();
					int poLineQty = poLine.getQty();
					String poLineId = poLine.getId();
					boolean poLineReceived = poLine.isReceived();
					String poLineStatus = poLine.getStatus();
					
					
					if (poLineId.equals(id)) {
						
						
						if(poLineReceived==true){
//							System.out.println(soLineTxnId);
							soLine.setStatus("Received");
							receivedConditions.add(soLineTxnId);
//							System.out.println(received);
						}else{
							poLine.setStatus(soLineTxnId);
							conditions.add(soLineTxnId);
							soLine.setStatus(poLineRef);
						}
					}

				}
				
			}
//			System.out.println(received);
			if(!(receivedConditions.size()==0)){
				received.setConditions(receivedConditions);
//				System.out.println("isNull");
				messageList.add(received);

			}
			if(!(conditions.size()==0)){
				message.setConditions(conditions);
//				System.out.println("condisNull");
				messageList.add(message);
			}
//			messageList.add(received);

			System.out.println(messageList);
//			System.out.println(received);
			System.out.println("---------------");
		}

		
		return messageList;
	}

	public static ArrayList<SaleOrder> listOpenPartsRMBSalesOrder(){
		
//		SELECT refNumber,customerRefFullName,dueDate,fob,shipdate,shipmethodRefFullName,isFullyInvoiced,isManuallyClosed FROM salesorderline where isFullyInvoiced=0 and fob='type_parts' and isManuallyclosed=0
		SalesOrderManager soManager = new SalesOrderManager();
		ArrayList<SaleOrder> soList = soManager.getOpenPartsRMBSalesOrder();		
		return soList;
		
	}
	
	public static ArrayList<SaleOrder> listAllPartsSalesOrder(){
		SalesOrderManager soManager = new SalesOrderManager();
		ArrayList<SaleOrder> soList = soManager.getOpenAllPartsSalesOrder();
		return soList;
	}
	
	public static void main(String arg[]) throws SQLException{
		
		String soNumber = "84708";
		UpdateOpenSalesOrderBySONumber(soNumber);
		
		
		
	}
	public static void UpdateOpenSalesOrderBySONumber(String number) throws SQLException{
		
		SOStatusReport report = new SOStatusReport();
		
		String refNumber1 = number;
		
		long lStartTime = System.currentTimeMillis();
		SalesOrderManager soManager = new SalesOrderManager();
		SaleOrder so1 = soManager.getSaleOrderByRefNumber(refNumber1);
	
		ArrayList<SaleOrder> openSaleorders = new ArrayList<>();
		ArrayList<SQLUpdateMessage> messageList = new ArrayList<>();
		openSaleorders.add(so1);
		
		for(int i=0;i<openSaleorders.size();i++){
			SaleOrder so = openSaleorders.get(i);
			
			String saleOrder = i+"\t"+so.toString();
			String refNumber = so.getRefNumber();
			PurchaseOrderManager poManager = new PurchaseOrderManager();
//			
			System.out.println(saleOrder);
			System.out.println("\n--------------------------------------------------");
//			
			ArrayList<PurchaseOrder> poList = poManager.getPurchaseOrderBySaleOrderNumber(refNumber);
//			
			System.out.println(poList);
			System.out.println("Status Update");
			if(!(poList==null)){
				ArrayList<SaleOrderLine> soLineList = checkOrderStatus(so,poList); 
//				messageList = checkOrderStatus2(so,poList);
//				System.out.println("size"+messageList.size());
//				for(int j=0;j<messageList.size();j++){
//
//					
//					String sql = messageList.get(j).formUpdateMessageSaleOrderLineStatus();
//					QBManager manager = new QBManager();
//					manager.update(sql);
//					System.out.println(sql);
//				}
			
			}else{
				missingPO.add(so);
			}
		}
//		System.out.println(messageList.size());
//		System.out.println(received);
		
		
		
		long lEndTime = System.currentTimeMillis();

		long difference = lEndTime - lStartTime;

		System.out.println("Elapsed seconds: " + difference/1000);
		System.out.println("Elapsed Minutes: " + difference/1000/60);
		
		

		
		
	}
	
	public void UpdateOpenSalesOrdersBatch() throws SQLException{

		SOStatusReport report = new SOStatusReport();


		long lStartTime = System.currentTimeMillis();


		ArrayList<SaleOrder> openSaleorders = listOpenPartsRMBSalesOrder();

		ArrayList<SQLUpdateMessage> messageList = new ArrayList<>();
		for (int i = 0; i < openSaleorders.size(); i++) {
			SaleOrder so = openSaleorders.get(i);

			String saleOrder = i + "\t" + so.toString();
			String refNumber = so.getRefNumber();
			PurchaseOrderManager poManager = new PurchaseOrderManager();
			//
			System.out.println(saleOrder);
			System.out.println("\n--------------------------------------------------");
			//
			ArrayList<PurchaseOrder> poList = poManager.getPurchaseOrderBySaleOrderNumber(refNumber);
			//
			System.out.println(poList);
			System.out.println("Status Update");
			if (!(poList == null)) {
				ArrayList<SaleOrderLine> soLineList = checkOrderStatus(so, poList);
				// messageList = checkOrderStatus2(so,poList);
				// System.out.println("size"+messageList.size());
				// for(int j=0;j<messageList.size();j++){
				//
				//
				// String sql =
				// messageList.get(j).formUpdateMessageSaleOrderLineStatus();
				// QBManager manager = new QBManager();
				// manager.update(sql);
				// System.out.println(sql);
				// }

			} else {
				missingPO.add(so);
			}
		}
		// System.out.println(messageList.size());
		// System.out.println(received);

		long lEndTime = System.currentTimeMillis();

		long difference = lEndTime - lStartTime;

		System.out.println("Elapsed seconds: " + difference / 1000);
		System.out.println("Elapsed Minutes: " + difference / 1000 / 60);

	}

}
