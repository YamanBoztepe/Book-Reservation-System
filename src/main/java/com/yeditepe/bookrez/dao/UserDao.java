package com.yeditepe.bookrez.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.yeditepe.bookrez.SQLQueries.UserQueries;
import com.yeditepe.bookrez.helper.Database;
import com.yeditepe.bookrez.model.Reservation;
import com.yeditepe.bookrez.model.User;


public class UserDao 
extends BaseDao 
implements UserQueries

{
	// Dependency Injection
	public UserDao(Connection connection) {
		databaseConnection = connection;
	}
	
	// Default Constructor
	public UserDao() {
		this(Database.getConnection());
	}
	
	public User logIn(String email, String password) {
		User user = null;
		try {
			PreparedStatement statement = databaseConnection.prepareStatement(SELECT_USER);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				int userId = result.getInt("id");
				String username = result.getString("username");			
				deactiveExpiredReservations(); // Quantity will increase if end date is over
				user = new User(userId, username, email, password);
			}
			
		} catch(SQLException e) {
			System.out.println("Connection Problem");
		}
		return user;
	}
	
	public int signUp(String username, String password, String email)  {
		int result = 0;
		if (isUserExists(email)) {
			return result;
		}
		
		try {
			PreparedStatement statement = databaseConnection.prepareStatement(ADD_USER);
			statement.setString(1, username);
			statement.setString(2, email);
			statement.setString(3, password);
			deactiveExpiredReservations(); // Quantity will increase if end date is over
			result = statement.executeUpdate();
		} catch(SQLException e) {
			System.out.println("Connection Problem for signup");
		}
		return result;
	}
	
	public int getUserId(String email) {
		try {
			PreparedStatement statement = databaseConnection.prepareStatement(SELECT_USER_FOR_EMAIL);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			
         while(result.next()) {
        	return result.getInt("id");
         }
        		 
		} catch(SQLException e) {
			System.out.println("Connection Problem for get user id");
		}
		System.out.println("User id is not found");
        return -1;
	}
	
	private boolean isUserExists(String email) {
		try {
			PreparedStatement statement = databaseConnection.prepareStatement(SELECT_USER_FOR_EMAIL);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			
         while(result.next()) {
        	return true;
         }
        		 
		} catch(SQLException e) {
			System.out.println("Connection Problem for is user exists");
		}
        return false;
	}
	
	private void deactiveExpiredReservations() {
		ReservationDao reservationDao = new ReservationDao();
		BookDao bookDao = new BookDao();
		ExpiredReservationDao expiredReservationDao = new ExpiredReservationDao();
		ArrayList<Reservation> reservations = reservationDao.getAllReservations();
		
		for(Reservation reservation : reservations) {
			Boolean isReservationAlreadyExpired = expiredReservationDao.isReservationExpired(reservation.getR_id());
			LocalDate reservationDate = reservation.getEnd_date();
			LocalDate currenDate = LocalDate.now();
			if (reservationDate.isBefore(currenDate) && !isReservationAlreadyExpired) {
				if (expiredReservationDao.expireReservation(reservation.getR_id())) {
					bookDao.updateQuantity(reservation.getB_id(), true);	
				}
			}
		}
	}
}

