package com.aionsoft.qblink.model.odoo;

public class SupplierInfoOdoo {
	



	String pt_id;
	String supp_id;
	String product_name;
	String product_code;
	String sequence;
	String supp_name;
	
	public SupplierInfoOdoo(String pt_id, String supp_id, String product_name, String product_code, String sequence,
			String supp_name) {
		super();
		this.pt_id = pt_id;
		this.supp_id = supp_id;
		this.product_name = product_name;
		this.product_code = product_code;
		this.sequence = sequence;
		this.supp_name = supp_name;
	}
//	<recordNumberSupplier>1</recordNumberSupplier>
//	<pt_id dataType="serial">146208</pt_id>
//	<supp_id dataType="int4">21806</supp_id>
//	<product_name dataType="varchar">null</product_name>
//	<product_code dataType="varchar">null</product_code>
//	<sequence dataType="int4">1</sequence>
//	<supp_name dataType="varchar">TBD</supp_name>

	public String toString(){
		String text ="\n\t\tpt_id:"+pt_id+"\n"+
				
				"\t\tsupp_id:"+supp_id+"\n"+
				"\t\tproduct_name:"+product_name+"\n"+
				"\t\tproduct_code:"+product_code+"\n"+
				"\t\tsequence:"+sequence+"\n"+
				"\t\tsupp_name:"+supp_name;
				
				
		return text;
	}
	public String getProduct_name() {
		return product_name;
	}
	public String getProduct_code() {
		return product_code;
	}

	public String getSupp_name() {
		return supp_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	
	public void setSupp_name(String supp_name) {
		this.supp_name = supp_name;
	}
	public String getPt_id() {
		return pt_id;
	}
	public String getSupp_id() {
		return supp_id;
	}
	public void setPt_id(String pt_id) {
		this.pt_id = pt_id;
	}
	public void setSupp_id(String supp_id) {
		this.supp_id = supp_id;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	

}
