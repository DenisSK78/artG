package by.art.gallery.view;

import by.art.gallery.view.command.Command;
import by.art.gallery.view.command.impl.AdminPage;
import by.art.gallery.view.command.impl.AuthorsPage;
import by.art.gallery.view.command.impl.ErrorPage;
import by.art.gallery.view.command.impl.LoginPage;
import by.art.gallery.view.command.impl.MainPage;
import by.art.gallery.view.command.impl.MassageOff;
import by.art.gallery.view.command.impl.NewsPage;
import by.art.gallery.view.command.impl.RegistrationPage;
import by.art.gallery.view.command.impl.SignIn;
import by.art.gallery.view.command.impl.SignOut;
import by.art.gallery.view.command.impl.UserRegistration;

import java.util.EnumMap;
import java.util.Map;

class CommandProvider {

	private Map<CommandName, Command> commands = new EnumMap<>(CommandName.class);

	 CommandProvider() {
		commands.put(CommandName.MAIN, new MainPage());
		commands.put(CommandName.NEWS, new NewsPage());
		commands.put(CommandName.ENTER, new SignIn());
		commands.put(CommandName.ADMIN, new AdminPage());
		commands.put(CommandName.ERROR, new ErrorPage());
		commands.put(CommandName.LOGIN, new LoginPage());
		commands.put(CommandName.AUTHORS, new AuthorsPage());
		commands.put(CommandName.SIGN_OUT, new SignOut());
		commands.put(CommandName.MESSAGE_OFF, new MassageOff());
		commands.put(CommandName.REGISTRATION, new RegistrationPage());
		commands.put(CommandName.USER_REGISTRATION, new UserRegistration());
	}

	Command getCommand(String commandName) {
		Command command;
		command = commands.get(CommandName.valueOf(commandName.toUpperCase()));
		return command;
	}
}
