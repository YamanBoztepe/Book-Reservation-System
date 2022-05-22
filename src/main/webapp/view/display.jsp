<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.yeditepe.bookrez.dao.BookDao"%>
<%@ page import="com.yeditepe.bookrez.model.Book"%>
<%@ page import="com.yeditepe.bookrez.model.User"%>
<%@ page import="com.yeditepe.bookrez.dao.ReservationDao"%>
<%@ page import="com.yeditepe.bookrez.model.Reservation"%>
<%@ page import="com.yeditepe.bookrez.helper.Keys"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Available Books</title>
<style>
#books {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#books td, #books th {
  border: 1px solid #ddd;
  padding: 8px;
}

#books tr:nth-child(even){background-color: #f2f2f2;}

#books tr:hover {background-color: #ddd;}

#books th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #04AA6D;
  color: white;
}
</style>
</head>
<%
ArrayList<Book> books = new BookDao().getBooks();
User user = (User) session.getAttribute(Keys.User.user);
Integer userId = user.getId();
ArrayList<Reservation> reservations = new ReservationDao().getReservations(userId);
%>
<body>

	<%
	if (reservations.size() > 0) {
		Reservation lastReservation = reservations.get(reservations.size() - 1);
		String suggestedCategory = new BookDao().getBook(lastReservation.getB_id()).getCategory();
	%>

	<h1>Suggestions For you</h1>
	<table id="books">
		<tr>
			<th>Book</th>
			<th>Author</th>
			<th>Publisher</th>
			<th>Category</th>
			<th>Quantity</th>

		</tr>
		<%
		for (Book book : books) {
		%>
		<%
		if (book.getCategory().equals(suggestedCategory) && book.getQuantity() > 0) {
		%>
		<tr>
			<td><%=book.getBook_name()%></td>
			<td><%=book.getAuthor_name()%></td>
			<td><%=book.getPublisher()%></td>
			<td><%=book.getCategory()%></td>
			<td><%=book.getQuantity()%></td>
			<td><a
				href="<%=request.getContextPath() + Keys.Paths.reservationDetail + "?" + Keys.Parameters.bookId + "=" + book.getId()%>">Reserve</a></td>
		</tr>
		<%
		}
		%>
		<%
		}
		%>

	</table>
	<%
	}
	%>
	<h1>Available Books</h1>
	<table id="books">
		<tr>
			<th>Book</th>
			<th>Author</th>
			<th>Publisher</th>
			<th>Category</th>
			<th>Quantity</th>

		</tr>
		<%
		for (Book book : books) {
		%>
		<%
		if (book.getQuantity() > 0) {
		%>
		<tr>
			<td><%=book.getBook_name()%></td>
			<td><%=book.getAuthor_name()%></td>
			<td><%=book.getPublisher()%></td>
			<td><%=book.getCategory()%></td>
			<td><%=book.getQuantity()%></td>
			<td><a
				href="<%=request.getContextPath() + Keys.Paths.reservationDetail + "?" + Keys.Parameters.bookId + "=" + book.getId()%>">Reserve</a></td>
		</tr>
		<%
		}
		%>
		<%
		}
		%>

	</table>
</body>
</html>