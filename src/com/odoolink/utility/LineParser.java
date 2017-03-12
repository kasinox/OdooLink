package com.odoolink.utility;

import java.util.Scanner;

public class LineParser {
	
	String inputText;
	String outputText;
	public LineParser(String inputText) {
		super();
		this.inputText = inputText;
	}
	
	public String removeLineBreaker(String inputText,String replaceText){
		
		Scanner s = new Scanner(inputText);
		 s.useDelimiter(",|\\n");
		 String sum="";
		 while(s.hasNext()){
			 String newline = s.next();
			 sum= sum+newline+replaceText;				          
		 }

		 outputText=sum;
		return outputText;
	}
	
	public static void main(){
		
		String newText = "line 1\n line 2\n";
		System.out.println(newText);
	}

}
