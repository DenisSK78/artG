package by.art.gallery.view.command.impl;

import by.art.gallery.view.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewsPage implements Command {

    final static Logger logger = LoggerFactory.getLogger(NewsPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(DoPath.NEWS);
        dispatcher.forward(request, response);
    }
}
