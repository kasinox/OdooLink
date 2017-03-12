package com.aionsoft.qblink.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvertor {

	String sqlDate;
	String javaDate;
	String dateFormatChina="yyyy-MM-dd";
	String dateFormatUS = "MM/dd/yyyy";
	String newDateString;
	public DateConvertor(String dateString){
		
//		System.out.println("DateString"+dateString);
		
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormatUS);
		
		Date d;
		try {
			d = sdf.parse(dateString);
			sdf.applyPattern(dateFormatChina);
//			System.out.println(sdf.toPattern());
			newDateString = sdf.format(d);
			
//			System.out.println("newDateString:"+newDateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getNewDateString(){
		
		return newDateString;
	}
}
