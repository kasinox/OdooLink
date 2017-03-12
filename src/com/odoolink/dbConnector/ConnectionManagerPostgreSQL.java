package com.odoolink.dbConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManagerPostgreSQL {

	private static ConnectionManagerPostgreSQL instance = null;

	private final String USERNAME = "sid";
	private final String PASSWORD = "password";
	private static final String P_CONN_STRING = "jdbc:postgresql://localhost:5432/odoo-production";
	private static final String Q_CONN_STRING = "jdbc:odbc:quickbooks" ;	
	private static final String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
	

	private DBType dbType = DBType.PostgreSQL;

	private Connection conn = null;

	private ConnectionManagerPostgreSQL() {
	}

	public static ConnectionManagerPostgreSQL getInstance() {
		if (instance == null) {
			instance = new ConnectionManagerPostgreSQL();
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
		        Class.forName("org.postgresql.Driver");
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
					System.out.println("\tOdoo Connection opened");
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
		System.out.println("Closing connection Odoo");
		try {
			conn.close();
			conn = null;
		} catch (Exception e) {
		}
	}


	
}
