package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/assignShift")
public class AssignShiftServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String ASSIGN_SHIFT_JSP = "/WEB-INF/views/assignShift.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(ASSIGN_SHIFT_JSP)
               .forward(request, response);
    }
}