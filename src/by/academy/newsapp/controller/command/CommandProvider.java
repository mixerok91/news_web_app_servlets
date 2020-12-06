package by.academy.newsapp.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.academy.newsapp.controller.command.impl.ChangeLocaleCommand;
import by.academy.newsapp.controller.command.impl.CreateNewsCommand;
import by.academy.newsapp.controller.command.impl.DeleteFewNewsCommand;
import by.academy.newsapp.controller.command.impl.DeleteNewsCommand;
import by.academy.newsapp.controller.command.impl.EditNewsCommand;
import by.academy.newsapp.controller.command.impl.ToEditPageCommand;
import by.academy.newsapp.controller.command.impl.ToNewsPageCommand;
import by.academy.newsapp.controller.command.impl.ViewAllNewsCommand;

public class CommandProvider {
	private Map<ParameterName, Command> commands = new HashMap<>();
	
	public CommandProvider() {
		commands.put(ParameterName.VIEW_ALL_NEWS, new ViewAllNewsCommand());
		commands.put(ParameterName.TO_NEWS_PAGE, new ToNewsPageCommand());
		commands.put(ParameterName.TO_EDIT_PAGE, new ToEditPageCommand());
		commands.put(ParameterName.EDIT_NEWS, new EditNewsCommand());
		commands.put(ParameterName.DELETE_NEWS, new DeleteNewsCommand());
		commands.put(ParameterName.CREATE_NEWS, new CreateNewsCommand());
		commands.put(ParameterName.DELETE_FEW_NEWS, new DeleteFewNewsCommand());
		commands.put(ParameterName.CHANGE_LOCALE, new ChangeLocaleCommand());
	}
	
	public Command getCommand(String commandName) {
		Command command;
		ParameterName valueName;
		
		commandName = commandName.toUpperCase();
		valueName = ParameterName.valueOf(commandName);
		
		command = commands.get(valueName);
		
		return command;
	}
}
