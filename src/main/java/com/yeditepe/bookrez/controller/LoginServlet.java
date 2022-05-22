package com.yeditepe.bookrez.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.yeditepe.bookrez.dao.UserDao;
import com.yeditepe.bookrez.helper.Keys;
import com.yeditepe.bookrez.model.User;

@WebServlet(Keys.Paths.login)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao dao;
       
	// Dependency Injection
    public LoginServlet(UserDao dao) {
        super();
        this.dao = dao;
    }
    
    // Default Constructor
    public LoginServlet() {
        this(new UserDao());
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logIn(request, response);
	}
	
	private void logIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = getUser(request);
		if (user != null) 
		{ saveInSessionAndGoToDisplay(request, response, user); } 
		else 
		{ goToLogin(response); }
	}
	
	private User getUser(HttpServletRequest request) {
		return dao.logIn(
				request.getParameter(Keys.User.email), 
				request.getParameter(Keys.User.password)
				);
	}
	
	private void saveInSessionAndGoToDisplay(HttpServletRequest request, HttpServletResponse response, User user) throws IOException {
		HttpSession session = request.getSession();
		session.setAttribute(Keys.User.user, user);
		response.sendRedirect(request.getContextPath() + Keys.Paths.display);
	}
	
	private void goToLogin(HttpServletResponse response) throws IOException {
		response.sendRedirect(Keys.JSPPages.login);
	}

}
