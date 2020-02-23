package by.epam.autoshow.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
public class SessionLocaleFilter implements Filter {
    private static final String PARAM_SESSION_LOCALE = "sessionLocale";
    private static final String ATTRIBUTE_LANGUAGE = "language";
    private static final Logger logger = LogManager.getLogger();

    public void init(FilterConfig arg) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (httpServletRequest.getParameter(PARAM_SESSION_LOCALE) != null) {
            HttpSession httpSession = httpServletRequest.getSession();
            String language = httpServletRequest.getParameter(PARAM_SESSION_LOCALE);
            httpSession.setAttribute(ATTRIBUTE_LANGUAGE, language);
        }
        String lang = (String) httpServletRequest.getSession().getAttribute(ATTRIBUTE_LANGUAGE);
        logger.debug("Language: " + lang);
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}