package com.aionsoft.qblink.controller.qodbc;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aionsoft.qblink.controller.database.ConnectionManager;
import com.aionsoft.qblink.model.base.Cell;
import com.aionsoft.qblink.model.base.Column;
import com.aionsoft.qblink.model.base.Row;
import com.aionsoft.qblink.model.base.Table;
import com.aionsoft.qblink.model.query.Arg;
import com.aionsoft.qblink.model.query.DataType;
import com.aionsoft.qblink.model.query.Query;


public class SQLManager {
	private static Connection conn = ConnectionManager.getInstance().getConnection();


	public Table getColumnsByTableName(String tableName) {

		// String tableName = table.getTableName();
		Table newTable = new Table(tableName);
		String sql = "select column_name,data_type from information_schema.columns where " + "table_name=" + "'"
				+ tableName + "'";

		ResultSet rs = null;

		try (PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);) {

			rs = stmt.executeQuery();
			rs.last();
			System.out.println(rs.getRow() + " record returned");
			rs.beforeFirst();
			// System.out.println(rs.getRow());
			System.out.println("\n\tIterating through columns\n\tReturning Values....");
			while (rs.next()) {
				Column bean = new Column();
				bean.setColumnName(rs.getString("column_name"));
				bean.setDataType(rs.getString("data_type"));
				// System.out.println(bean.getColumnName()+"\t"+bean.getDataType());
				newTable.addColumns(bean);
			}

			return newTable;

		} catch (SQLException e) {
			System.out.println("error");
			System.err.println(e);
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			;
		}

	}

	public Table getRowsByTableName(String tableName) {

		SQLManager man = new SQLManager();

		Table table = man.getColumnsByTableName(tableName);

		System.out.println("ProductManagerOdeoo Initialize" + tableName);

		String sql = "SELECT * from " + tableName;

		ResultSet rs = null;

		try (PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);) {

			rs = stmt.executeQuery();
			rs.last();
			// System.out.println(rs.getRow() + " record returned");
			rs.beforeFirst();
			// System.out.println(rs.getRow());
			System.out.println("Returning Values....from " + tableName);

			while (rs.next()) {

				Row ro = new Row();
				for (int i = 0; i < table.getColumnSize(); i++) {
					Column col = table.getColumns().get(i);
					Cell cell = new Cell();
					cell.setColumn(col);
					cell.setValue(rs.getString(col.getColumnName()));
					ro.addCell(cell);
				}
				table.addRows(ro);

			}

			return table;
		} catch (SQLException e) {
			System.out.println("error");
			System.err.println(e);
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			;
		}

	}

	public Table getRowsBySQL(Query query) {

		String sql = query.getQuery();
		ArrayList<Arg> args = query.getArgs();
		SQLManager man = new SQLManager();
		// String tableName = table.getTableName();
		// table = man.getColumns(table, sql);
		Table table = new Table();
		// System.out.println("ProductManagerOdeoo Initialize" + tableName);
		// String sql = "SELECT * from "+tableName;
		ResultSet rs = null;
		try (PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);) {

			if(args.size()==0){
				System.out.println("no arguments");
			}else {
				
				for(int i=0;i<args.size();i++){
					int index = i+1;
					String value = args.get(i).getValue();
					DataType type = args.get(i).getDataType();
					System.out.println(value+"\t"+type+"\t"+index);
					
					
					if(type.equals(DataType.Int)){
						System.out.println(value+"\t"+type+"\t"+index);
						int value1 = Integer.parseInt(value);
						
						System.out.println(value1+"\t");
						stmt.setInt(1, value1);	
//						stmt.setInt(1, 8);
					}else if(type.equals(DataType.String)){
						System.out.println(value+"\t"+type+"\t"+index);
						stmt.setString(1, value);
					}else if(type.equals(DataType.Boolean)){
						System.out.println(value+"\t"+type+"\t"+index);
						boolean bol = Boolean.parseBoolean(value);
						stmt.setBoolean(1, bol);
						
					}
					
						
				}
//					stmt.setBoolean(1, true);
			}
//			stmt.setInt(1, 8);

			
			rs = stmt.executeQuery();
			//return column information
			ResultSetMetaData rsmd = rs.getMetaData();

			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				String name = rsmd.getColumnName(i);
				String datatype = rsmd.getColumnTypeName(i);
				Column col = new Column(name, datatype);
				table.addColumns(col);
			}

			rs.last();
			// System.out.println(rs.getRow() + " record returned");
			rs.beforeFirst();
			// System.out.println(rs.getRow());
			System.out.println("Returning Values....from " + query);

			while (rs.next()) {

				Row ro = new Row();
				for (int i = 0; i < table.getColumnSize(); i++) {
					Column col = table.getColumns().get(i);
					Cell cell = new Cell();
					cell.setColumn(col);
					cell.setValue(rs.getString(col.getColumnName()));
					ro.addCell(cell);
				}
				table.addRows(ro);

			}

			return table;
		} catch (SQLException e) {
			System.out.println("error");
			System.err.println(e);
			System.err.println(e.getMessage());
			System.err.println(e.getErrorCode());
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			;
		}

	}
	
	public void printXMLByTableName(PrintWriter writer, String tableName) {

		SQLManager newPQ = new SQLManager();

		Table newTable = newPQ.getColumnsByTableName(tableName);
		newTable = newPQ.getRowsByTableName(tableName);
		System.out.println(newTable);

		writer.println("<recordNumber>" + newTable.getRowSize() + "</recordNumber>");

		writer.println("<result>");

		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);
			writer.println("<row>");
			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();
				String dataType = newCell.getColumn().getDataType().toString();
				writer.print("<" + name + "\t dataType='" + dataType + "' >" + value + "</" + name + ">");
			}
			writer.println("</row>");
		}

		writer.println("</result>");

	}

	public void printXMLBySQL(PrintWriter writer, Query query) {

		SQLManager newPQ = new SQLManager();		
		Table newTable = newPQ.getRowsBySQL(query);
		System.out.println(newTable);

		writer.println("<recordNumber>" + newTable.getRowSize() + "</recordNumber>");

		writer.println("<result>");

		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);
			writer.println("<row>");
			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();
				String dataType = newCell.getColumn().getDataType().toString();
				writer.print("<" + name + " dataType='" + dataType + "' >" + value + "</" + name + ">");
				
				
			}
			writer.println("</row>");
		}

		writer.println("</result>");

	}
	public void printXMLBySQLSaleOrderLine(PrintWriter writer, int order_id) {

		String sql = "select * from sale_order_line where order_id="+order_id;
		Query query = new Query(sql);
		SQLManager newPQ = new SQLManager();		
		Table newTable = newPQ.getRowsBySQL(query);
//		System.out.println(newTable);

		writer.println("<sale_order_line>");
		writer.println("<recordNumber>" + newTable.getRowSize() + "</recordNumber>");
		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);
			writer.println("<line>");
			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();
				String dataType = newCell.getColumn().getDataType().toString();
				writer.print("<" + name + " dataType='" + dataType + "' >" + value + "</" + name + ">");
			}
			writer.println("</line>");
		}

		writer.println("</sale_order_line>");

	}
	public void printXMLBySQLSaleOrder(PrintWriter writer, Query query) {

		SQLManager newPQ = new SQLManager();		
		Table newTable = newPQ.getRowsBySQL(query);
		System.out.println(newTable);
		writer.println("<sale_order>");
		writer.println("<recordNumber>" + newTable.getRowSize() + "</recordNumber>");

		

		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);
			writer.println("<row>");
			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();
				String dataType = newCell.getColumn().getDataType().toString();
				
				if(name.equals("id")&&value != null) {
					int order_id = Integer.parseInt(value);
					printXMLBySQLSaleOrderLine(writer, order_id);
					
					//writer.print("<state_code dataType='string' >" + state + "</state_code>");		
				}
				else if(name.equals("partner_invoice_id")&&value != null) {
					writer.print("<partner_invoice_id >");
					int partner_invoice_id = Integer.parseInt(value);
					
					String sqlf="id as pid, name, street,street2,city,zip,state_id";
					String sql_pid= "SELECT "+sqlf+" FROM res_partner WHERE id="+partner_invoice_id;

					Query query_pid1 = new Query(sql_pid);
					printXMLBySQLContact(writer, query_pid1);
					writer.print("</partner_invoice_id >");
					//writer.print("<state_code dataType='string' >" + state + "</state_code>");		
				}
				else if(name.equals("partner_shipping_id")&&value != null) {
					writer.print("<partner_shipping_id >");
					int partner_shipping_id = Integer.parseInt(value);
					
					String sqlf="id as pid, name, street,street2,city,zip,state_id";
					String sql_pid= "SELECT "+sqlf+" FROM res_partner WHERE id="+partner_shipping_id;

					Query query_pid1 = new Query(sql_pid);
					printXMLBySQLContact(writer, query_pid1);
					writer.print("</partner_shipping_id >");
					//writer.print("<state_code dataType='string' >" + state + "</state_code>");		
				}
				else{
					writer.print("<" + name + " dataType='" + dataType + "' >" + value + "</" + name + ">");
				}
				
			}
			writer.println("</row>");
		}

		writer.println("</sale_order>");

	}
	public void printXMLBySQLItemCost(PrintWriter writer, int pt_id) {

		String sql = "SELECT id as cost_id, value_float, name, res_id "
				+ "FROM ir_property "
				+ "WHERE name='standard_price'"
				+ "AND res_id='product.template,"+pt_id+"'";
		Query query = new Query(sql);
		SQLManager newPQ = new SQLManager();		
		Table newTable = newPQ.getRowsBySQL(query);
		System.out.println(newTable);
		writer.println("<cost>");
		writer.println("<recordNumber>" + newTable.getRowSize() + "</recordNumber>");

//		writer.println("<result>");

		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);
			
			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();
				String dataType = newCell.getColumn().getDataType().toString();
				writer.print("<" + name + " dataType='" + dataType + "' >" + value + "</" + name + ">");
			}
			
		}

		writer.println("</cost>");

	}
	
	public void printXMLBySQLProduct(PrintWriter writer, Query query) {

		SQLManager newPQ = new SQLManager();		
		Table newTable = newPQ.getRowsBySQL(query);
		System.out.println(newTable);

		writer.println("<product>");
		writer.println("<recordNumber>" + newTable.getRowSize() + "</recordNumber>");

		

		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);
			writer.println("<row>");
			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();
				String dataType = newCell.getColumn().getDataType().toString();
				writer.print("<" + name + " dataType='" + dataType + "' >" + value + "</" + name + ">");
				if(name.equals("pt_id")&&value == null){
//					System.out.println("null value");
					writer.print("<supplier_info dataType='string' >");
					writer.println("<recordNumber>0</recordNumber>");
					writer.print("</supplier_info>");
				}
				else if(name.equals("pt_id")&&value != null) {
					int pt_id = Integer.parseInt(value);
					printXMLBySQLSupplier(writer, pt_id);
					printXMLBySQLItemCost(writer, pt_id);
					//writer.print("<state_code dataType='string' >" + state + "</state_code>");		
					}
				
			}
			
			writer.println("</row>");
		}

		writer.println("</product>");

	}
	public void printXMLBySQLSupplier(PrintWriter writer, int supp_id) {
		
		String sql = "select pt.id as pt_id,ps.name as supp_id,ps.product_name,ps.product_code,ps.sequence,part.name as supp_name "
				+ "from product_supplierInfo as ps "
				+ "join product_template as pt "
				+ "on pt.id=ps.product_tmpl_id "
				+ "join res_partner as part "
				+ "ON ps.name = part.id "
				+ "WHERE pt.id="+supp_id+" LIMIT 1";
		Query query = new Query(sql);
		SQLManager newPQ = new SQLManager();		
		Table newTable = newPQ.getRowsBySQL(query);
//		System.out.println(newTable);
		writer.println("<supplier_info>");
		writer.println("<recordNumber>" + newTable.getRowSize() + "</recordNumber>");

//		

		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);
//			writer.println("<supplier_info>");
//			writer.println("<recordNumber>" + newTable.getRowSize() + "</recordNumber>");
			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();
				String dataType = newCell.getColumn().getDataType().toString();
				writer.print("<" + name + " dataType='" + dataType + "' >" + value + "</" + name + ">");
			}
//			writer.println("</supplier_info>");
		}
		
		writer.println("</supplier_info>");

	}
	public void printXMLBySQLContact(PrintWriter writer, Query query) {

		SQLManager newPQ = new SQLManager();		
		Table newTable = newPQ.getRowsBySQL(query);
		System.out.println(newTable);

		

		writer.println("<customer>");
		writer.println("<recordNumber>" + newTable.getRowSize() + "</recordNumber>");
		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);
			writer.println("<row>");
			String state = "";
			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();
				String dataType = newCell.getColumn().getDataType().toString();
				writer.print("<" + name + " dataType='" + dataType + "' >" + value + "</" + name + ">");
				//System.out.println(name+":"+value);
				if(name.equals("state_id")&&value == null){
//					System.out.println("null value");
					writer.println("<res_state dataType='string'>");
					writer.println("<recordNumber>0</recordNumber>");
					writer.println("</res_state>");
							
				}
				else if(name.equals("state_id")&&value != null) {
					int state_id = Integer.parseInt(value);
					printXMLBySQLState(writer, state_id);
					//writer.print("<state_code dataType='string' >" + state + "</state_code>");		
					}
			}
			
			writer.println("</row>");
		}

		writer.println("</customer>");

	}
	public void printXMLBySQLState(PrintWriter writer, int state_id) {

		String sql = "SELECT code as state_code,name as state_name,country_id "
				+ "FROM res_country_state "
				+ "WHERE id="+state_id;
		Query query = new Query(sql);
		SQLManager newPQ = new SQLManager();		
		Table newTable = newPQ.getRowsBySQL(query);
		//System.out.println(newTable);
		writer.println("<res_state>");
		writer.println("<recordNumber>" + newTable.getRowSize() + "</recordNumber>");

		//writer.println("<result>");

		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);
			
			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();
				String dataType = newCell.getColumn().getDataType().toString();
				writer.print("<" + name + " dataType='" + dataType + "' >" + value + "</" + name + ">");
				if(name.equals("country_id")&&value == null){
					System.out.println("null value");
					writer.print("<code dataType='string' >null</code>");
				}
				else if(name.equals("country_id")&&value != null) {
					int country_id = Integer.parseInt(value);
					printXMLBySQLCountry(writer, country_id);
					//writer.print("<state_code dataType='string' >" + state + "</state_code>");		
					}
			
			}
			
		}

		//writer.println("</result>");
		writer.println("</res_state>"); 

	}
	public void printXMLBySQLCountry(PrintWriter writer, int country_id) {

		String sql = "SELECT code as country_code,name as country_name "
				+ "FROM res_country "
				+ "WHERE id="+country_id;
		Query query = new Query(sql);
		SQLManager newPQ = new SQLManager();		
		Table newTable = newPQ.getRowsBySQL(query);
		//System.out.println(newTable);
		writer.println("<res_country>");
		writer.println("<recordNumber>" + newTable.getRowSize() + "</recordNumber>");

		//writer.println("<result>");

		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);
			
			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();
				String dataType = newCell.getColumn().getDataType().toString();
				writer.print("<" + name + " dataType='" + dataType + "' >" + value + "</" + name + ">");
			}
			
		}

		//writer.println("</result>");
		writer.println("</res_country>");

	}

	public static void main(String argsp[]) {
		SQLManager newPQ = new SQLManager();

		String tableName = "sale_order";
		String columName = "id";
		String sql1 = "SELECT e,pp.id,pp.product_tmpl_id as pt_id,pt.description_sale,pt.description_purchase,pt.list_price,pt.write_date "
				+ "FROM product_product as pp " + "JOIN product_template as pt " + "on pp.product_tmpl_id =pt.id"
				+ "	ORDER BY pp.name_template, pt.description_sale";
		String sql = "SELECT * FROM res_partner WHERE customer=true AND is_company = ? ";
		String sql2 = "SELECT * FROM res_partner WHERE id=?";
		String sql3 = "SELECT * FROM res_partner WHERE name =?";
		String sql4=  "SELECT * FROM res_partner";
//		String sql5=" where "+columnName;
		// Table newTable = newPQ.getColumnsByTableName(tableName);
		// newTable = newPQ.getRowsByTableName(tableName);
		Arg arg = new Arg(DataType.Boolean,"id",true);
		Arg arg2 = new Arg(DataType.Int,tableName,8);
//		Arg arg3 = new Arg(DataType.STRING,"China Export");
		ArrayList<Arg>args = new ArrayList<>();
		args.add(arg2);
		Query query = new Query(sql2,args);
		System.out.println(arg);
		
		Table newTable = newPQ.getRowsBySQL(query);
		//newPQ.printXMLBySQL(writer, query);
		System.out.println(newTable.getColumnSize());
		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);
			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();
				System.out.println("(" + name + "," + value + ")");
				//System.out.println(name+":"+value);
				if(name.equals("state_id")&&value == null){
					System.out.println("null value");
					System.exit(0);
				}
			}
			System.out.println("");
		}
	}

	

}
