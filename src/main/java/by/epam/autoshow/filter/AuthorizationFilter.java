package by.epam.autoshow.filter;

import by.epam.autoshow.model.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "authorizationFilter", urlPatterns = "/*")
public class AuthorizationFilter implements Filter {
    private static final String USER_ROLE_ATTRIBUTE = "userRole";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(true);
        if (session.getAttribute(USER_ROLE_ATTRIBUTE) == null) {
            session.setAttribute(USER_ROLE_ATTRIBUTE, UserRole.GUEST);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
