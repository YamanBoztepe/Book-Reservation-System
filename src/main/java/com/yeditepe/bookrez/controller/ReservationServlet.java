package com.yeditepe.bookrez.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

import com.yeditepe.bookrez.dao.BookDao;
import com.yeditepe.bookrez.dao.ReservationDao;
import com.yeditepe.bookrez.helper.Keys;
import com.yeditepe.bookrez.model.User;

@WebServlet(Keys.Paths.defaultPath)
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReservationDao reservationDao;
	private BookDao bookDao;
    
 // Dependency Injection
    public ReservationServlet(ReservationDao reservationDao, BookDao bookDao) {
        super();
        this.reservationDao = reservationDao;
        this.bookDao = bookDao;
    }
    
    // Default Constructor
    public ReservationServlet() {
    	this(new ReservationDao(), new BookDao());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		switch (path) {
		case Keys.Paths.display:
			goToDisplayPage(response);
			break;
		case Keys.Paths.reservationDetail:
			goToReservation(request, response);
			break;
		case Keys.Paths.reservationCompleted:
			reservationCompleted(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void goToDisplayPage(HttpServletResponse response) throws IOException {
		response.sendRedirect(Keys.JSPPages.display);
	}
	
	private void goToReservation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int bookId = Integer.parseInt(request.getParameter(Keys.Parameters.bookId));
		RequestDispatcher dispatcher = request.getRequestDispatcher(Keys.JSPPages.reservation);
		request.setAttribute(Keys.Parameters.bookId, bookId);
		try {
			dispatcher.forward(request, response);
			
		} catch (ServletException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	private void reservationCompleted(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Keys.User.user);
		String startDate = request.getParameter(Keys.Parameters.startDate);
		String endDate = request.getParameter(Keys.Parameters.endDate);
		Integer bookId = Integer.parseInt(request.getParameter(Keys.Parameters.bookId));
		Integer userId = user.getId();
		
		if (
				   (startDate != null && !startDate.isBlank()) 
				&& (endDate != null && !endDate.isBlank()) 
		   ) 
		{
			LocalDate startLocalDate = LocalDate.parse(startDate);
			LocalDate endLocalDate = LocalDate.parse(endDate);
			if (reservationDao.addReservation(bookId, userId, startLocalDate, endLocalDate)) {
				if (bookDao.updateQuantity(bookId, false)) {
					response.sendRedirect(Keys.JSPPages.myReservations);			}
			}
		}
	}

}
