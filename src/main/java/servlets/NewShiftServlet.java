package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

import dao.UserDAO;
import users.User;
import users.Manager;

import dao.ShiftDAO;
import services.ShiftService;


@WebServlet("/newShift")
public class NewShiftServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NEW_SHIFT_JSP = "/WEB-INF/views/newShift.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(NEW_SHIFT_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String shiftDate = request.getParameter("shiftDate");
		String employeeType = request.getParameter("employeeType");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String requiredEmployees = request.getParameter("requiredEmployees");
		String notes = request.getParameter("notes");

		request.setAttribute("shiftDate", shiftDate);
		request.setAttribute("employeeType", employeeType);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("requiredEmployees", requiredEmployees);
		request.setAttribute("notes", notes);
		
		
		
		HttpSession session = request.getSession(false);
		Integer userId = (Integer) session.getAttribute("userId");

		System.out.println("User ID from session: " + userId);
		
		
		LocalDate date = LocalDate.parse(shiftDate);
		LocalTime start = LocalTime.parse(startTime);
		LocalTime end = LocalTime.parse(endTime);

		LocalDateTime startDateTime = LocalDateTime.of(date, start);
		LocalDateTime endDateTime = LocalDateTime.of(date, end);
		
		
		
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUser(userId);
		Manager manager = (Manager) user;
		
	
		
		ShiftService shiftService = new ShiftDAO();
		shiftService.createShift(startDateTime, endDateTime, manager);
		
		session.setAttribute("successMessage", "Shift created successfully.");
		response.sendRedirect(request.getContextPath() + "/managerDash");
		return;
		
		
	}
}