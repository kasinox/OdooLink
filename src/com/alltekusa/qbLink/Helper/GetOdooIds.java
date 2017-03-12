package com.alltekusa.qbLink.Helper;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GetOdooIds {
	
	public GetOdooIds()
	{
		
	}

	
	public ArrayList<String> getProductIds(Document sale_order) {
		
		
		//retriving product ids
		ArrayList<String> productIds = new ArrayList<>();
		// System.out.println("2.1.1-Processor-checking SALE ORDER record exist
		// Odoo");
		
		
		NodeList nodeList_record_number = sale_order.getElementsByTagName("recordNumberSaleOrderLine");
		if (nodeList_record_number == null) {
			System.out.println("GetOdooIds - nodeList_record_number=null");
//			System.exit(0);
		}
		Node node_record = nodeList_record_number.item(0);

		if (node_record == null) {
			System.out.println("GetOdooIds - node_record=null");
//			System.exit(0);
		}
		int record_number = Integer.parseInt(node_record.getTextContent());

		for (int i = 0; i < record_number; i++) {
			NodeList nodeList = sale_order.getElementsByTagName("product_id_" + i);
			Node node = nodeList.item(0);
			String productId = node.getTextContent();
			if(productId.equals(null)||productId==null||productId.equals("null")){
				productId="168236";
			}
//			System.out.println("t"+productId);
			productIds.add(productId);
		}

		return productIds;
	}
}
