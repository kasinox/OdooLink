package com.aionsoft.qblink.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;



public class LoggerManager {
	private static LoggerManager instance = null;

	private Logger logger = null;

	private LoggerManager() {
	}

	public static LoggerManager  getInstance() {
		if (instance == null) {
			instance = new LoggerManager();
		}
		return instance;
	}

	

}
