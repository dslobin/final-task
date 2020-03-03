package by.epam.autoshow.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Locale;

@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
public class SessionLocaleFilter implements Filter {
    private static final String PARAM_SESSION_LOCALE = "sessionLocale";
    private static final String ATTRIBUTE_LANGUAGE = "language";
    private static final String LOCALE_REGEX = "_";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void init(FilterConfig arg) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getParameter(PARAM_SESSION_LOCALE) != null) {
            HttpSession session = request.getSession();
            String language = request.getParameter(PARAM_SESSION_LOCALE);
            session.setAttribute(ATTRIBUTE_LANGUAGE, language);
            changeLocale(language);
        }
        chain.doFilter(req, resp);
    }

    private void changeLocale(String locale) {
        String[] localeData = locale.split(LOCALE_REGEX);
        logger.debug("locale was changed");
        Locale.setDefault(new Locale(localeData[0], localeData[1]));
    }

    @Override
    public void destroy() {
    }
}