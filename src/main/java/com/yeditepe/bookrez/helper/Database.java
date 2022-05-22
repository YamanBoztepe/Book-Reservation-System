package com.yeditepe.bookrez.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static Connection connection;
	private static final String database_url = "jdbc:mysql://localhost:3306/" + "BookRez";
	private static final String user = "root";
	private static final String password = "";

	private Database() {
		System.out.println("Database object is instantitiated");
	}

	public synchronized static Connection getConnection() {
		if (connection != null) {
			return connection;

		} else {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(database_url, user, password);
			} catch (ClassNotFoundException e) {
				System.out.println("Driver can not be loaded");
			} catch (SQLException e) {
				System.out.println("Database connection is not established");
			}
			return connection;
		}
	}
}
