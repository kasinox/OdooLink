package com.alltekusa.qbLink.Helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.naming.Context;

public class Downloader {
	
	

	
public void XML() throws MalformedURLException{

	File xml = null;
	int id=83907;
	String down_file = id+".xml";
	Context context;
	URL url;
	URLConnection connection;
	url  = new URL("http://54.223.173.85:8181/OdooLink/Search?table=sale_order&field=name&value="+id);
	
	System.out.println(url);


	
	try {
	    connection = url.openConnection();
	    connection.connect();
	    InputStream stream = connection.getInputStream();
	    if (stream == null) {
	        System.out.println("unable to create file");
	    }
	    FileOutputStream fos = new FileOutputStream(xml);
	    byte buf[] = new byte[128 * 1024];
	    do {
	        int numread = stream.read(buf);
	        if (numread <= 0) {
	            break;
	        } else {
	            fos.write(numread);
	        }
	    } while (true);

	    fos.close();
	} catch(Exception e) {
	    System.out.println("cannot download the xml file");
	    e.printStackTrace();
	}


}
public static void main(String args[]) throws MalformedURLException{
	
	Downloader download = new Downloader();
	
	download.XML();
	
}
}
