package com.yeditepe.bookrez.helper;

public class Keys {
	
	
	private Keys() { }
	
	public static final class User {
		public static final String user = "user";
		public static final String username = "username";
		public static final String password = "password";
		public static final String email = "email";
	}
	
	public static final class Paths {
		public static final String defaultPath = "/";
		public static final String login = "/login";
		public static final String signUp = "/signUp";
		public static final String display = "/display";
		public static final String reservationDetail = "/reservationDetail";
		public static final String reservationCompleted = "/reservationCompleted";
	}
	
	public static final class Parameters {
		public static final String bookId = "bookId";
		public static final String startDate = "startDate";
		public static final String endDate = "endDate";
	}
	
	public static final class JSPPages {
		public static final String login = "view/login.jsp";
		public static final String display = "view/display.jsp";
		public static final String reservation = "view/reservation.jsp";
		public static final String myReservations = "view/myReservations.jsp";
	}
	
}