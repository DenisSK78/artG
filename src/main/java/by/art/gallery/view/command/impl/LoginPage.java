package by.art.gallery.view.command.impl;

import by.art.gallery.view.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginPage implements Command {

	final static Logger logger = LoggerFactory.getLogger(LoginPage.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String doPath = DoPath.LOGIN;
		RequestDispatcher dispatcher = request.getRequestDispatcher(doPath);
		dispatcher.forward(request, response);
	}
}
