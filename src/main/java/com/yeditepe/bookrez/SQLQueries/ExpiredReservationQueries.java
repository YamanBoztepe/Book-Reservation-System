package com.yeditepe.bookrez.SQLQueries;

public interface ExpiredReservationQueries {
	String GET_EXPIRED_RESERVATIONS_FOR_ID = "select * from ExpiredReservations where id = ?";
	String ADD_EXPIRED_RESERVATION = "INSERT INTO ExpiredReservations(id) VALUES (?)";
}
