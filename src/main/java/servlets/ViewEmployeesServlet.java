package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.UserService;
import users.Employee;

import java.io.IOException;
import java.util.List;

import dao.UserDAO;

@WebServlet("/viewEmployees")
public class ViewEmployeesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW_EMPLOYEES_JSP = "/WEB-INF/views/viewEmployees.jsp";
    private static final UserService userServ = new UserDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	List<Employee> employees = userServ.getAllEmployees();
    	request.setAttribute("employees", employees);
    	
        request.getRequestDispatcher(VIEW_EMPLOYEES_JSP).forward(request, response);
    }
}