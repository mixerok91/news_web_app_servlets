package by.academy.newsapp.controller.command.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.academy.newsapp.controller.command.Command;
import by.academy.newsapp.service.NewsService;
import by.academy.newsapp.service.ServiceException;
import by.academy.newsapp.service.ServiceProvider;

public class DeleteFewNewsCommand implements Command{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String page;
		String name;
		String value;
		
		try {
			Enumeration<String> parameterNamesEnum = request.getParameterNames();
			List<String> parameterNames = Collections.list(parameterNamesEnum);
						
			NewsService service = ServiceProvider.getInstance().getNewsService();
			
			service.deleteFewNews(parameterNames);
			
			page = Page.CONTROLLER;
			name = "command";
			value = "view_all_news";
			
		} catch (ServiceException e) {
			page = Page.ERROR_PAGE;
			name = "errorMessage";
			value = "Ooops, something went wrong";
		}
		
		response.sendRedirect(page + "?" + name + "=" + value);
	}

}
