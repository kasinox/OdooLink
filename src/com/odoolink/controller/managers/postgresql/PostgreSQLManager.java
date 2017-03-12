package com.odoolink.controller.managers.postgresql;

import java.io.PrintWriter;
import static java.nio.charset.StandardCharsets.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.odoolink.controller.managers.postgresql.model.Arg;
import com.odoolink.controller.managers.postgresql.model.DataType;
import com.odoolink.controller.managers.postgresql.model.Query;
import com.odoolink.controller.model.Cell;
import com.odoolink.controller.model.Column;
import com.odoolink.controller.model.Row;
import com.odoolink.controller.model.Table;
import com.odoolink.dbConnector.ConnectionManagerPostgreSQL;

public class PostgreSQLManager {

	private static Connection conn = ConnectionManagerPostgreSQL.getInstance().getConnection();

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
			// System.out.println("\n\tIterating through columns\n\tReturning
			// Values....");
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

		PostgreSQLManager man = new PostgreSQLManager();

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
			// System.out.println("Returning Values....from " + tableName);

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
		PostgreSQLManager man = new PostgreSQLManager();
		// String tableName = table.getTableName();
		// table = man.getColumns(table, sql);
		Table table = new Table();
		// System.out.println("ProductManagerOdeoo Initialize" + tableName);
		// String sql = "SELECT * from "+tableName;
		ResultSet rs = null;
		try (PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);) {

			if (args.size() == 0) {
				System.out.println("no arguments");
			} else {

				for (int i = 0; i < args.size(); i++) {
					int index = i + 1;
					String value = args.get(i).getValue();
					DataType type = args.get(i).getDataType();
					// System.out.println(value+"\t"+type+"\t"+index);

					if (type.equals(DataType.Int)) {
						// System.out.println(value+"\t"+type+"\t"+index);
						int value1 = Integer.parseInt(value);

						// System.out.println(value1+"\t");
						stmt.setInt(1, value1);
						// stmt.setInt(1, 8);
					} else if (type.equals(DataType.String)) {
						// System.out.println(value+"\t"+type+"\t"+index);
						stmt.setString(1, value);
					} else if (type.equals(DataType.Boolean)) {
						// System.out.println(value+"\t"+type+"\t"+index);
						boolean bol = Boolean.parseBoolean(value);
						stmt.setBoolean(1, bol);

					}

				}
				// stmt.setBoolean(1, true);
			}
			// stmt.setInt(1, 8);

			rs = stmt.executeQuery();
			// return column information
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
			// System.out.println("Returning Values....from " + query);

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

		PostgreSQLManager newPQ = new PostgreSQLManager();

		Table newTable = newPQ.getColumnsByTableName(tableName);
		newTable = newPQ.getRowsByTableName(tableName);
		System.out.println(newTable);

		writer.println("<result>");
		writer.println("<recordNumber>" + newTable.getRowSize() + "</recordNumber>");
		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);
			writer.println("<record>");
			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();
				String dataType = newCell.getColumn().getDataType().toString();
				writer.print("<" + name + "\t dataType='" + dataType + "' >" + value + "</" + name + ">");
			}
			writer.println("</record>");
		}

		writer.println("</result>");

	}

	public void printXMLBySQL(PrintWriter writer, Query query) {

		PostgreSQLManager newPQ = new PostgreSQLManager();
		Table newTable = newPQ.getRowsBySQL(query);
		System.out.println(newTable);

		writer.println("<result>");
		writer.println("<recordNumber>" + newTable.getRowSize() + "</recordNumber>");
		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);
			writer.println("<record>");
			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();
				String dataType = newCell.getColumn().getDataType().toString();
				writer.print("<" + name + " dataType='" + dataType + "' >" + value + "</" + name + ">");

			}
			writer.println("</record>");
		}

		writer.println("</result>");

	}

	public void printXMLBySQLSaleOrderLine(PrintWriter writer, int order_id) {
		//
//		String sql = "select  soLine.id as so_id,soLine.product_uom_qty as qty,soLine.sequence,soLine.order_id,soLine.price_unit as price,soLine.discount,soLine.salesman_id,"
//				+ "soLine.product_id,soLine.name as so_desc,soLine.state,soLine.order_partner_id,"
//				+ "layout.id as layout_id,layout.name as layout_name "
//				+ "From sale_layout_category as layout "
//				+ "JOIN sale_order_line as soLine on layout.id=soLine.sale_layout_cat_id "
//				+ "where order_id=" + order_id;
		
		String sql="select  soLine.id as so_id,soLine.product_uom_qty as qty,soLine.sequence,soLine.order_id,soLine.price_unit as price,soLine.discount,soLine.salesman_id,"
				+ "soLine.product_id,soLine.name as so_desc,soLine.state,soLine.order_partner_id,soLine.sale_layout_cat_id "
				
				+ "From sale_order_line as soLine "
				+ "where order_id=" + order_id;
				
				
				
				
		// String sql = "select id,name from sale_order_line WHERE
		// order_id="+order_id;
		Query query = new Query(sql);
		PostgreSQLManager newPQ = new PostgreSQLManager();
		Table newTable = newPQ.getRowsBySQL(query);
		// System.out.println(newTable);

		writer.println("<sale_order_line>");
		writer.println("<recordNumberSaleOrderLine>" + newTable.getRowSize() + "</recordNumberSaleOrderLine>");
		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);
			writer.println("<line  >");
			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();
				if (name.equals("so_desc")) {
					if (value.contains("F&K")) {
						value = value.replace("F&K", "FnK");
					}
					if (value.contains("&")) {
						value = value.replace("&", "&amp; ");
					}
					if (value.contains("<")) {
						value = value.replace("<", "&lt;");
					}
					if (value.contains(">")) {
						value = value.replace(">", "&gt;");
					}
				}
				
				if (name.equals("sale_layout_cat_id") && value == null) {
					// System.out.println("null value");
					writer.print("<location dataType='string'  >");
					writer.println("<recordNumber>0</recordNumber>");
					writer.print("</location>");
				} else if (name.equals("sale_layout_cat_id") && value != null) {
					int layout_id = Integer.parseInt(value);
					printXMLBySQLLocation(writer, layout_id,i);
				}

				String dataType = newCell.getColumn().getDataType().toString();
				writer.print(
						"<" + name + "_" + i + " dataType='" + dataType + "' >" + value + "</" + name + "_" + i + ">");
			}
			writer.print("<TemplateRefListID_" + i + " dataType='varchar' >8000000D-1455041288</TemplateRefListID_" + i
					+ ">");
			writer.print("<SalesOrderLineSalesTaxCodeRefListID_" + i
					+ " dataType='varchar'>80000002-1451927059</SalesOrderLineSalesTaxCodeRefListID_" + i + ">");

			writer.println("</line>");
		}

		writer.println("</sale_order_line>");

	}
	
	public void printXMLBySQLLocation(PrintWriter writer, int layout_id,int k) {
		//
		String sql = "select name as layout_name "
				+ "From sale_layout_category "		
				+ "where id=" + layout_id;

		Query query = new Query(sql);
		PostgreSQLManager newPQ = new PostgreSQLManager();
		Table newTable = newPQ.getRowsBySQL(query);
		System.out.println(newTable);

		writer.println("<location>");
		writer.println("<recordNumberLocation>" + newTable.getRowSize() + "</recordNumberLocation>");
		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);
			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();
				

				String dataType = newCell.getColumn().getDataType().toString();
				writer.print(
						"<" + name + "_" + k + " dataType='" + dataType + "' >" + value + "</" + name + "_" + k + ">");
			}
			
		}

		writer.println("</location>");

	}

	public void printXMLBySQLSaleOrder(PrintWriter writer, Query query) {

		PostgreSQLManager newPQ = new PostgreSQLManager();
		Table newTable = newPQ.getRowsBySQL(query);
		System.out.println(newTable);
		writer.println("<sale_order>");
		writer.println("<recordNumberSaleOrder>" + newTable.getRowSize() + "</recordNumberSaleOrder>");

		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);
			writer.println("<record  >");
			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();
				String dataType = newCell.getColumn().getDataType().toString();
				if (name.equals("name")||name.equals("x_special_request")) {
					if(value==null){
						value="";
					}
					if (value.contains("F&K")) {
						value = value.replace("F&K", "FnK");
					}
					if (value.contains("&")) {
						value = value.replace("&", "&amp;");
					}
					if (value.contains("<")) {
						value = value.replace("<", "&lt;");
					}
					if (value.contains(">")) {
						value = value.replace(">", "&gt;");
					}
				}

				if (name.equals("id") && value != null) {
					int order_id = Integer.parseInt(value);
					printXMLBySQLSaleOrderLine(writer, order_id);

					// writer.print("<state_code dataType='string' >" + state +
					// "</state_code>");
				} else if (name.equals("partner_id") && value != null) {
					writer.print("<partner_id>");
					int partner_id = Integer.parseInt(value);
					System.out.println(partner_id);
					String sqlf = "id as customer_id, name as partner_name, "
							+ "street as partner_street,street2 as partner_street2,"
							+ "city as partner_city,zip as partner_zip,state_id as partner_state_id";
					String sql_pid = "SELECT " + sqlf + " FROM res_partner WHERE id=" + partner_id;

					Query query_pid1 = new Query(sql_pid);
					printXMLBySQLContact(writer, query_pid1, "Partner_id");
					writer.print("</partner_id >");

					// writer.print("<state_code dataType='string' >" + state +
					// "</state_code>");
				} else if (name.equals("partner_invoice_id") && value != null) {
					writer.print("<partner_invoice_id  >");
					int partner_invoice_id = Integer.parseInt(value);

					String sqlf = "id as invoice_id, name as invoice_name, "
							+ "street as invoice_street,street2 as invoice_street2,"
							+ "city as invoice_city,zip as invoice_zip,state_id as invoice_state_id";
					String sql_pid = "SELECT " + sqlf + " FROM res_partner WHERE id=" + partner_invoice_id;

					Query query_pid1 = new Query(sql_pid);
					printXMLBySQLContact(writer, query_pid1, "Invoice_id");
					writer.print("</partner_invoice_id >");
					// writer.print("<state_code dataType='string' >" + state +
					// "</state_code>");
				} else if (name.equals("partner_shipping_id") && value != null) {
					writer.print("<partner_shipping_id  >");
					int partner_shipping_id = Integer.parseInt(value);

					String sqlf = "id as shipping_id, name as shipping_name, street as shipping_street,"
							+ "street2 as shipping_street2,city as shipping_city,zip as shipping_zip,state_id as shipping_state_id";
					String sql_pid = "SELECT " + sqlf + " FROM res_partner WHERE id=" + partner_shipping_id;

					Query query_pid1 = new Query(sql_pid);
					printXMLBySQLContact(writer, query_pid1, "Shipping_id");
					writer.print("</partner_shipping_id >");
					// writer.print("<state_code dataType='string' >" + state +
					// "</state_code>");
				} else {
					writer.print("<" + name + " dataType='" + dataType + "' >" + value + "</" + name + ">");
				}

			}
			writer.print("<TemplateRefListID dataType='varchar' >8000000D-1455041288</TemplateRefListID>");
			writer.print(
					"<SalesOrderLineSalesTaxCodeRefListID dataType='varchar'>80000002-1451927059</SalesOrderLineSalesTaxCodeRefListID>");
			writer.println("</record>");
		}

		writer.println("</sale_order>");

	}

	public void printXMLBySQLItemCost(PrintWriter writer, int pt_id) {

		String sql = "SELECT id as cost_id, value_float as cost_value, name, res_id " + "FROM ir_property "
				+ "WHERE name='standard_price'" + "AND res_id='product.template," + pt_id + "'";
		Query query = new Query(sql);
		PostgreSQLManager newPQ = new PostgreSQLManager();
		Table newTable = newPQ.getRowsBySQL(query);
		System.out.println(newTable);
		writer.println("<cost>");
		writer.println("<recordNumberProductCost>" + newTable.getRowSize() + "</recordNumberProductCost>");

		// writer.println("<result>");

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
		if (newTable.getRowSize() == 0) {
			writer.print("<cost_value  dataType='float8'>0</cost_value>");
		}

		writer.println("</cost>");

	}

	public void printXMLBySQLIncomeAccount(PrintWriter writer, int pt_id) {

		// SELECT id,value_reference,res_id FROM ir_property where
		// name='property_account_income'
		String sql = "SELECT id as income_id, value_reference, name, res_id " + "FROM ir_property "
				+ "WHERE name='property_account_income'" + "AND res_id='product.template," + pt_id + "'";
		Query query = new Query(sql);
		PostgreSQLManager newPQ = new PostgreSQLManager();
		Table newTable = newPQ.getRowsBySQL(query);
		System.out.println(newTable);
		writer.println("<incomeAccount>");
		writer.println("<recordNumberIncome>" + newTable.getRowSize() + "</recordNumberIncome>");

		// writer.println("<result>");

		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);

			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);

				String name = newCell.getColumnName();
				String value = newCell.getValue();
				String dataType = newCell.getColumn().getDataType().toString();
				if (name.equals("value_reference")) {
					value = value.replace("account.account,", "");
					int account_id = Integer.parseInt(value);
					printXMLBySQLAccountIncome(writer, account_id);
				}
				writer.print("<" + name + " dataType='" + dataType + "' >" + value + "</" + name + ">");
			}

		}

		writer.println("</incomeAccount>");

	}

	public void printXMLBySQLExpenseAccount(PrintWriter writer, int pt_id) {

		String sql = "SELECT id as income_id, value_reference, name, res_id " + "FROM ir_property "
				+ "WHERE name='property_account_expense'" + "AND res_id='product.template," + pt_id + "'";
		Query query = new Query(sql);
		PostgreSQLManager newPQ = new PostgreSQLManager();
		Table newTable = newPQ.getRowsBySQL(query);
		System.out.println(newTable);
		writer.println("<expenseAccount>");
		writer.println("<recordNumberExpense>" + newTable.getRowSize() + "</recordNumberExpense>");

		// writer.println("<result>");

		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);

			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();
				String dataType = newCell.getColumn().getDataType().toString();
				if (name.equals("value_reference")) {
					value = value.replace("account.account,", "");
					int account_id = Integer.parseInt(value);
					printXMLBySQLAccountExpense(writer, account_id);
				}
				writer.print("<" + name + " dataType='" + dataType + "' >" + value + "</" + name + ">");
			}

		}

		writer.println("</expenseAccount>");

	}

	public void printXMLBySQLAccountIncome(PrintWriter writer, int account_id) {

		// SELECT id,value_reference,res_id FROM ir_property where
		// name='property_account_income'
		String sql = "select code as accountIncome_code from account_account where id=" + account_id;
		Query query = new Query(sql);
		PostgreSQLManager newPQ = new PostgreSQLManager();
		Table newTable = newPQ.getRowsBySQL(query);
		System.out.println(newTable);
		writer.println("<account_Income>");
		writer.println("<recordNumberAccountIncome>" + newTable.getRowSize() + "</recordNumberAccountIncome>");

		// writer.println("<result>");

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

		writer.println("</account_Income>");

	}

	public void printXMLBySQLAccountExpense(PrintWriter writer, int account_id) {

		// SELECT id,value_reference,res_id FROM ir_property where
		// name='property_account_income'
		String sql = "select code as accountExpense_code from account_account where id=" + account_id;
		Query query = new Query(sql);
		PostgreSQLManager newPQ = new PostgreSQLManager();
		Table newTable = newPQ.getRowsBySQL(query);
		System.out.println(newTable);
		writer.println("<account_Expense>");
		writer.println("<recordNumberAccountExpense>" + newTable.getRowSize() + "</recordNumberAccountExpense>");

		// writer.println("<result>");

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

		writer.println("</account_Expense>");

	}

	public void printXMLBySQLProduct(PrintWriter writer, Query query) {

		PostgreSQLManager newPQ = new PostgreSQLManager();
		Table newTable = newPQ.getRowsBySQL(query);
		System.out.println(newTable);

		writer.println("<product  >");
		writer.println("<recordNumberProduct>" + newTable.getRowSize() + "</recordNumberProduct>");
		System.out.println("size:" + newTable.getRowSize());

		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);
			writer.println("<record  >");
			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();
				String dataType = newCell.getColumn().getDataType().toString();
				if (name.equals("description_sale") || name.equals("description_purchase")
						|| name.equals("name_template")||name.equals("product_name")) {
					
					
					if(value==null){
						value=" ";
					}
						
					if (value.contains("F&K")) {
						value = value.replace("F&K", "FnK");
					}
					if (value.contains("&")) {
						value = value.replace("&", "&amp; ");
					}
					if (value.contains("<")) {
						value = value.replace("<", "&lt;");
					}
					if (value.contains(">")) {
						value = value.replace(">", "&gt;");
					}
					}
				
				if (name.equals("categ_id")) {
					int categ_id = Integer.parseInt(value);
					printXMLBySQLCategory(writer, categ_id);
				}

				writer.print("<" + name + " dataType='" + dataType + "' >" + value + "</" + name + ">");
				if (name.equals("pt_id") && value == null) {
					// System.out.println("null value");
					writer.print("<supplier_info dataType='string'  >");
					writer.println("<recordNumber>0</recordNumber>");
					writer.print("</supplier_info>");
				} else if (name.equals("pt_id") && value != null) {
					int pt_id = Integer.parseInt(value);
					printXMLBySQLSupplier(writer, pt_id);
					printXMLBySQLItemCost(writer, pt_id);
					printXMLBySQLIncomeAccount(writer, pt_id);
					printXMLBySQLExpenseAccount(writer, pt_id);
					writer.print("<accountasset_code dataType=\"varchar\">12100</accountasset_code>");
					// writer.print("<state_code dataType='string' >" + state +
					// "</state_code>");
				}

			}

			writer.println("</record>");
		}

		writer.println("</product>");

	}

	private void printXMLBySQLCategory(PrintWriter writer, int categ_id) {
		// TODO Auto-generated method stub
		String sql = "SELECT id as product_categ_id, x_hs_code_category_cn,x_hs_code_category_us,name as product_categ_name  " + "FROM product_category "
				+ "WHERE id=" + categ_id;
		Query query = new Query(sql);
		PostgreSQLManager newPQ = new PostgreSQLManager();
		Table newTable = newPQ.getRowsBySQL(query);
		System.out.println(newTable);
		writer.println("<category>");
		writer.println("<recordNumberCategory>" + newTable.getRowSize() + "</recordNumberCategory>");

		// writer.println("<result>");

		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);

			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();
				String dataType = newCell.getColumn().getDataType().toString();

				if (name.equals("product_categ_name")) {
					if (value.contains("F&K")) {
						value = value.replace("F&K", "FnK");
					} else if (value.contains("&")) {
						value = value.replace("&", "&amp;");
					}
					if (value.contains("<")) {
						value = value.replace("<", "&lt;");
					}
					if (value.contains(">")) {
						value = value.replace(">", "&gt;");
					}
				}
				writer.print("<" + name + " dataType='" + dataType + "' >" + value + "</" + name + ">");
			}

		}
		// if(newTable.getRowSize()==0){
		// writer.print("<cost_value dataType='float8'>0</cost_value>");
		// }

		writer.println("</category>");
	}

	public void printXMLBySQLSupplier(PrintWriter writer, int supp_id) {

		String sql = "select pt.id as pt_id,ps.name as supp_id,ps.product_name,ps.product_code,ps.sequence,part.name as supp_name "
				+ "from product_supplierInfo as ps " + "join product_template as pt " + "on pt.id=ps.product_tmpl_id "
				+ "join res_partner as part " + "ON ps.name = part.id " + "WHERE pt.id=" + supp_id + " LIMIT 1";
		Query query = new Query(sql);
		PostgreSQLManager newPQ = new PostgreSQLManager();
		Table newTable = newPQ.getRowsBySQL(query);
		// System.out.println(newTable);
		writer.println("<supplier_info  >");
		writer.println("<recordNumberSupplier>" + newTable.getRowSize() + "</recordNumberSupplier>");

		//

		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);
			// writer.println("<supplier_info>");
			// writer.println("<recordNumber>" + newTable.getRowSize() +
			// "</recordNumber>");
			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();
				String dataType = newCell.getColumn().getDataType().toString();
//				if (name.equals("supp_name")||name.equals("product_name")) {
//					if(!value.equals("null")||!(value==null)){
//						if (value.contains("F&K")) {
//							value = value.replace("F&K", "FnK");
//						} else if (value.contains("&")) {
//							value = value.replace("&", "&amp;");
//						}
//						if (value.contains("<")) {
//							value = value.replace("<", "&lt;");
//						}
//						if (value.contains(">")) {
//							value = value.replace(">", "&gt;");
//						}
//					}
//					
//				}
				if (name.equals("supp_name")||name.equals("product_name")){
					if(value==null){
						value="";
					}
					if (value.contains("F&K")) {
						value = value.replace("F&K", "FnK");
					}
					if (value.contains("&")) {
						value = value.replace("&", "&amp;");
					}
					if (value.contains("<")) {
						value = value.replace("<", "&lt;");
					}
					if (value.contains(">")) {
						value = value.replace(">", "&gt;");
					}
				}
				
				writer.print("<" + name + " dataType='" + dataType + "' >" + value + "</" + name + ">");
			}
			// writer.println("</supplier_info>");
		}

		writer.println("</supplier_info>");

	}

	public void printXMLBySQLContact(PrintWriter writer, Query query, String tag) {

		PostgreSQLManager newPQ = new PostgreSQLManager();
		Table newTable = newPQ.getRowsBySQL(query);
		// System.out.println(newTable);

		writer.println("<contact>");
		writer.println(
				"<recordNumberContact" + tag + ">" + newTable.getRowSize() + "</recordNumberContact" + tag + ">");
		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);
			writer.println("<row  >");
			String state = "";
			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();
				String dataType = newCell.getColumn().getDataType().toString();
				// System.out.println(name+":"+value);
				if (name.equals("name")||name.equals("email")||name.equals("partner_name")||name.equals("invoice_name")||name.equals("shipping_name")) {
					System.out.println("name:"+name+",value:"+value);
					if(value==null){
						value="blank@blank.com";
					}
					byte ptext[] = value.getBytes();
					String newValue = new String(ptext, UTF_8);
					if (newValue.contains("F&K")) {
						newValue = value.replace("F&K", "FnK");
					} 
					if (newValue.contains("&")) {
						
						newValue = newValue.replace("&", "&amp; ");
						System.out.println(newValue);
						// System.exit(0);
					}
					if (newValue.contains("<")) {
						newValue = newValue.replace("<", "&lt; ");
					}
					if (newValue.contains(">")) {
						newValue = newValue.replace(">", "&gt; ");
					}
					

					writer.print("<" + name + " dataType='" + dataType + "' >" + newValue + "</" + name + ">");

				}

				else {
					writer.print("<" + name + " dataType='" + dataType + "' >" + value + "</" + name + ">");
				}
				if (name.equals("state_id") && value == null) {
					// System.out.println("null value");
					writer.println("<res_state dataType='string'  >");
					writer.println("<recordNumber>0</recordNumber>");
					writer.println("</res_state>");

				} else if (name.equals("state_id") && value != null) {
					int state_id = Integer.parseInt(value);
					printXMLBySQLState(writer, state_id);
					// writer.print("<state_code dataType='string' >" + state +
					// "</state_code>");
				}
			}

			writer.println("</row>");
		}

		writer.println("</contact>");

	}

	public void printXMLBySQLState(PrintWriter writer, int state_id) {

		String sql = "SELECT code as state_code,name as state_name,country_id " + "FROM res_country_state "
				+ "WHERE id=" + state_id;
		Query query = new Query(sql);
		PostgreSQLManager newPQ = new PostgreSQLManager();
		Table newTable = newPQ.getRowsBySQL(query);
		// System.out.println(newTable);
		writer.println("<res_state  >");
		writer.println("<recordNumberState>" + newTable.getRowSize() + "</recordNumberState>");

		// writer.println("<result>");

		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);

			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();
				String dataType = newCell.getColumn().getDataType().toString();
				writer.print("<" + name + " dataType='" + dataType + "' >" + value + "</" + name + ">");
				if (name.equals("country_id") && value == null) {
					System.out.println("null value");
					writer.print("<code dataType='string' >null</code>");
				} else if (name.equals("country_id") && value != null) {
					int country_id = Integer.parseInt(value);
					printXMLBySQLCountry(writer, country_id);
					// writer.print("<state_code dataType='string' >" + state +
					// "</state_code>");
				}

			}

		}

		// writer.println("</result>");
		writer.println("</res_state>");

	}

	public void printXMLBySQLCountry(PrintWriter writer, int country_id) {

		String sql = "SELECT code as country_code,name as country_name " + "FROM res_country " + "WHERE id="
				+ country_id;
		Query query = new Query(sql);
		PostgreSQLManager newPQ = new PostgreSQLManager();
		Table newTable = newPQ.getRowsBySQL(query);
		// System.out.println(newTable);
		writer.println("<res_country  >");
		writer.println("<recordNumberCountry>" + newTable.getRowSize() + "</recordNumberCountry>");

		// writer.println("<result>");

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

		// writer.println("</result>");
		writer.println("</res_country>");

	}

	public static void main(String argsp[]) {
		PostgreSQLManager newPQ = new PostgreSQLManager();

		String tableName = "sale_order";
		String columName = "id";
		String sql1 = "SELECT pp.name_template,pp.id,pp.product_tmpl_id as pt_id,pt.description_sale,pt.description_purchase,pt.list_price,pt.write_date "
				+ "FROM product_product as pp " + "JOIN product_template as pt " + "on pp.product_tmpl_id =pt.id"
				+ "	ORDER BY pp.name_template, pt.description_sale";
		String sql = "SELECT * FROM res_partner WHERE customer=true AND is_company = ? ";
		String sql2 = "SELECT * FROM res_partner WHERE id=?";
		String sql3 = "SELECT * FROM res_partner WHERE name =?";
		String sql4 = "SELECT * FROM res_partner";
		// String sql5=" where "+columnName;
		// Table newTable = newPQ.getColumnsByTableName(tableName);
		// newTable = newPQ.getRowsByTableName(tableName);
		Arg arg = new Arg(DataType.Boolean, "id", true);
		Arg arg2 = new Arg(DataType.Int, tableName, 8);
		// Arg arg3 = new Arg(DataType.STRING,"China Export");
		ArrayList<Arg> args = new ArrayList<>();
		args.add(arg2);
		Query query = new Query(sql2, args);
		System.out.println(arg);

		Table newTable = newPQ.getRowsBySQL(query);
		// newPQ.printXMLBySQL(writer, query);
		System.out.println(newTable.getColumnSize());
		for (int i = 0; i < newTable.getRowSize(); i++) {
			Row newRow = newTable.getRows().get(i);
			for (int j = 0; j < newTable.getColumnSize(); j++) {
				Cell newCell = newRow.getCells().get(j);
				String name = newCell.getColumnName();
				String value = newCell.getValue();

				// System.out.println(name+":"+value);
				if (name.equals("description_purchase")) {

					if (value.contains("F&K")) {
						value = value.replace("F&K", "FnK");
					} else if (value.contains("&")) {
						value = value.replace("&", "&amp;");
					}
					if (value.contains("<")) {
						value = value.replace("<", "&lt;");
					}
					if (value.contains(">")) {
						value = value.replace(">", "&gt;");
					}

				}
				System.out.println("(" + name + "," + value + ")");
				if (name.equals("state_id") && value == null) {
					System.out.println("null value");
					System.exit(0);
				}
			}
			System.out.println("");
		}
	}

}
