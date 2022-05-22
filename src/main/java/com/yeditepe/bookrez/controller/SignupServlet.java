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

@WebServlet(Keys.Paths.signUp)
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao dao;
	
	// Dependency Injection
    public SignupServlet(UserDao dao) {
        super();
        this.dao = dao;
    }
    
    // Default Constructor
    public SignupServlet() {
        this(new UserDao());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		goToSignUpPage(response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		addNewUser(request, response);
	}
	
	private void addNewUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter(Keys.User.username);
		String password = request.getParameter(Keys.User.password);
		String email = request.getParameter(Keys.User.email);
		boolean isSignedUp = dao.signUp(username, password, email) == 1;
		Integer userId = dao.getUserId(email);
		boolean isUserExists = userId != -1;
		
		if (isSignedUp && isUserExists) {
			User user = new User(userId, username, email, password);
			saveInSessionAndGoToDisplay(request, response, user);
		} else {
			goToSignUpPage(response);
		}
	}
	
	private void saveInSessionAndGoToDisplay(HttpServletRequest request, HttpServletResponse response, User user) throws IOException {
		HttpSession session = request.getSession();
		session.setAttribute(Keys.User.user, user);
		response.sendRedirect(request.getContextPath() + Keys.Paths.display);
	}

	private void goToSignUpPage(HttpServletResponse response) throws IOException {
		response.sendRedirect("view/signup.jsp");
	}
}
