package by.art.gallery.view.command.impl;

import by.art.gallery.view.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.art.gallery.view.AttributeName.USER_SESSION;

public class SignOut implements Command {

    final static Logger logger = LoggerFactory.getLogger(SignOut.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute(USER_SESSION, null);
        response.sendRedirect(DoPath.pathForRedirect(request, DoPath.MAIN));
    }
}
