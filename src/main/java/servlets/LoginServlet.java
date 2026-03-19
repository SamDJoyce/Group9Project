package servlets;

import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import security.PasswordUtility;
import services.UserService;
import users.User;

/**
 * Servlet controlling the login page login.jsp
 * 
 * @author Sam Joyce
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final String STATUS 			 = "status";
	private static final String LOGIN_SUCCESSFUL = "loginSuccessful";
	private static final String LOGIN_ERROR 	 = "LoginError";
	private static final String MANAGER			 = "manager";
	private static final String EMPLOYEE		 = "employee";
	private static final String MANAGER_DASH 	 = "/managerDash";
	private static final String EMPLOYEE_DASH 	 = "/employeeDash";
	private static final String LOGIN_JSP 		 = "/WEB-INF/views/login.jsp";
	
	private static final UserService userServ = new UserDAO();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = (String) request.getAttribute(STATUS);
		if (status != null) {
			request.setAttribute(STATUS, status);
		}
		// Forward the user to the login page
		request.getRequestDispatcher(LOGIN_JSP)
        		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String   email    = request.getParameter("email");
		String   password = request.getParameter("password");
		User     user     = userServ.getUserByEmail(email);
		
		
		if (user != null 
		 && password != null 
		 && PasswordUtility.verifyPassword(password.toCharArray(), user.getPassHash()) ) {
		// If password matches start a session
			HttpSession s = request.getSession(true);
			s.setAttribute("userId", user.getUserId());
			String dashboard;
			
			if (!MANAGER.equalsIgnoreCase(user.getType()) ) {
				dashboard = EMPLOYEE_DASH;
				request.setAttribute(EMPLOYEE, user);
			} else {
				dashboard = MANAGER_DASH;
				request.setAttribute(MANAGER, user);
			}
			request.setAttribute(STATUS, LOGIN_SUCCESSFUL);
			request.getRequestDispatcher(dashboard)
			.forward(request, response);
		} else { // Return user to the login page
			bounce(request,response, LOGIN_ERROR);
		}
	}
	
	private void bounce(HttpServletRequest request,
			 			HttpServletResponse response,
			 			String status)
			 			throws ServletException, IOException {
			request.setAttribute(STATUS, status);
			request.getRequestDispatcher(LOGIN_JSP)
			.forward(request, response);
	}
}
