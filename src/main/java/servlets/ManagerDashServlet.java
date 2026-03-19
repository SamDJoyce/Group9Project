package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.UserService;
import users.User;

import java.io.IOException;

import dao.UserDAO;

/**
 * Servlet implementation class ManagerDashServlet
 */
@WebServlet("/managerDash")
public class ManagerDashServlet extends HttpServlet {
	private static final String MAN_DASH_JSP = "/WEB-INF/views/managerDashboard.jsp";
	private static final long serialVersionUID = 1L;
	
	private static final UserService userServ = new UserDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerDashServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Check user is an authorized Manager
		// TODO
		// If they are, forward the user to their Manager Dashboard
		HttpSession s = request.getSession(false);
		Integer userId = (Integer) s.getAttribute("userId");
		User u;
		if (userId == null) {
			bounce(request, response, "loginError");
		}
		u = userServ.getUser(userId);
		if (!u.isManager()) {
			bounce(request, response, "InsufficientRights");
		}
		
		request.getRequestDispatcher(MAN_DASH_JSP)
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.getRequestDispatcher(MAN_DASH_JSP)
		.forward(request, response);
	}
	
	private void bounce(HttpServletRequest request,
			 HttpServletResponse response,
			 String status)
			 throws ServletException, IOException {

		request.setAttribute("status", status);
		request.getRequestDispatcher("/login")
		.forward(request, response);
	}

}
