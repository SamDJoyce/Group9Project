package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/addEmployee")
public class AddEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String ADD_EMPLOYEE_JSP = "/WEB-INF/views/addEmployee.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("AddEmployeeServlet doGet() called");

        request.getRequestDispatcher(ADD_EMPLOYEE_JSP).forward(request, response);
    }
}