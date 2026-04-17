package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.ShiftService;
import services.UserService;
import shifts.Shift;
import users.User;

import java.io.IOException;
import java.util.List;

import dao.ShiftDAO;
import dao.UserDAO;

@WebServlet("/viewSchedule")
public class ViewScheduleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW_SCHEDULE_JSP = "/WEB-INF/views/viewSchedule.jsp";
    private static final ShiftService shiftServ = new ShiftDAO();
    private static final UserService userServ   = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	// Get session and validate user
    	HttpSession s = request.getSession(false);
    	Integer userId = (Integer) s.getAttribute("userId");
    	if (userId == null) {
			bounce(request, response, "loginError");
		}
    	User u = userServ.getUser(userId);
    	if (!u.isManager()) {
			bounce(request, response, "InsufficientRights");
		}
    	// Get all shifts created by/assigned to this manager
    	List<Shift> shifts = shiftServ.getShiftByManagerId(userId);
    	request.setAttribute("shifts", shifts);
        request.getRequestDispatcher(VIEW_SCHEDULE_JSP).forward(request, response);
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