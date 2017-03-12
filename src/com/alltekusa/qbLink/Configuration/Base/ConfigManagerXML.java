package com.alltekusa.qbLink.Configuration.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.alltekusa.qbLink.Model.Configuration.Entry;

public class ConfigManagerXML {
	
	
	ArrayList<Entry> entrySet=new ArrayList<>();
	ArrayList<Entry> updateSet = new ArrayList<>();
	ArrayList<Entry> createSet = new ArrayList<>();

	private String filePath = "";

	public ConfigManagerXML(String file) {
		System.out.println("loading configuration");
		setFilePath(file);
	}
	public void setFilePath(String path){
		this.filePath=path;
		init();
	}
	public void init(){
		File configFile = new File(filePath);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try

		{
			FileReader reader = new FileReader(configFile);
			InputStream in = new FileInputStream(configFile);
			builder = factory.newDocumentBuilder();

			Document doc = builder.parse(in);

			Element root = doc.getDocumentElement();
			doc.getDocumentElement().normalize();
//			System.out.println("----------");
			NodeList nl = doc.getElementsByTagName("entry");

			Node n;

			for (int i = 0; i < nl.getLength(); i++) {
				boolean create=false;
				boolean update=false;
				n = nl.item(i);	
				//iterate thru the items in the entryList
				String name = n.getAttributes().getNamedItem("name").getNodeValue();
				String type = n.getAttributes().getNamedItem("type").getNodeValue();
				String c = n.getAttributes().getNamedItem("create").getNodeValue();
				String u = n.getAttributes().getNamedItem("update").getNodeValue();
				String mapValue = n.getTextContent();
				
				{
					if(c.equals("True")) create = true;
					else create=false;
					if(u.equals("True"))update=true;
					else update = false;
				}
				
				Entry temp = new Entry(name,type,create,update,mapValue);
				
				if(create==true)createSet.add(temp);
				if(update==true)updateSet.add(temp);
				
//				System.out.println("b\t"+name+"\t"+type+"\t"+create+"\t"+update);
				entrySet.add(temp);
			}

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (

		FileNotFoundException ex1) {
			// file does not exist
			System.out.println(ex1);
		} catch (IOException ex) {
			// I/O error
			System.out.println(ex);
		}
	}
	
	public ArrayList<Entry> getEntrySet() {
		return entrySet;
	}


	public ArrayList<Entry> getUpdateSet() {
		return updateSet;
	}


	public ArrayList<Entry> getCreateSet() {
		return createSet;
	}
	public Entry getEntry(String name){
		for(int i =0;i<entrySet.size();i++){
			Entry tempEntry = entrySet.get(i);
			String tempName = tempEntry.getName();
			if(name.equals(tempName)) {
				return tempEntry;
			}
		}
		return null;
	}
	
public static void main(String args[]) {
		String path="file/config/customer.xml";
		ConfigManagerXML man = new ConfigManagerXML(path);
		
		Entry name = man.getEntry("Name");
		System.out.println(name.getContent());
//		man.setFilePath(path);
		
//		System.out.println("test");
//		for (int i=0;i<temp.size();i++){
//			System.out.println(temp.get(i).toStringField());
//		}
		
		
	}
}
