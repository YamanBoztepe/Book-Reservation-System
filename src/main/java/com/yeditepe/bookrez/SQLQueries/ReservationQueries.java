package com.yeditepe.bookrez.SQLQueries;

public interface ReservationQueries {
	String GET_RESERVATIONS_FOR_ID = "select * from Reservations where u_id = ?";
	String GET_RESERVATIONS = "select * from Reservations";
	String ADD_RESERVATION = "INSERT INTO Reservations(b_id, u_id, start_date, end_date) VALUES (?,?,?,?)";
}
