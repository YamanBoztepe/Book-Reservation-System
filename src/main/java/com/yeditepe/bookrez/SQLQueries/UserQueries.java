package com.yeditepe.bookrez.SQLQueries;

public interface UserQueries {
	String SELECT_USER = "select * from Users where email=? and password=?";
	String SELECT_USER_FOR_EMAIL = "select * from Users where email=?";
	String ADD_USER = "INSERT INTO Users (username, email, password) VALUES (?,?,?);";
}
