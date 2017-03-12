package com.alltekusa.qbLink.Helper;

import java.sql.SQLException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ExistInOdoo {
	
	private NodeList nodeList_record_number = null;
	public ExistInOdoo(){
//		System.out.println("2.0-Processor-existOdoo");

	}
	

	/**
	 * to make sure there are at least ONE result returned from the search
	 * in Odoo
	 *
	 * if resultNumber ==0 return false else return true
	 * 
	 */
	
	public boolean OdooFileValidationLVL1(Document doc){
		
		
		String so = getSO_id(doc);
		System.out.println("Validation: LVL 1 - SO #"+so);
		
		
		boolean existSaleOrder =new ExistInOdoo().saleOrder(doc);	
		
//		Boolean existProduct = new ExistInOdoo().items(doc);
		boolean existCustomer = false;
		boolean existSaleOrderLine=false;
		if(existSaleOrder){
			System.out.println("\tOdoo Sale Order:PASS");
			
			existCustomer=new ExistInOdoo().customer(doc);
			existSaleOrderLine = new ExistInOdoo().soLine(doc);
			if(existCustomer){
				System.out.println("\tOdoo Customer:PASS");
			}else{
				System.out.println("\tOdoo Customer:FAIL");
			}
			if(existSaleOrderLine){
				System.out.println("\tOdoo SOLine:PASS");
			}else{
				System.out.println("\tOdoo SOLine:FAIL");
			}
		}else{
			System.out.println("\tOdoo Sale Order:FAIL");			
		}
		if(existSaleOrder&&existCustomer&&existSaleOrderLine){
			System.out.println("\tValidation LVL1: PASS");	

			return true;
		}
		System.out.println("\tValidation LVL1: FAIL");	

		return false;
	}
	public boolean OdooFileValidationLVL2(Document doc){
		String so = getSO_id(doc);
		System.out.println("Validation: LVL 2 - SO #"+so);
		boolean existProducts = new ExistInOdoo().products(doc);
		return false;
	}
	
	public boolean saleOrder(Document doc){	
		//checking if record exist
//		System.out.println("2.1.1-Processor-checking SALE ORDER record exist Odoo");
		nodeList_record_number = doc.getElementsByTagName("recordNumberSaleOrder");

		return validate(nodeList_record_number);
	}
	
	public boolean customer(Document doc){
		
		
//		System.out.println("2.1.3-Processor-checking CUSTOMER record exist Odoo");
		nodeList_record_number = doc.getElementsByTagName("recordNumberContactPartner_id");
		

		return validate(nodeList_record_number);
	}
	
	public boolean soLine(Document doc){
		nodeList_record_number = doc.getElementsByTagName("recordNumberSaleOrderLine");
		return validate(nodeList_record_number);
	}
	
	public boolean products(Document doc){
		
		//product informaiton validation
		ArrayList<String> productId = new ArrayList<>();
		GetOdooIds getter = new GetOdooIds();
		productId=getter.getProductIds(doc);
		Processor cpu;
		try {
			cpu = new Processor();
		
//		System.out.println(productId.size());
		for(int i=0;i<productId.size();i++){			
			String current_id = productId.get(i);
//			System.out.println(current_id);
			if(current_id==null){
				return false;
			}
			String uri= new GetURI().product(current_id);
			Document product = cpu.getDocument(uri);
			
//			System.out.println(current_id);
			
			Boolean validate = new ExistInOdoo().item(product);
			if(validate ==true){
//				System.out.println(validate);
				System.out.println("\tOdoo Product:PASS");
			}else{
				System.out.println("\tOdoo Product:FAIL");
				return false;
			}
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
	
	public boolean item(Document doc){
		
		nodeList_record_number = doc.getElementsByTagName("recordNumberProduct");
		

		return validate(nodeList_record_number);
	}
	
	public boolean vendor(Document doc){
	
		//checking if record exist
		
//		System.out.println("2.1.4-Processor-checking VENDOR record exist Odoo");
		nodeList_record_number = doc.getElementsByTagName("recordNumberContact");
		

		return validate(nodeList_record_number);
	}

	private boolean validate(NodeList nodeList_record_number) {
//		System.out.println("test");
		Node node_record = nodeList_record_number.item(0);

		if (node_record == null) {
			System.out.println("2.2.2-Processor- node_record=null");
			System.exit(0);
		}
		int record_number = Integer.parseInt(node_record.getTextContent());
//		System.out.println(record_number);
		if (record_number == 0) {
//			System.out.println("2.2.3-Processor- node_record=0");
			System.out.println("Record Number not found");
			return false;
		} else {
//			System.out.println("2.2.1-Processor-record number: " + record_number + "\n");
			return true;

		}
	}
	public String getSO_id(Document doc){
		String id=null;
		NodeList node = doc.getElementsByTagName("searchValue");
		Node node_record = node.item(0);
		
		
		if (node == null) {
			System.out.println("so_number does not exist");
			System.exit(0);
		}
		id = node_record.getTextContent();
		
		
		
		return id;
	}
	
	
	
	
	

}
