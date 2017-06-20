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

import com.aionsoft.qblink.model.quickbooks.SaleOrder;
import com.aionsoft.qblink.model.quickbooks.SaleOrderLine;
import com.aionsoft.qblink.model.report.SOStatusReport;
import com.aionsoft.qblink.view.QBGet;

/**
 * Servlet implementation class UpdateSO
 */
@WebServlet(description = "Update Sales Order Status", urlPatterns = { "/UpdateSO" })
public class UpdateSO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String soNumber = request.getParameter("update");
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(
				response.getOutputStream(), StandardCharsets.UTF_8), true);
		ArrayList<String> ids = new ArrayList<String>(Arrays.asList(soNumber.split(",")));
		try {
			
			new SOStatusReport().UpdateOpenSalesOrderBySONumber(soNumber);
			SaleOrder so = new QBGet().getSaleOrder(soNumber);
			ArrayList<SaleOrderLine> soLine = so.getSaleOrderLine();
			writer.println("<!DOCTYPE html>");
			writer.println("<html>");
			writer.println("<body>");
			writer.println("<table  border=\'1\'> ");
			
			writer.println("<tr><th>SO Number</th><th>#</th><th>Status</th>"
					+ "<th>PO Number</th><th>Ship Date</th><th>Quantity</th><th>Price</th><th>Price Total</th>"
					+ "<th>Description</th></tr>");
			
			for (int i=0;i<soLine.size();i++){
				SaleOrderLine line = soLine.get(i); 
				writer.println("<tr>");	
				writer.println("<td>"+line.getRefNumber()+"</td>");	
				writer.println("<td>"+line.getRow()+"</td>");
				writer.println("<td>"+line.getStatus()+"</td>");	
				writer.println("<td>"+line.getPONumber()+"</td>");	
				writer.println("<td>"+line.getShipdate()+"</td>");	
				writer.println("<td>"+line.getQty()+"</td>");	
				writer.println("<td>"+line.getRate()+"</td>");	
				writer.println("<td>"+line.getAmount()+"</td>");					
				writer.println("<td>"+line.getName()+"</td>");		
				writer.println("</tr>");	
			}
			writer.println("</table>");
			writer.println("</body>");
			writer.println("</html>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
