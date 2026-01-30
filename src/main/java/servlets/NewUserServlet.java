package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import security.PasswordUtility;
import services.EmployeeService;
import users.Employee;

import java.io.IOException;

import dao.EmployeeDAO;

/**
 * Servlet controlling the new user creation page NewUser.jsp
 * 
 * @author Sam Joyce
 */
@WebServlet("/NewUser")
public class NewUserServlet extends HttpServlet {
	private static final String SUCCESSFULL_CREATION = "successfullCreation";

	private static final String FAILED_CREATION = "failedCreation";

	private static final String MISSING_EMAIL = "missingEmail";

	private static final String EMAIL_EXISTS = "emailExists";

	private static final String MISSING_PASSWORD = "missingPassword";

	private static final long serialVersionUID = 1L;
	
	private static final String CREATE_USER = "createUser";
	private static final String PASSWORD_MISMATCH = "passwordMismatch";
	private static final String NEW_USER_JSP = "/WEB-INF/views/newUser.jsp";
	private static final String LOGIN_JSP = "/WEB-INF/views/login.jsp";
	
	private static final EmployeeService emplServ = new EmployeeDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(NEW_USER_JSP)
        		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
	    String status = null;
	    
	    if (CREATE_USER.equalsIgnoreCase(action)) {
	    	// ----- Examine the entered New User data -----
	    	// Validate email
	        String email = request.getParameter("email".toLowerCase().trim());
	        // Confirm an email was entered
	        if (email == null || email.isBlank()) {
	        	bounce(request, response, MISSING_EMAIL);
	            return;
	        }
	        // Confirm the email has not already been used
	        if (emplServ.getEmployeeByEmail(email) != null) {
	        	bounce(request, response, EMAIL_EXISTS);
	            return;
	        }
	    	// Validate password
	        String password     = request.getParameter("password");
	        String confirmPass  = request.getParameter("confirmPass");
	        // Confirm a password was entered
	        if (password == null || confirmPass == null ||
	            password.isBlank() || confirmPass.isBlank()) {
	        	bounce(request, response, MISSING_PASSWORD);
	            return;
	        }
	        // Validate password and re-entered password match
	        if (!password.equals(confirmPass)) {
	        	bounce(request, response, PASSWORD_MISMATCH);
	            return;
	        }
	        
	        // ----- Create Employee -----
	        String firstName = request.getParameter("firstName");
	        String lastName  = request.getParameter("lastName");
	        String type      = request.getParameter("type");

	        String passHash = PasswordUtility.hashPassword(password.toCharArray());

	        Employee empl = emplServ.createEmployee(firstName, lastName, email, type, passHash);
	        
	        if (empl == null) {
	        	status = FAILED_CREATION;
	        	bounce(request, response, status);
	        } else {
	        	status = SUCCESSFULL_CREATION;
	        	request.getRequestDispatcher(LOGIN_JSP)
				.forward(request, response);
	        }
	    } // Else if (other actions)
	}
 
	/**
	 * Return the user to the new user page
	 * with a status message.
	 * 
	 * @param request
	 * @param response
	 * @param status
	 * @throws ServletException
	 * @throws IOException
	 */
	private void bounce(HttpServletRequest request,
						 HttpServletResponse response,
						 String status)
						 throws ServletException, IOException {
		
		request.setAttribute("status", status);
		request.getRequestDispatcher(NEW_USER_JSP)
		  .forward(request, response);
	}

}
