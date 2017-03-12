package com.aionsoft.qblink.helper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.aionsoft.qblink.controller.database.CMDType;
import com.aionsoft.qblink.controller.database.ConnectionManager;
import com.aionsoft.qblink.controller.database.DBType;
import com.aionsoft.qblink.controller.quickbooks.CustomerManager;
import com.aionsoft.qblink.controller.quickbooks.ProductManager;
import com.aionsoft.qblink.controller.quickbooks.SalesOrderLineManager;
import com.aionsoft.qblink.controller.quickbooks.SalesOrderManager;
import com.aionsoft.qblink.model.base.Cell;
import com.aionsoft.qblink.model.base.Column;
import com.aionsoft.qblink.model.configuration.Entry;
import com.aionsoft.qblink.model.quickbooks.Customer;
import com.aionsoft.qblink.model.quickbooks.Product;
import com.aionsoft.qblink.model.quickbooks.SaleOrder;
import com.aionsoft.qblink.model.quickbooks.SaleOrderLine;
import com.aionsoft.qblink.model.quickbooks.Vendor;
import com.aionsoft.qblink.processor.odoo.SaleOrderProcessor;

//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;

public class Processor {
	// public final static Logger LOGGER =
	// Logger.getLogger(Processor.class.getName());
	private static Connection conn;
//	ArrayList<SimpleStringProperty> searchProp = new ArrayList<SimpleStringProperty>();
	String encoding;
	String searchTable;
	String searchField;
	String searchValue;

	// public String getInfo;

	public Processor() throws SQLException {
		if (conn == null || conn.isClosed()) {
			QBConnection();
		}
	}

	private void QBConnection() {
		conn = ConnectionManager.getInstance().getConnection();
		if (conn == null) {
			System.out.println("please connect to quickbooks first");
		} else {
			System.out.println("connected to quickbooks");

		}
	}

	/**
	 * algorithm for Proccessing XML 1. function
	 * ProcessSaleOrder/PurchaseOrder/Customer/vendor/Product() format URL
	 * string for processing 2. fucntion existOdoo check for search result
	 * existence in odoo if exist return true else return false 3. function
	 * existQuickbooks check for search result existence in quickbooks if exist
	 * return true else return false 2. check for product product parent, if
	 * parent exist(do nothing), else (add parent) 3. check for product vendor,
	 * if exist(do nothing), else(add vendor )
	 */
//	

	public void getDocumentInfo(Document doc) {
		Element root = doc.getDocumentElement();
		NodeList rootNodes = root.getChildNodes();
		for (int i = 1; i < rootNodes.getLength(); i = i + 2) {
			Node tempNode = rootNodes.item(i);
			String name = tempNode.getNodeName();
			String value = tempNode.getNodeValue();
			String text = tempNode.getTextContent();

			if (name.equals("encoding")) {
				encoding = text;
			}
			if (name.equals("searchTable")) {
				searchTable = text;
			}
			if (name.equals("searchField")) {
				searchField = text;
			}
			if (name.equals("searchValue")) {
				searchValue = text;
			}
		}
	}
	public ArrayList<Document> getDocument(ArrayList<String> uris) throws SQLException {
		int size = uris.size();
		ArrayList<Document> docs = new ArrayList<>();
		for (int i = 0;i<size;i++){
			String id = uris.get(i);
			Document document = getDocument(id);
			docs.add(document);
			showPercentage(size, i);
		}
		System.out.println("100%");
		return docs;
	}

	private void showPercentage(int size, int i) {
		double a = i;
		double b = size;
		
		double rate = a/b;
		System.out.printf("%.2f",rate*100);
		System.out.print("%");
		System.out.println("");
	}
	

	public Document getDocument(String uri) throws SQLException {

		// processing
		// LOGGER.setLevel(Level.INFO);
		// LOGGER.info("GET DOCUMENT\n");
//		System.out.println("1.0-Processor-getDocument:\n\t" + uri);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=null;
		Document doc = null;
//		System.out.println(uri);
		try {
			// extract document from xml
			builder = factory.newDocumentBuilder();
			doc = builder.parse(uri);
			// get root document

			doc.getDocumentElement().normalize();
			
			// extract basic information
			getDocumentInfo(doc);
//			String pt_id = new SaleOrderProcessor().getItem("searchValue");
//			System.out.println("searchVAlue"+pt_id);

		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			// e1.getMessage();
			e1.getMessage();

		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			e.getMessage();
		}
		return doc;
	}

//	public SaleOrder getSaleOrderDocument(Document doc) {
//		System.out.println("Processor-getSaleOrder");
//		SaleOrder saleOrder = new Generator().parseNodeSaleOrder(doc);
//		return saleOrder;
//	}
//
//	public SaleOrderLine getSaleOrderLineDocument(Document doc) {
//		System.out.println("Processor-getSaleOrderLine");
//		SaleOrderLine saleOrderLine = new Generator().parseNodeSaleOrderLine(doc);
//		return saleOrderLine;
//	}
//
//	public boolean insertSaleOrderLine(Document doc) throws SQLException {
//		System.out.println("Processor-insertSaleOrderLine");
//
//		SaleOrder saleOrder = new Generator().parseNodeSaleOrder(doc);
//		SaleOrderLine saleOrderLine = new Generator().parseNodeSaleOrderLine(doc);
//		boolean insertSaleOrderSuccess = new SalesOrderLineManager().insertSaleOrderLine(saleOrderLine);
//		if (insertSaleOrderSuccess) {
//			return true;
//		}
//		return false;
//
//	}

//	public boolean insertProduct(Document doc) throws SQLException {
//		System.out.println("Processor-insertProduct");
//		NodeList nl = doc.getElementsByTagName("product");
//		Product product = new Generator().parseNodeProduct(doc);
//		System.out.println(product.printProductValue());
//		boolean insert = new ProductManager().insertProduct(product);
//		if (insert) {
//			return true;
//		}
//
//		return false;
//
//	}

	

	public String printHeader() {
		String text = "\n---------------------------";
		text = text + "encoding\t" + encoding;
		text = text + "searchTable\t" + searchTable;
		text = text + "searchField\t" + searchField;
		text = text + "searchValue\t" + searchValue;
		text = text + "---------------------------\n";
		return text;
	}





	public Boolean existCustomerQB(String value) {
		System.out.println("2.1.3.2-Processor-existCustomerQB");
		Boolean exist = new CustomerManager().existById(value);
		System.out.println("\tCustmomer ID:" + value + "," + exist);
		return exist;
	}

	public Boolean existProductQB(String value) {
		// TODO Auto-generated method stub
		Boolean exist = new ProductManager().existById(value);
		System.out.println("\tProduct ID:" + value + "," + exist);
		return exist;
	}

	
}
