package com.aionsoft.qblink.controller.quickbooks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aionsoft.qblink.controller.database.ConnectionManager;
import com.aionsoft.qblink.helper.LoggerManager;
import com.aionsoft.qblink.helper.Processor;
import com.aionsoft.qblink.model.base.Row;
import com.aionsoft.qblink.model.quickbooks.Customer;
import com.aionsoft.qblink.model.quickbooks.Product;
import com.aionsoft.qblink.model.quickbooks.ProductCategory;
import com.aionsoft.qblink.processor.Generator;

public class ProductManager extends QBManager {
	private static Connection conn = ConnectionManager.getInstance().getConnection();
	// private static LoggerManager LOGGER = LoggerManager.getInstance();
	private String insertFieldNames;
	private String sqlMessage;
	public final static Logger LOGGER = Logger.getLogger(ProductManager.class.getName());

	private ProductCategory product_category = new ProductCategory();

	// setting product type
	boolean serviceProduct = false;
	boolean discountProduct = false;
	boolean inventoryProduct = false;
	boolean nonInventoryProduct = false;

	public ProductManager() {
		this.serviceProduct = false;
		this.discountProduct = false;
		this.inventoryProduct = false;
		this.nonInventoryProduct = false;
	}

	public boolean insertProduct(Product bean) throws SQLException {
		
		System.out.println("ProductManger.InsertProduct()");
		System.out.println(bean.getProductName());
		String productName = bean.getProductName();
		String productType = bean.getProductType().toString();
		
		String productID = bean.getProductOdooID();
		System.out.println("..........................."+productType);
		String SalesOrPurchaseAccountRefListID ="";
		String SalesORPurchaseDesc="";
		String SalesOrPurchasePrice="";
		
		String sqltext="";
		/**
		 * algorithm for insert product 1. check for product existence by name
		 * and id if product exist(do nothing) else(add product) 2. check for
		 * product product parent, if parent exist(do nothing), else (add
		 * parent) 3. check for product vendor, if exist(do nothing), else(add
		 * vendor )
		 */

		// LOGGER.setLevel(Level.INFO);
		// LOGGER.info("INSERT PRODUCT");
		System.out.println("ProductManager-insertProduct");
		String sqlFieldNames = "";// sqlfieldName
		String sqlFieldValues = "";// values

		// insert field values
		int j = 0;
		// get product name and id;
//		String productName = bean.getProductName();
//		String productId = bean.getProductOdooID();

		/**
		 * algorithm for insert product 1. check for product existence by name
		 * and id if product exist(do nothing) else(add product)
		 */
		if(productType.equals("stock")){
		if (bean.getRows().get(0).getCells().size() > 0) {
			// System.out.println(x);
			Row row = bean.getRows().get(0);
			// System.out.println(row);
			for (j = 0; j < row.getCells().size() - 1; j++) {

				// check if the column is needed to insert into quickbooks
				boolean insert = row.getCells().get(j).getColumn().isInsert();

				// get the name type and value of the columsn
				String name = row.getCells().get(j).getColumnName();
				String type = row.getCells().get(j).getColumn().getDataType();
				String value = row.getCells().get(j).getValue();
				// LOGGER.info("PRODUCT INFO:"+name+","+value+","+type);
				System.out.println(j+"\tPRODUCT INFO:" + name + "," + value + "," + type);
				
				if (insert) {
					// if name equals any of the accounts, need to look up their
					// cooresondign id
					// value = setProductParent(name, value);
					// setProductParent
					// value = setProductParent(name, value);

					/**
					 * 2. check for product product parent, if parent exist(do
					 * nothing), else (add parent)
					 */

					/**
					 * 3. check for product account information
					 */

					if(value.contains("бу")){
						value=value.replace("бу", "DEG");
					}

					if (name.equals("IncomeAccountRefListID") || name.equals("COGSAccountRefListID")
							|| name.equals("AssetAccountRefListID")) {
						// check for product account information to insert
						value = getRefListID(value);
						
						if(name.equals("IncomeAccountRefListID")){
							SalesOrPurchaseAccountRefListID=value;
						}
					
						System.out.println("PRODUCT INFO:" + name + "," + value + "," + type);
					}
					
					if(name.equals("PrefVendorRefListID")){
						value = getPreferedVendorID(value);
						
						if(value.equals("null")||value==null){
							value="";
						}
					}
					
					
					if(value.contains("'")){
						System.out.println("replacing values");
						value = value.replace("'", "''");
//						System.out.println(value);
					}
					if(name.equals("SalesDesc")){
						SalesORPurchaseDesc=value;
					}
					if(name.equals("SalesPrice")){
						SalesOrPurchasePrice=value;
					}

					sqlFieldNames = sqlFieldNames + "\"" + name + "\", ";
					if (type.equals("String")) {
						sqlFieldValues = sqlFieldValues + "'" + value + "',";
					} else {
						sqlFieldValues = sqlFieldValues + value + ",";
					}

				} else {

					System.out.println("Product Manager - product not inserted");

					// LOGGER.info("not inserted");
				}

			}
		}
		sqlFieldNames = sqlFieldNames + bean.getColumn(bean.getColumnSize() - 1).getColumnName();
		sqlFieldValues = sqlFieldValues + bean.getRows().get(0).getCells().get(j).getValue();


		this.insertFieldNames = new String(sqlFieldNames);
		this.sqlMessage = new String(sqlFieldValues);
		
		sqltext = "INSERT into ItemInventory(" + insertFieldNames + ")" + "VALUES (" + sqlMessage + ")";

		}else if (productType.equals("service")){
			if (bean.getRows().get(0).getCells().size() > 0) {
				// System.out.println(x);
				Row row = bean.getRows().get(0);
				// System.out.println(row);
				for (j = 0; j < row.getCells().size() - 1; j++) {

					// check if the column is needed to insert into quickbooks
					boolean insert = row.getCells().get(j).getColumn().isInsert();

					// get the name type and value of the columsn
					String name = row.getCells().get(j).getColumnName();
					String type = row.getCells().get(j).getColumn().getDataType();
					String value = row.getCells().get(j).getValue();
					// LOGGER.info("PRODUCT INFO:"+name+","+value+","+type);
					System.out.println("PRODUCT INFO:" + name + "," + value + "," + type);
					
					if (insert) {
							if (name.equals("IncomeAccountRefListID") || name.equals("COGSAccountRefListID")
								|| name.equals("AssetAccountRefListID")) {
							// check for product account information to insert
							value = getRefListID(value);
							
							if(name.equals("IncomeAccountRefListID")){
								SalesOrPurchaseAccountRefListID=value;
							}
						
//							System.out.println("PRODUCT INFO:" + name + "," + value + "," + type);
						}				
						
						if(value.contains("'")){
							System.out.println("replacing values");
							value = value.replace("'", "''");
//							System.out.println(value);
						}
						if(name.equals("SalesDesc")){
							SalesORPurchaseDesc=value;
						}
						if(name.equals("SalesPrice")){
							SalesOrPurchasePrice=value;
						}
					}
				}
			}

			
			sqltext = "INSERT INTO ItemService " 
					+ "(\"Name\", " 
					+ "\"IsActive\", "
					+ "\"SalesORPurchaseDesc\", "
					+ "\"SalesOrPurchasePrice\", " 
					+ "\"CustomFieldodoo_id\", " 
					+ "\"SalesOrPurchaseAccountRefListID\" "
					+ ") "					
					+ "VALUES " 
					+ "('" + productName// NAME					
					+ "', " + "TRUE, "// ACTIVE
					+ "'"+SalesORPurchaseDesc
					+ "', "// SALE DESC
					+ SalesOrPurchasePrice+", '"// SALE PRICE
					+ productID+"', "
					+ "'"+SalesOrPurchaseAccountRefListID
					+"'"// SalesOrPurchaseAccountRefListID
					+ ")";
			
		}
		LOGGER.info(sqltext);

		
		boolean insertSuccess = new QBManager().insert(sqltext);
		
		if (insertSuccess) {
			System.out.println("ProductManager - Product insert Successful");
//			return true;
			// LOGGER.info("PRODUCT INSERT SUCCESSFUL");
		} else {
			System.out.println("ProductManger - Product insert failed");
			// LOGGER.info("PRODUCT INSERT FAILED");

			return false;
		}

		String sqltextUpdate = "";
		// System.out.println("\n"+sqltextUpdate);
		
		
		String parentRefListID = bean.getParentRefListID();
		String parentRefID = getParentRefListID(parentRefListID);
		
		String updateSQL ="";
		if(productType.equals("stock")){
			updateSQL=	"update  ItemInventory set ParentRefListID='"+parentRefID+"' where FullName='"+productName+"'";		
		}
		else if(productType.equals("service")){
			updateSQL=	"update  ItemService set ParentRefListID='"+parentRefID+"' where FullName='"+productName+"'";		
		}

		
		
		
		System.out.println(updateSQL);
		
		
		
		

		
		boolean updateParent = new QBManager().insert(updateSQL);
		
		if (updateParent) {
			System.out.println("ProductManager - Product Add Parent Successful");
			// LOGGER.info("PRODUCT INSERT SUCCESSFUL");
		} else {
			System.out.println("ProductManger - Product Add Parent Failed");
			// LOGGER.info("PRODUCT INSERT FAILED");

			return false;
		}
//
//		// boolean update = new QBManager().update(sqltextUpdate);
		if (insertSuccess&&updateParent) {
			// LOGGER.info("update & insert successful");

			System.out.println("Update and insert successful");
			return true;
		}
		return false;

	}

	private String setProductParent(String name, String value) {
		if (name.equals("ParentRefFullName")) {
			this.product_category.setCategory_name(value);
		}

		if (name.equals("ParentRefListID")) {
			this.product_category.setCategory_id(value);
			String newValue = getParentRefListID(value);

			// this.product_category.setCategory_id(newValue);
			if (newValue == null) {
				// insert new product
				boolean insertProductCategory = insertProductCategory(product_category);
				newValue = getParentRefListID(value);
				if (insertProductCategory) {
					value = newValue;
				} else {
					LOGGER.setLevel(Level.WARNING);
					LOGGER.warning("failed to insert Product Category");
					// System.out.println();
					// System.exit(0);
				}

			}
			value = newValue;

		}
		return value;
	}

	private String getAccount(String name, String value) {
		if (name.equals("IncomeAccountRefListID") || name.equals("COGSAccountRefListID")
				|| name.equals("AssetAccountRefListID")) {
			String newValue = getRefListID(value);
			System.out.println(name + "," + newValue);
			if (newValue == "null") {
				value = newValue; // replace value with the new account
			} else {
				value = newValue;

			}

		}
		return value;
	}

	/***
	 * This section is complete
	 * @param bean
	 * @return
	 */
	public boolean insertProductCategory(ProductCategory bean) {
		String name = bean.getCategory_name();
		String id = bean.getCategory_id();
		String type = bean.getType();
		System.out.println("..........................."+type);
		String sqltext="";
		if(type.equals("product")){
			sqltext = "INSERT INTO ItemInventory " + "(\"Name\", " + "\"IsActive\", " + "\"SalesDesc\", "
					+ "\"SalesPrice\", " + "\"IncomeAccountRefListID\", " + "\"PurchaseDesc\", " + "\"PurchaseCost\", "
					+ "\"COGSAccountRefListID\", " + "\"AssetAccountRefListID\", " + "\"customFieldcateg_id\"" + ") "
					+ "VALUES " + "('" + name// NAME
					+ "', " + "TRUE, "// ACTIVE
					+ "'do not use', "// SALE DESC
					+ "0, "// SALE PRICE
					+ "'80000006-1451927127', "// INCOME ACCOUNT
					+ "'do not use', " // PURCHASE DESCRIPTION
					+ "0, "// PURCHASE COST
					+ "'80000023-1451927503', "// COGS ACCOUNT
					+ "'80000036-1451967470', "// ASSET ACCOUNT
					+ "'" + id + "'"// CATEG_ID
					+ ")";
			//do one
		}else if (type.equals("service")){
			sqltext = "INSERT INTO ItemService " + "(\"Name\", " + "\"IsActive\", "
					+ "\"SalesORPurchaseDesc\", "
					+ "\"SalesOrPurchasePrice\", " 
					+ "\"SalesOrPurchaseAccountRefListID\", "
					+ "\"customFieldcateg_id\"" + ") "					
					+ "VALUES " + "('" + name// NAME					
					+ "', " + "TRUE, "// ACTIVE
					+ "'do not use', "// SALE DESC
					+ "0, "// SALE PRICE
					+ "'80000006-1451927127', "// SalesOrPurchaseAccountRefListID
					+ "'" + id + "'"// CATEG_ID
					+ ")";
		}
		
		
		
		System.out.println(sqltext);
		boolean insert = new QBManager().insert(sqltext);
		return insert;
	}

	private String getParentRefListID(String value) {
		// TODO Auto-generated method stub
		String sql = "SELECT ListID FROM item WHERE customfieldcateg_id='" + value + "'";
		ResultSet rs = null;
		try (
				// PreparedStatement stmt = conn.prepareStatement(sql);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// rs = stmt.executeQuery("SELECT * FROM customer WHERE name='jason'");

		) {
			rs = stmt.executeQuery(sql);
			// rs.beforeFirst();
			if (rs.next()) {
				String name = rs.getString("ListID");
				// System.out.println("return true\t"+name);
				return name;
			} else {
				System.out.println("return false");
				return null;
			}

		} catch (SQLException e) {
			System.out.println("Exception Occured");
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
			return null;
		}

	}
	
	private String getPreferedVendorID(String value){
		
		String sql = "SELECT * FROM vendor where AccountNumber ='" + value + "'";
		ResultSet rs = null;
		try (
				// PreparedStatement stmt = conn.prepareStatement(sql);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// rs = stmt.executeQuery("SELECT * FROM customer WHERE name='jason'");

		) {
			rs = stmt.executeQuery(sql);
			// rs.beforeFirst();
			if (rs.next()) {
				String name = rs.getString("ListID");
				// System.out.println("return true\t"+name);
				return name;
			} else {
				System.out.println("CANNOT FIND ACCOUNT!ProductManagerPreferedVendorID");
				return null;
			}

		} catch (SQLException e) {
			System.out.println("Exception Occured");
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
			return null;
		}
	}

	private String getRefListID(String value) {
		// TODO Auto-generated method stub
		String sql = "SELECT ListID FROM account WHERE accountNumber='" + value + "'";
		System.out.println(sql);
		ResultSet rs = null;
		try (
				// PreparedStatement stmt = conn.prepareStatement(sql);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// rs = stmt.executeQuery("SELECT * FROM customer WHERE name='jason'");

		) {
			rs = stmt.executeQuery(sql);
			// rs.beforeFirst();
			if (rs.next()) {
				String name = rs.getString("ListID");
				// System.out.println("return true\t"+name);
				return name;
			} else {
				System.out.println("CANNOT FIND ACCOUNT!ProductManager.getRefListID");
				return null;
			}

		} catch (SQLException e) {
			System.out.println("Exception Occured");
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
			return null;
		}

	}

	public boolean existProduct(String id) {

		return false;

	}

	public static void main(String[] args) {

		try {
			boolean insert = new ProductManager().insertProduct(new Product());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean existByIdCategory(String value) {
		boolean existence = false;
		String sql1 = "SELECT * FROM item WHERE CustomFieldcateg_id='" + value + "'";
		ResultSet rs = null;
		existence = exist(sql1);
		return existence;
	}

	public boolean existByIdCategoryName(String value) {
		boolean existence = false;
		String sql1 = "SELECT * FROM item WHERE name='" + value + "'";
		ResultSet rs = null;
		existence = exist(sql1);
		return existence;
	}

	public Boolean existById(String value) {

//		System.out.println("exist by id");
		boolean existence = false;
		String sql1 = "SELECT * FROM item WHERE CustomFieldodoo_id='" + value + "'";
		ResultSet rs = null;
		existence = exist(sql1);
		return existence;
	}

	public boolean existByProductName(String value) {
//		System.out.println("exist by name");
		boolean existence = false;
		String sql1 = "SELECT * FROM item WHERE name='" + value + "'";
		ResultSet rs = null;
		existence = exist(sql1);
		return existence;
	}
	public String getTypeByProductID(String id){
		
//		Select type,listId from item
		
		String sql = "Select type,listId from item WHERE listId='" + id + "'";
		
		ResultSet rs = null;
		try (
				// PreparedStatement stmt = conn.prepareStatement(sql);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// rs = stmt.executeQuery("SELECT * FROM customer WHERE name='jason'");

		) {
			rs = stmt.executeQuery(sql);
			// rs.beforeFirst();
			if (rs.next()) {
				String type = rs.getString("type");
				// System.out.println("return true\t"+name);
				return type;
			} else {
				System.out.println("return false");
				return null;
			}

		} catch (SQLException e) {
			System.out.println("Exception Occured");
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
			return null;
		}
	}
}
