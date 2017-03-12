package com.aionsoft.qblink.processor;
//
//import java.util.ArrayList;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//import com.alltekusa.qbLink.Model.Base.Cell;
//import com.alltekusa.qbLink.Model.Base.Column;
//import com.alltekusa.qbLink.Model.Base.Row;
//import com.alltekusa.qbLink.Model.Base.Table;
//import com.alltekusa.qbLink.Model.Quickbooks.Customer;
//import com.alltekusa.qbLink.Model.Quickbooks.Product;
//import com.alltekusa.qbLink.Model.Quickbooks.SaleOrder;
//import com.alltekusa.qbLink.Model.Quickbooks.SaleOrderLine;
//
public class Generator {
//	
////	//not used.
////
////	ArrayList<Table> tables = new ArrayList<>();
////
//	public Generator() {
//
//	}
//
//	
//	
//	public Product parseNodeProduct(Document doc) {
//		System.out.println("Generator-ParseNodeProduct");
//
//
//		Product product = new Product();
//		Row row = new Row();
//		for (int i = 0; i < product.getColumnSize(); i++) {
//
//			Column col = product.getColumn(i);
//			if (col.isInsert() == true) {
//				String field = col.getField();
//				
////				System.out.println("working:"+field);
//				NodeList nodeList = doc.getElementsByTagName(field);
//				Node node = nodeList.item(0);
//				String value = node.getTextContent();
////				System.out.println(node.getNodeName()+value);
//
//				Cell cel = new Cell();
//				if (node.getNodeName().equals("product_name")) {
//
//					if (value.equals("null")) {
////						System.out.println("tada");
//						NodeList nodeList2 = doc.getElementsByTagName("name_template");
//						Node node2 = nodeList2.item(0);
//						value = node2.getTextContent();
//						cel = new Cell(value, col);
//						// System.out.println(node2.getNodeName()+"\t"+value);
//						// System.out.println("\t\t"+cel);
//					}else{
//						cel = new Cell(value, col);
//					}
//					
//				}
//				else if(node.getNodeName().equals("active")){
//					if(node.getTextContent().equals("t")){
//						cel=new Cell("TRUE",col);
//					}else{
//						cel=new Cell("FALSE",col); 
//					}
//					
//				}else if(value.equals("null")){
//					value="";
//					cel = new Cell(node.getTextContent(), col);
//				}
//				else {
//				
//					cel = new Cell(node.getTextContent(), col);
//				}
////				System.out.println(node.getNodeName()+value);
//				
//				row.addCell(cel);
//
//				// System.out.println(node.getNodeName());
//				// System.out.println("\t\t"+cel);
//			}
//
//		}
//		product.addRows(row);
//
//		return product;
//	}
//
//	public SaleOrder parseNodeSaleOrder(Document doc) {
//		SaleOrder bean = new SaleOrder();
//		Row row = new Row();
//		for (int i = 0; i <bean.getColumnSize(); i++) {
//
//			Column col = bean.getColumn(i);
//			if (col.isInsert() == true) {
//				String field = col.getField();
//				//
//				NodeList nodeList = doc.getElementsByTagName(field);
//				Node node = nodeList.item(0);
//				Cell cel = new Cell();
//
//				
//				cel = new Cell(node.getTextContent(), col);
//				
//				row.addCell(cel);
//
//			}
//
//		}
//		bean.addRows(row);
//		return bean;
//
//	}
//	public SaleOrderLine parseNodeSaleOrderLine(Document doc) {
//		SaleOrderLine bean = new SaleOrderLine();
//		
//		
//		NodeList nodeList_record_number =doc.getElementsByTagName("recordNumberSaleOrderLine");
//		Node node_record = nodeList_record_number.item(0);
//		int record_number = Integer.parseInt(node_record.getTextContent());
////		System.out.println(record_number);
//		
//		for(int k=0;k<record_number;k++){
//			Row row = new Row();
//		for (int i = 0; i <bean.getColumnSize(); i++) {
//
//			Column col = bean.getColumn(i);
//			if (col.isInsert() == true) {
//				String field = col.getField();
////				System.out.println(field);
////				System.out.println(field+"_"+k);
//				//
//				NodeList nodeList;
//				if(field.equals("so_number")){
//					nodeList = doc.getElementsByTagName(field);
//				}else {
//					nodeList = doc.getElementsByTagName(field+"_"+k);
//				}
//				
//				Node node = nodeList.item(0);
//				Cell cel = new Cell();
//
//				
//				cel = new Cell(node.getTextContent(), col);
//				
//				row.addCell(cel);
//
//			}
//
//		}
//		bean.addRows(row);
//		}
//		return bean;
//
//	}
//
//	public Customer parseNodeCustomer(Document doc) {
//		// TODO Auto-generated method stub
//		
//			Customer customer = new Customer();
//			Row row = new Row();
//			for (int i = 0; i < customer.getColumnSize(); i++) {
//
//				Column col = customer.getColumn(i);
//				if (col.isInsert() == true) {
//					String field = col.getField();
//					//
//					NodeList nodeList = doc.getElementsByTagName(field);
//					Node node = nodeList.item(0);
//					Cell cel = new Cell();
////					
//						if(node.getNodeName().equals("active")){
//						if(node.getTextContent().equals("t")){
//							cel=new Cell("TRUE",col);
//						}else{
//							cel=new Cell("FALSE",col);
//						}
//						
//					}else {
//					
//						cel = new Cell(node.getTextContent(), col);
//					}
//					
//					
//					row.addCell(cel);
//
//					// System.out.println(node.getNodeName());
//					// System.out.println("\t\t"+cel);
//				}
//
//			}
//			customer.addRows(row);
//
//			return customer;
//		
//		
//	}
}
