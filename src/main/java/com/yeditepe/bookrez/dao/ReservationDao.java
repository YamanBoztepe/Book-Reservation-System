package com.yeditepe.bookrez.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.yeditepe.bookrez.SQLQueries.ReservationQueries;
import com.yeditepe.bookrez.helper.Database;
import com.yeditepe.bookrez.model.Reservation;

public class ReservationDao 
extends BaseDao 
implements ReservationQueries

{
	// Dependency Injection
	public ReservationDao(Connection connection) {
		databaseConnection = connection;
	}
	
	// Default Constructor
	public ReservationDao() {
		this(Database.getConnection());
	}
	
	public boolean addReservation(Integer bookId, Integer userId, LocalDate startDate, LocalDate endDate) {
		try {
			PreparedStatement statement = databaseConnection.prepareStatement(ADD_RESERVATION);
			statement.setInt(1, bookId);
			statement.setInt(2, userId);
			Date start_date = Date.valueOf(startDate);
			statement.setDate(3, start_date);
			Date end_date = Date.valueOf(endDate);
			statement.setDate(4, end_date);
			return statement.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println("Connection Problem");
		}
		
		return false;
	}

	public ArrayList<Reservation> getAllReservations() {
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		try {
			PreparedStatement statement = databaseConnection.prepareStatement(GET_RESERVATIONS);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Integer resId = result.getInt("r_id");
				Integer bookId = result.getInt("b_id");
				LocalDate startDate = result.getDate("start_date").toLocalDate();
				LocalDate endDate = result.getDate("end_date").toLocalDate();
				Reservation reservation = new Reservation(resId, bookId, null, startDate, endDate);
				reservations.add(reservation);
			}
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println("Connection Problem");
		}
		return reservations;
	}

	public ArrayList<Reservation> getReservations(Integer userId) {
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		try {
			PreparedStatement statement = databaseConnection.prepareStatement(GET_RESERVATIONS_FOR_ID);
			statement.setInt(1, userId);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Integer resId = result.getInt("r_id");
				Integer bookId = result.getInt("b_id");
				LocalDate startDate = result.getDate("start_date").toLocalDate();
				LocalDate endDate = result.getDate("end_date").toLocalDate();
				Reservation reservation = new Reservation(resId, bookId, userId, startDate, endDate);
				reservations.add(reservation);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println("Connection Problem");
		}
		
		return reservations;
	}
	
}
