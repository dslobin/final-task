package by.epam.autoshow.controller;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.command.ActionFactory;
import by.epam.autoshow.command.RouteType;
import by.epam.autoshow.command.Router;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(urlPatterns = "/controller")
public class MainServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionRequestContent content = new SessionRequestContent(request);
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(content);
        Router router = command.execute(content);
        content.insert(request);
        String page = router.getPagePath();
        if (RouteType.FORWARD.equals(router.getRouteType())) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + page);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}