package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.UserService;
import users.Employee;
import users.User;

import java.io.IOException;

import dao.UserDAO;

/**
 * Servlet implementation class EmployeeDashServlet
 */
@WebServlet("/employeeDash")
public class EmployeeDashServlet extends HttpServlet {
	private static final String EMP_DASH_JSP = "/WEB-INF/views/employeeDashboard.jsp";
	private static final long serialVersionUID = 1L;
	
	private static final UserService userServ = new UserDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeDashServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession(false);
		Integer userId = (Integer) s.getAttribute("userId");
		User user;
		if (userId == null) {
			bounce(request, response, "loginError");
		} else {
			user = userServ.getUser(userId);
			request.setAttribute("employee", user);
			String status = "loginSuccessful";
			request.setAttribute("status", status);
		}
		request.getRequestDispatcher(EMP_DASH_JSP)
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher(EMP_DASH_JSP)
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
