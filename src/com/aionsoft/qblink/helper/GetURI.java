package com.aionsoft.qblink.helper;

import java.sql.SQLException;
import java.util.ArrayList;

public class GetURI {

	String baseURL;
	public GetURI(){
		ReadFileByLine newFile = new ReadFileByLine("C:\\OdooLink\\User\\odoo_address.txt");

		baseURL=newFile.getURL();
	}
	
	public String saleOrder(String id) throws SQLException {
		String uri = baseURL+"Search?table=sale_order&field=name&value=" + id;
		return uri;
	}

	public String customer(String id) throws SQLException {
		String uri = baseURL+"Search?table=customer&field=id&value=" + id;
		return uri;
	}

	public String vendor(String id) throws SQLException {
		String uri = baseURL+"Search?table=customer&field=id&value=" + id;
		return uri;
	}

	public String product(String id) throws SQLException {
		String uri = baseURL+"Search?table=items&field=id&value=" + id;
		return uri;
	}

	public String purchaseOrder(String id) throws SQLException {
		String uri = baseURL+"Search?table=items&field=id&value=" + id;
		return uri;
	}
	public ArrayList<String> saleOrder(ArrayList<String> so_ids) throws SQLException {
		int size = so_ids.size();
		ArrayList<String> uris = new ArrayList<String>();
		for (int i = 0;i<size;i++){
			String id = so_ids.get(i);
			String uri = saleOrder(id);
			uris.add(uri);
		}
		return uris;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}
}
