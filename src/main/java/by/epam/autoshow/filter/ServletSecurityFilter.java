package by.epam.autoshow.filter;

import by.epam.autoshow.model.UserRole;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(filterName = "servletSecurityFilter", urlPatterns = "/*")
public class ServletSecurityFilter implements Filter {
    private static final String REFERER = "referer";
    private static final String USER_ROLE_ATTRIBUTE = "userRole";
    private static final String PATH_REGEX = "command=.+";
    private static final String URI_REGEX = "((\\/[\\w\\-]*)+)(\\/)?(\\?[a-zA-Z\\d]+\\=[\\w\\-]*)?(\\&[a-zA-z\\d]+\\=[\\w\\-]*)?$";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(true);
        UserRole userRole = (UserRole) session.getAttribute(USER_ROLE_ATTRIBUTE);
        String url = request.getHeader(REFERER);
        String command = getUserCommand(url);
        //logger.debug("user role = " + userRole.name());
        //logger.debug("command = " + command);
        //logger.debug("Servlet security filter has worked.");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getUserCommand(String url) {
        Pattern pattern = Pattern.compile(PATH_REGEX);
        String command = null;
        if (url != null) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()) {
                command = matcher.group(0);
            }
        }
        return command;
    }

    @Override
    public void destroy() {

    }
}