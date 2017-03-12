package com.alltekusa.qbLink.Controller.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/* Connection Manager for quickbooks 
 * 
 * 
 */
public class ConnectionManager {

	private static ConnectionManager instance = null;

	private final String USERNAME = "dbuser";
	private final String PASSWORD = "dbpassword";
	private static final String P_CONN_STRING = "jdbc:postgresql://localhost:5432/odoo-php-test-1";
	private static final String M_CONN_STRING = "jdbc:postgresql://localhost:5432/odoo-php-test-1";
	private static final String Q_CONN_STRING = "jdbc:odbc:quickbooks" ;	
	private static final String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
	

	private DBType dbType = DBType.QODBC;

	private Connection conn = null;

	private ConnectionManager() {
	}

	public static ConnectionManager getInstance() {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

	public void setDBType(DBType dbType) {
		this.dbType = dbType;
	}

	private boolean openConnection() throws ClassNotFoundException
	{
		try {
			switch (dbType) {
	
			case QODBC:
				Class.forName(driver);
				conn = DriverManager.getConnection(Q_CONN_STRING);
				return true;
			case PostgreSQL:
				conn = DriverManager.getConnection(P_CONN_STRING, USERNAME, PASSWORD);
				return true;

			default: 
				return false;
			}
		}
		catch (SQLException e) {
			System.err.println(e);
			return false;
		}

	}

	public Connection getConnection()
	{
		if (conn == null) {
			try {
				if (openConnection()) {
					System.out.println("\tOpening connection - Qbooks");
					return conn;
				} else {
					return null;
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}

	public void close() {
		System.out.println("\tClosing connection - Qbooks");
		try {
			conn.close();
			conn = null;
		} catch (Exception e) {
		}
	}


	
}
