<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.yeditepe.bookrez.dao.ReservationDao"%>
<%@ page import="com.yeditepe.bookrez.dao.BookDao"%>
<%@ page import="com.yeditepe.bookrez.model.Reservation"%>
<%@ page import="com.yeditepe.bookrez.model.User"%>
<%@ page import="com.yeditepe.bookrez.model.Book"%>
<%@ page import="com.yeditepe.bookrez.helper.Keys"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Reservations</title>
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
User user = (User) session.getAttribute(Keys.User.user);
Integer userId = user.getId();
ArrayList<Reservation> reservations = new ReservationDao().getReservations(userId);
%>
<body>
	<table id="books">
		<tr>
			<th>Book</th>
			<th>Author</th>
			<th>Publisher</th>
			<th>Category</th>
			<th>Quantity</th>
			<th>Start Date</th>
			<th>End Date</th>

		</tr>
		<%
		for (Reservation reservation : reservations) {
			Book book = new BookDao().getBook(reservation.getB_id());
		%>
		<tr>
			<td><%=book.getBook_name()%></td>
			<td><%=book.getAuthor_name()%></td>
			<td><%=book.getPublisher()%></td>
			<td><%=book.getCategory()%></td>
			<td><%=book.getQuantity()%></td>
			<td><%=reservation.getStart_date()%></td>
			<td><%=reservation.getEnd_date()%></td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>