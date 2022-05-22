package com.yeditepe.bookrez.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.yeditepe.bookrez.SQLQueries.ExpiredReservationQueries;
import com.yeditepe.bookrez.helper.Database;

public class ExpiredReservationDao 
extends BaseDao 
implements ExpiredReservationQueries

{

	// Dependency Injection
	public ExpiredReservationDao(Connection connection) {
		databaseConnection = connection;
	}

	// Default Constructor
	public ExpiredReservationDao() {
		this(Database.getConnection());
	}

	public Boolean isReservationExpired(Integer id) {
		try {
			PreparedStatement statement = databaseConnection.prepareStatement(GET_EXPIRED_RESERVATIONS_FOR_ID);
			statement.setInt(1, id);
			return statement.executeQuery().next();
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println("Connection Problem");
		}
		return false;
	}

	public Boolean expireReservation(Integer id) {
		try {
			PreparedStatement statement = databaseConnection.prepareStatement(ADD_EXPIRED_RESERVATION);
			statement.setInt(1, id);
			return statement.executeUpdate() > 0;

		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println("Connection Problem");
		}

		return false;
	}
}
