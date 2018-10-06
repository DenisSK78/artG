package by.art.gallery.view.command.impl;

import by.art.gallery.service.ServiceFactory;
import by.art.gallery.service.UserService;
import by.art.gallery.service.exception.ServiceException;
import by.art.gallery.service.entity.UserAuthentication;
import by.art.gallery.view.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.art.gallery.view.AttributeName.MESSAGE;
import static by.art.gallery.view.AttributeName.USER_SESSION;

public class SignIn implements Command {

    final static Logger logger = LoggerFactory.getLogger(SignIn.class);

    private static final String REQ_EMAIL = "email";
    private static final String REQ_PASSWORD = "password";
    private static final String MESSAGE_USER_NOT_FOUND = "not_found";

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        UserAuthentication userAuth;
        String doPath = DoPath.LOGIN;

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();

        String email = request.getParameter(REQ_EMAIL);
        String password = request.getParameter(REQ_PASSWORD);

        try {
            userAuth = userService.getForAuth(email, password);
            if (userAuth != null) {
                request.getSession().setAttribute(USER_SESSION, userAuth);
                request.getSession().removeAttribute(MESSAGE);
                doPath = DoPath.MAIN;
            } else {
                request.getSession().setAttribute(MESSAGE, MESSAGE_USER_NOT_FOUND);
                if (request.getSession().getAttribute(USER_SESSION) != null){
                    request.getSession().removeAttribute(USER_SESSION);
                }
            }
        } catch (ServiceException e) {
            logger.error("User registration redirect to Error page", e);
            doPath = DoPath.ERROR_PAGE;
        }
        response.sendRedirect(DoPath.pathForRedirect(request, doPath));
    }
}
