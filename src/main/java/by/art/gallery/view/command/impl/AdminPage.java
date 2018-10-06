package by.art.gallery.view.command.impl;

import by.art.gallery.service.exception.ServiceException;
import by.art.gallery.view.command.Command;
import by.art.gallery.view.command.impl.admin.AdminCommandProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminPage implements Command {

    private static final Logger logger = LoggerFactory.getLogger(AdminPage.class);
    private static final String GET = "get";
    private static final String FRAGMENT = "fragment";
    private static final String EDITOR = "editor_";
    private static final String EDITOR_MATCHES = "^editor.*";
    private AdminCommandProvider adminProvider = new AdminCommandProvider();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter(GET) != null) {

            if (request.getParameter(GET).matches(EDITOR_MATCHES)){
                try {
                    adminProvider.getCommand(request.getParameter(GET)).execute(request, response);
                    response.sendRedirect(request.getRequestURL()
                            + "?" +request.getQueryString().replaceAll(EDITOR, ""));
                    return;
                } catch (ServiceException e) {
                    logger.error("Admin page editors", e);
                    response.sendRedirect(DoPath.pathForRedirect(request, DoPath.ERROR_PAGE));
                }
            }

            request.setAttribute(FRAGMENT, request.getParameter(GET));

            try {
                adminProvider.getCommand(request.getParameter(GET)).execute(request,response);
            } catch (ServiceException e) {
                logger.error("Admin page admin command", e);
                response.sendRedirect(DoPath.pathForRedirect(request, DoPath.ERROR_PAGE));
                return;
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(DoPath.ADMIN);
        dispatcher.forward(request, response);
    }
}
