package security;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest  request  = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String contextPath = request.getContextPath();
        String uri         = request.getRequestURI();
        String path        = uri.substring(contextPath.length());   // e.g. "/Login"

        // ----- Public pages (no login required) ----- \\
        boolean isPublic =
                path.equals("/") ||
                path.equals("/login") ||          // login page
                path.equals("/NewUser") ||        // registration page
                path.startsWith("/style") ||      // CSS
                path.endsWith(".css") ||
                path.startsWith("/images/") ||
                path.startsWith("/img/");

        if (isPublic) {
            chain.doFilter(req, res);
            return;
        }

        // ----- Everything else: require login ----- \\
        HttpSession session = request.getSession(false);
        boolean loggedIn = (session != null && session.getAttribute("userId") != null);

        if (!loggedIn) {
            // Not logged in – send to login page
            response.sendRedirect(contextPath + "/login");
            return;
        }

        // logged in: continue
        chain.doFilter(req, res);
    }
}
