package com.aionsoft.qblink.model.quickbooks;

import java.util.ArrayList;

import com.aionsoft.qblink.model.base.Table;

public class SaleOrder extends Table {
	
	String CustomerRefListID;
	String TemplateRefListID;
	String BillAddressAddr1;
	String BillAddressAddr2;
	String BillAddressAddr3;
	String BillAddressCity;
	String BillAddressPostalCode;
	String ShipAddressAddr1;
	String ShipAddressAddr2;
	String ShipAddressAddr3;
	String ShipAddressCity;
	String ShipAddressPostalCode;
	String SalesRepRefListID;
	String TermsRefFullName;
	SaleOrderLine soLines;
	double amount_tax;
	static String tableName = "saleOrder";

	//for checking order status
	String customerName;
	String dueDate;
	String orderType;
	String shipDate;
	String shipMethodRefFullName;
	String RefNumber;
	String FOB;
	String PONumber;
	boolean complete;
	ArrayList<SaleOrderLine> saleOrderLines;

	public SaleOrder(){
		super(tableName);
		init();
	}
	
	public SaleOrder(String refNumber2, String customerName, String dueDate, String order_type, String shipDate,
			String shipMethodRefFullName) {
		this.RefNumber=refNumber2;
		this.customerName=customerName;
		this.dueDate=dueDate;
		this.orderType=orderType;
		this.shipDate=shipDate;
		this.shipMethodRefFullName=shipMethodRefFullName;
	}

	public void init(){

//		addColumn("CustomerRefListID","String","customer_id",true,true);
//		addColumn("TemplateRefListID","String","TemplateRefListID",true,true);	
		addColumn("RefNumber","String","so_number",true,true);
		addColumn("BillAddressAddr1","String","invoice_name",true,true);	
		addColumn("BillAddressAddr2","String","invoice_street",true,true);	
		addColumn("BillAddressAddr3","String","invoice_street2",true,true);
//		addColumn("BillAddressAddr4","String","",true,true);	
//		addColumn("BillAddressAddr5","String","",true,true);
		addColumn("BillAddressCity","String","invoice_city",true,true);
//		addColumn("BillAddressState","String","",true,true);
		addColumn("BillAddressPostalCode","String","invoice_zip",true,true);
//		addColumn("BillAddressCountry","String","invoice_country",true,true);
//		addColumn("BillAddressNote","String","",true,true);
		addColumn("ShipAddressAddr1","String","shipping_name",true,true);	
		addColumn("ShipAddressAddr2","String","shipping_street",true,true);
		addColumn("ShipAddressAddr3","String","shipping_street2",true,true);
//		addColumn("ShipAddressAddr4","String","",true,true);
//		addColumn("ShipAddressAddr5","String","",true,true);
		addColumn("ShipAddressCity","String","shipping_city",true,true);
//		addColumn("ShipAddressState","String","",true,true);
		addColumn("ShipAddressPostalCode","String","shipping_zip",true,true);	
//		addColumn("ShipAddressCountry","String","shipping_country",true,true);
//		addColumn("ShipAddressNote","String","",true,true);	
//		addColumn("TermsRefListID","String","",true,true);//add later
//		addColumn("DueDate","String","",true,true);
		addColumn("SalesRepRefListID","String","",true,true);
		addColumn("TermsRefFullName","String","",true,true);
		addColumn("PONumber","String","x_contract",true,true);
		addColumn("FOB","String","",true,true);
		addColumn("amount_tax","doule","",true,true);
		

//		addColumn("ShipDate","String","",true,true);
//		addColumn("ShipMethodRefListID","String","",true,true);
//		ConfigManSalesOrderLine solineconfig= new ConfigManSalesOrderLine();
//		ArrayList<Entry> entrySet = solineconfig.getEntrySet();
//		addColumns(entrySet);		


	}

	public String getCustomerRefListID() {
		return CustomerRefListID;
	}

	public String getTemplateRefListID() {
		return TemplateRefListID;
	}

	public String getRefNumber() {
		return RefNumber;
	}

	public String getBillAddressAddr1() {
		return BillAddressAddr1;
	}

	public String getBillAddressAddr2() {
		return BillAddressAddr2;
	}

	public String getBillAddressAddr3() {
		return BillAddressAddr3;
	}

	public String getBillAddressCity() {
		return BillAddressCity;
	}

	public String getShipAddressAddr1() {
		return ShipAddressAddr1;
	}

	public String getShipAddressAddr2() {
		return ShipAddressAddr2;
	}

	public String getShipAddressAddr3() {
		return ShipAddressAddr3;
	}

	public String getShipAddressCity() {
		return ShipAddressCity;
	}

	public String getSalesRepRefListID() {
		return SalesRepRefListID;
	}

	public String getTableName() {
		return tableName;
	}

	public void setCustomerRefListID(String customerRefListID) {
		CustomerRefListID = customerRefListID;
	}

	public void setTemplateRefListID(String templateRefListID) {
		TemplateRefListID = templateRefListID;
	}

	public void setRefNumber(String refNumber) {
		RefNumber = refNumber;
	}

	public void setBillAddressAddr1(String billAddressAddr1) {
		BillAddressAddr1 = billAddressAddr1;
	}

	public void setBillAddressAddr2(String billAddressAddr2) {
		BillAddressAddr2 = billAddressAddr2;
	}

	public void setBillAddressAddr3(String billAddressAddr3) {
		BillAddressAddr3 = billAddressAddr3;
	}

	public void setBillAddressCity(String billAddressCity) {
		BillAddressCity = billAddressCity;
	}

	public void setShipAddressAddr1(String shipAddressAddr1) {
		ShipAddressAddr1 = shipAddressAddr1;
	}

	public void setShipAddressAddr2(String shipAddressAddr2) {
		ShipAddressAddr2 = shipAddressAddr2;
	}

	public void setShipAddressAddr3(String shipAddressAddr3) {
		ShipAddressAddr3 = shipAddressAddr3;
	}

	public void setShipAddressCity(String shipAddressCity) {
		ShipAddressCity = shipAddressCity;
	}

	public void setSalesRepRefListID(String salesRepRefListID) {
		SalesRepRefListID = salesRepRefListID;
	}

	public void setTableName(String tableName) {
		SaleOrder.tableName = tableName;
	}

	public ArrayList<SaleOrderLine> getSaleOrderLine() {
		return saleOrderLines;
	}

	public void setSaleOrderLine(ArrayList<SaleOrderLine> saleOrderLine) {
		this.saleOrderLines = saleOrderLine;
	}

	public String getTermsRefFullName() {
		return TermsRefFullName;
	}

	public void setTermsRefFullName(String termsRefFullName) {
		TermsRefFullName = termsRefFullName;
	}


	public String getFOB() {
		return FOB;
	}

	public void setFOB(String fOB) {
		FOB = fOB;
	}

	public double getAmount_tax() {
		return amount_tax;
	}

	public void setAmount_tax(double amount_tax) {
		this.amount_tax = amount_tax;
	}

	public String getPONumber() {
		return PONumber;
	}

	public void setPONumber(String pONumber) {
		PONumber = pONumber;
	}

	public String getBillAddressPostalCode() {
		return BillAddressPostalCode;
	}

	public String getShipAddressPostalCode() {
		return ShipAddressPostalCode;
	}

	public void setBillAddressPostalCode(String billAddressPostalCode) {
		BillAddressPostalCode = billAddressPostalCode;
	}

	public void setShipAddressPostalCode(String shipAddressPostalCode) {
		ShipAddressPostalCode = shipAddressPostalCode;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	
	public String toString(){
	
		return "SalesOrder ["+
		RefNumber+"\t"+
		customerName+"\t"+
		dueDate+"\t"+
		orderType+"\t"+
		shipDate+"\t"+
		shipMethodRefFullName+"\t"+
		FOB+"]";
	}

	public SaleOrderLine getSoLines() {
		return soLines;
	}

	public void setSoLines(SaleOrderLine soLines) {
		this.soLines = soLines;
	}
	

	


	public String getCustomerName() {
		return customerName;
	}

	public String getDueDate() {
		return dueDate;
	}

	public String getOrderType() {
		return orderType;
	}

	public String getShipDate() {
		return shipDate;
	}

	public String getShipMethodRefFullName() {
		return shipMethodRefFullName;
	}

	public ArrayList<SaleOrderLine> getSaleOrderLines() {
		return saleOrderLines;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	public void setShipMethodRefFullName(String shipMethodRefFullName) {
		this.shipMethodRefFullName = shipMethodRefFullName;
	}

	public void setSaleOrderLines(ArrayList<SaleOrderLine> saleOrderLines) {
		this.saleOrderLines = saleOrderLines;
	}
	
	/**
	 * print sales order to xml
	 * @return
	 */
	public String toXML(){
		String xml="<SalesOrder>\n"+
					"<RefNumber>"+RefNumber+"</RefNumber> \n"+
					"<CustomerName>"+customerName+"</CustomerName>\n"+
					"<DueDate>"+dueDate+"</DueDate>\n"+
					"<OrderType>"+orderType+"</OrderType>\n"+
					"<ShipDate>"+shipDate+"</ShipDate>\n"+
					"<ShipMethod>"+shipMethodRefFullName+"</ShipMethod>\n"+
					"<FOB>"+FOB+"</FOB>\n"
				   +"</SalesOrder>";
		
		
		return xml;				
	}
	public static void main(String args[]){
		SaleOrder so = new SaleOrder();
		
		so.setRefNumber("111");
		so.setCustomerName("name");
		so.setDueDate("1-1-2011");
		System.out.println(so.toXML());
	}

}
