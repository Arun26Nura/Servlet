package com.example.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}
	private final String username = "admin";
    private final String password = "password";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String username = request.getParameter("username");
	        String password = request.getParameter("pwd");

	        if (this.username.equals(username) && this.password.equals(password)) {
	            //get the old session and invalidate
	            HttpSession oldSession = request.getSession(false);
	            if (oldSession != null) {
	                oldSession.invalidate();
	            }
	            //generate a new session
	            HttpSession newSession = request.getSession(true);

	            //setting session to expiry in 5 mins
	            newSession.setMaxInactiveInterval(5*60);

	            Cookie message = new Cookie("message", "Welcome");
	            response.addCookie(message);
	            response.sendRedirect("LoginSuccess.jsp");
	        } else {
	            RequestDispatcher rd = getServletContext().getRequestDispatcher("/loginPage.html");
	            PrintWriter out = response.getWriter();
	            out.println("<font color=red>Either username or password is wrong.</font>");
	            rd.include(request, response);
	        }}

}
