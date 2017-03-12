package com.aionsoft.qblink.model.odoo;

public class AccountOdoo {

		AccountType type;
		int income_id;
		int accountincome_code;
		String value_reference;
		
		public AccountOdoo(){
			
		}
		
		public AccountOdoo(AccountType type, int income_id, int accountincome_code, String value_reference) {
			super();
			this.type = type;
			this.income_id = income_id;
			this.accountincome_code = accountincome_code;
			this.value_reference = value_reference;
		}

		public enum AccountType{
			income,
			expense,
		}
		
		public int getIncome_id() {
			return income_id;
		}
		public int getAccountincome_code() {
			return accountincome_code;
		}
		public String getValue_reference() {
			return value_reference;
		}
		
		public void setIncome_id(int income_id) {
			this.income_id = income_id;
		}
		public void setAccountincome_code(int accountincome_code) {
			this.accountincome_code = accountincome_code;
		}
		public void setValue_reference(String value_reference) {
			this.value_reference = value_reference;
		}
		public AccountType getType() {
			return type;
		}
		public void setType(AccountType type) {
			this.type = type;
		}
		
}
