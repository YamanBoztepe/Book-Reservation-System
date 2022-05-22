package com.yeditepe.bookrez.SQLQueries;

public interface BookQueries {
	String SELECT_BOOKS = "select * from Books";
	String SELECT_BOOK_FOR_ID = "select * from Books where id=?";
	String UPDATE_BOOK_FOR_QUANTITY = "update Books set quantity = ? where id = ?;";
}