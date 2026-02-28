package servlets;

import java.io.IOException;

import dao.EmployeeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import security.PasswordUtility;
import services.EmployeeService;
import users.Employee;

/**
 * Servlet controlling the login page login.jsp
 * 
 * @author Sam Joyce
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final String LOGIN_SUCCESSFUL = "login successful";
	private static final String LOGIN_ERROR = "LoginError";
	
	private static final String HOME      = "/Index";
	private static final String LOGIN_JSP = "/WEB-INF/views/login.jsp";
	
	private static final EmployeeService emplServ = new EmployeeDAO();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		Employee empl     = emplServ.getEmployeeByEmail(email);
		
		
		if (empl != null 
		 && password != null 
		 && PasswordUtility.verifyPassword(password.toCharArray(), empl.getPassHash()) ) {
		// If password matches start a session
			HttpSession s = request.getSession(true);
			s.setAttribute("userId", empl.getUserId());
			request.setAttribute("status", LOGIN_SUCCESSFUL);
			request.getRequestDispatcher(HOME)
			.forward(request, response);
		} else { // Return user to the login page
			bounce(request,response, LOGIN_ERROR);
		}
	}
	
	private void bounce(HttpServletRequest request,
			 			HttpServletResponse response,
			 			String status)
			 			throws ServletException, IOException {
			request.setAttribute("status", status);
			request.getRequestDispatcher(LOGIN_JSP)
			.forward(request, response);
	}
}
