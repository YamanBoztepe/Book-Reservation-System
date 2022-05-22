<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.yeditepe.bookrez.dao.BookDao"%>
<%@ page import="com.yeditepe.bookrez.model.Book"%>
<%@ page import="com.yeditepe.bookrez.helper.Keys"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reservation</title>
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

#books tr:nth-child(even) {
	background-color: #f2f2f2;
}

#books tr:hover {
	background-color: #ddd;
}

#books th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #04AA6D;
	color: white;
}

button {
	background-color: #4CAF50;
	width: 100%;
	color: white;
	padding: 15px;
	margin: 10px 0px;
	border: none;
	cursor: pointer;
}

button:hover {
	opacity: 0.7;
}
</style>
</head>
<%
Book book = new Book();
String bookId = request.getParameter(Keys.Parameters.bookId);
if (bookId != null && !bookId.isBlank()) {
	book = new BookDao().getBook(Integer.parseInt(bookId));
} else {
	book = null;
}
%>
<body>
	<form
		action="<%=request.getContextPath() + Keys.Paths.reservationCompleted%>"
		method="post">
		<h1>Book Details</h1>
		<table id="books">
			<tr>
				<th>Book</th>
				<td><%=book.getBook_name()%></td>
			</tr>
			<tr>
				<th>Author</th>
				<td><%=book.getAuthor_name()%></td>
			</tr>
			<tr>
				<th>Publisher</th>
				<td><%=book.getPublisher()%></td>
			</tr>
			<tr>
				<th>Category</th>
				<td><%=book.getCategory()%></td>
			</tr>
			<tr>
				<th>Quantity</th>
				<td><%=book.getQuantity()%></td>
			</tr>
			<tr>
				<th>Start Date</th>
				<td><input type="date" name="<%=Keys.Parameters.startDate%>"></td>
			</tr>
			<tr>
				<th>End Date</th>
				<td><input type="date" name="<%=Keys.Parameters.endDate%>"></td>
			</tr>
		</table>
		<input type="hidden" name="<%=Keys.Parameters.bookId%>"
			value="<%=book.getId()%>">
		<button type="submit">Done</button>
	</form>
</body>
</html>