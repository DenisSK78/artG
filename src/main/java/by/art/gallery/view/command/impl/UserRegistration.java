package by.art.gallery.view.command.impl;

import by.art.gallery.repository.entity.User;
import by.art.gallery.service.ServiceFactory;
import by.art.gallery.service.UserService;
import by.art.gallery.service.exception.ServiceException;
import by.art.gallery.service.converter.ConverterEnum;
import by.art.gallery.view.AttributeName;
import by.art.gallery.view.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UserRegistration implements Command {

    private final static Logger logger = LoggerFactory.getLogger(SignOut.class);

    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String GENDER = "gender";
    private static final String SUCCESS = "success";
    private static final String SURNAME = "surname";
    private static final String BAD_DATA = "bad_data";
    private static final String PASSWORD = "password";
    private static final String EMAIL_EXIST = "email_exist";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        User user;
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();

        user = User.builder()
                .firstName(request.getParameter(NAME))
                .surname(request.getParameter(SURNAME))
                .email(request.getParameter(EMAIL))
                .password(request.getParameter(PASSWORD))
                .gender(ConverterEnum.getGender(request.getParameter(GENDER)))
                .build();

        try {
            String result = userService.saveUser(user);
            if (result.equals(SUCCESS)){
                request.getSession().setAttribute(AttributeName.MESSAGE, SUCCESS);
                response.sendRedirect(DoPath.pathForRedirect(request, DoPath.LOGIN));
            }
            if (result.equals(BAD_DATA)){
                request.getSession().setAttribute(AttributeName.MESSAGE, BAD_DATA);
                response.sendRedirect(DoPath.pathForRedirect(request, DoPath.REGISTRATION_PAGE));
            }
            if (result.equals(EMAIL_EXIST)){
                request.getSession().setAttribute(AttributeName.MESSAGE, EMAIL_EXIST);
                response.sendRedirect(DoPath.pathForRedirect(request, DoPath.REGISTRATION_PAGE));
            }

        } catch (ServiceException e) {
            logger.error("User registration redirect to Error page", e);
            response.sendRedirect(DoPath.pathForRedirect(request, DoPath.ERROR_PAGE));
        }
    }
}
