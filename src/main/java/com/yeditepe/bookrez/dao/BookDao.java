package com.yeditepe.bookrez.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.yeditepe.bookrez.SQLQueries.BookQueries;
import com.yeditepe.bookrez.helper.Database;
import com.yeditepe.bookrez.model.Book;
import com.yeditepe.bookrez.model.Reservation;

public class BookDao 
extends BaseDao 
implements BookQueries 

{
	// Dependency Injection
	public BookDao(Connection connection) {
		databaseConnection = connection;
	}
	
	// Default Constructor
	public BookDao() {
		this(Database.getConnection());
	}
	
	public ArrayList<Book> getBooks() {
		ArrayList<Book> books = new ArrayList<Book>();
		try {
			PreparedStatement statement = databaseConnection.prepareStatement(SELECT_BOOKS);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Integer id = result.getInt("id");
				String book_name = result.getString("book_name");
				String author_name = result.getString("author_name");
				String publisher = result.getString("publisher");
				String category = result.getString("category");
				Integer quantity = result.getInt("quantity");
				Book book = new Book(id, book_name, author_name, publisher, category, quantity);
				books.add(book);
			}
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println("Connection Problem");
		}
		return books;
	}
	
	public Book getBook(Integer id) {
		try {
			PreparedStatement statement = databaseConnection.prepareStatement(SELECT_BOOK_FOR_ID);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				String book_name = result.getString("book_name");
				String author_name = result.getString("author_name");
				String publisher = result.getString("publisher");
				String category = result.getString("category");
				Integer quantity = result.getInt("quantity");
				Book book = new Book(id, book_name, author_name, publisher, category, quantity);
				return book;
			}
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println("Connection Problem");
		}
		
		return null;
	}
	
	public boolean updateQuantity(Integer id, Boolean isIncreased) {
		Book book = getBook(id);
		Integer quantity = book.getQuantity() + (isIncreased ? 1 : - 1);
		try {
			PreparedStatement statement = databaseConnection.prepareStatement(UPDATE_BOOK_FOR_QUANTITY);
			statement.setInt(1, quantity);
			statement.setInt(2, id);
			return statement.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println("Connection Problem");
		}
		
		return false;
	}
}
