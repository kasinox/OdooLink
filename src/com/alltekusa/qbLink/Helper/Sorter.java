package com.alltekusa.qbLink.Helper;

import java.util.ArrayList;

import com.alltekusa.qbLink.Odoo.Model.SaleOrderLineOdoo;

public class Sorter {
	
	ArrayList<SaleOrderLineOdoo> soLinesContainer = new ArrayList<>();
	public Sorter(){
		
	}
	
	public ArrayList<SaleOrderLineOdoo> sort(ArrayList<SaleOrderLineOdoo> lines){
		
		int size = lines.size();

		int i =0;
		while(lines.size()>0){
			
			for(int j=0;j<lines.size();j++){
				
				SaleOrderLineOdoo soLine = lines.get(j);
				
				int index = Integer.parseInt(soLine.getSequence());
//				System.out.println(index+","+j);
				if(index==i||index<i){
					if(!soLinesContainer.contains(soLine))soLinesContainer.add(soLine);
					
					
					lines.remove(soLine);
//					System.out.println(soLinesContainer.size()+"");
				}

			}
			
			i++;
			
		}
		return soLinesContainer;
		
		
	}

}
