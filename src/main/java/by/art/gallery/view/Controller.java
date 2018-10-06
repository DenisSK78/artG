package by.art.gallery.view;

import by.art.gallery.view.command.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/gallery")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
	private static final String COMMAND_PARAM_NAME = "do";

	private final CommandProvider provider = new CommandProvider();
	
    public Controller() {
        super(); 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter(COMMAND_PARAM_NAME);
		Command command = provider.getCommand(name);
		command.execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
	}

	public static String getCommandParamName() {
		return COMMAND_PARAM_NAME;
	}
}
