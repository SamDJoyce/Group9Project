package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/viewSchedule")
public class ViewScheduleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW_SCHEDULE_JSP = "/WEB-INF/views/viewSchedule.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(VIEW_SCHEDULE_JSP).forward(request, response);
    }
}