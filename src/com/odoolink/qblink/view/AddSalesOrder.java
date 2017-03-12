package com.odoolink.qblink.view;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aionsoft.qblink.view.OdooData;
import com.aionsoft.qblink.view.QBAdd;
import com.aionsoft.qblink.view.QBExist;
import com.aionsoft.qblink.view.QBExtract;
import com.aionsoft.qblink.view.QBTransform;
import com.aionsoft.qblink.view.QuickbooksData;

/**
 * Servlet implementation class AddSalesOrder
 */
@WebServlet("/AddSalesOrder")
public class AddSalesOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSalesOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				long lStartTime = System.currentTimeMillis();

				
				PrintWriter writer = new PrintWriter(new OutputStreamWriter(
						response.getOutputStream(), StandardCharsets.UTF_8), true);
				
				String soNumber = request.getParameter("add");
				ArrayList<String> ids = new ArrayList<String>(Arrays.asList(soNumber.split(",")));
				writer.println("<!DOCTYPE html>");
				writer.println("<html>");
				writer.println("<body>");
				writer.println("<table  border=\'1\'> ");
				
				writer.println("<tr><th>Sequence</th><th>Command</th><th>Status</th></tr>");				
				
				try {
					writer.println("<tr>");
					writer.println("<td>1/8</td>");
					writer.println("<td>Extracting OdooData</td>");
					OdooData data = new QBExtract().extractOdooData(ids);
					writer.println("<td>done</td></tr>");
					
					writer.println("<tr>");
					writer.println("<td>2/8</td>");
					writer.println("<td>Checking OdooData for Existence</td>");
					data = new QBExist().exist(data);
					writer.println("<td>done</td></tr>");
					
					
					writer.println("<tr>");
					writer.println("<td>3/8</td>");
					writer.println("<td>Transforming OdooData</td>");
					QuickbooksData qbData = new QBTransform().transform(data);
					writer.println("<td>done</td></tr>");
					
					writer.println("<tr>");
					writer.println("<td>4/8</td>");
					writer.println("<td>Adding Customer to Quickbooks</td>");	
					boolean insertCustomer = new QBAdd().insertCustomer(qbData);
					writer.println("<td>done</td></tr>");
					
					writer.println("<tr>");
					writer.println("<td>5/8</td>");
					writer.println("<td>Adding Vendor to Quickbooks</td>");	
					boolean insertVendor = new QBAdd().insertVendors(qbData);
					writer.println("<td>done</td></tr>");
					
					writer.println("<tr>");
					writer.println("<td>6/8</td>");
					writer.println("<td>Adding Category to Quickbooks</td>");	
					boolean insertCategory = new QBAdd().insertProductCategory(qbData);
					writer.println("<td>done</td></tr>");
					
					
					
					writer.println("<tr>");
					writer.println("<td>7/8</td>");
					writer.println("<td>Adding Product to Quickbooks</td>");	
					boolean insertProduct = new QBAdd().insertProduct(qbData);
					writer.println("<td>done</td></tr>");
					
					writer.println("<tr>");
					writer.println("<td>8/8</td>");
					writer.println("<td>Adding SO #"+soNumber+" to Quickbooks</td>");
					
					boolean insert = new QBAdd().insertSaleOrders(qbData);
					writer.println("<td>done</td></tr>");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				//			boolean add = new MainCommand().addBatchSalesOrder(soNumber);
				System.out.println();
				long lEndTime = System.currentTimeMillis();

				long difference = lEndTime - lStartTime;

				writer.println("<tr><td>Elapsed seconds: </td><td>" + difference/1000+"</td></tr>");
				writer.println("<tr><td>Elapsed Minutes: </td><td>" + difference/1000/60+"</td></tr>");
				
				writer.println("</table>");
				
				
				
				writer.println("<H1>Quickbooks Update Tool</H1>"
								+ "<form name=\"Qbooks Link\" method =\"get\" action=\"AddSalesOrder\">"
								+ "sale order:<input type = \"text\" name = \"add\">"
								+ "<input type=\"submit\" value=\"Add\"></form>");				
				writer.println("</body>");
				writer.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
