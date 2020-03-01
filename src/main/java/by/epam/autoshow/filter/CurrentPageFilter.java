package by.epam.autoshow.filter;

import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.PagePathProperty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(filterName = "CurrentPageFilter", urlPatterns = "/*")
public class CurrentPageFilter implements Filter {
    private static final String REFERER = "referer";
    private static final String PATH_REGEX = "/controller.+";
    private static final String CURRENT_PAGE_ATTRIBUTE = "currentPage";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(true);
        String url = request.getHeader(REFERER);
        String path = substringPathWithRegex(url);
        session.setAttribute(CURRENT_PAGE_ATTRIBUTE, path);
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }

    private String substringPathWithRegex(String url) {
        Pattern pattern = Pattern.compile(PATH_REGEX);
        String path = null;
        if (url != null) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()) {
                path = matcher.group(0);
            } else {
                path = PagePathProvider.getProperty(PagePathProperty.HOME_PAGE_PROPERTY);
            }
        }
        return path;
    }
}