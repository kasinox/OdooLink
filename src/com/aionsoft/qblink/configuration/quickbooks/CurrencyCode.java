package com.aionsoft.qblink.configuration.quickbooks;

import java.util.ArrayList;

public class CurrencyCode {
	
	double rateUSD=1.0;
	double rateCNY=7.05*1.08;
	double rateEUR=1.0;
	ArrayList<Currency> list = new ArrayList<>();
	public CurrencyCode(){
		
		list.add(new Currency("USD","1",rateUSD));
		list.add(new Currency("CNY","5",rateCNY));
		list.add(new Currency("EUR","6",rateEUR));
	}
	
	public double getCurrencyRate(String id){
		double rate = 0;
		for (int i=0;i<list.size();i++){
			String id2 = list.get(i).getId();
			
			if(id2.equals(id)){
				rate = list.get(i).getRate();
			}
		}
		return rate;
	}
}
