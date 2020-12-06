package by.academy.newsapp.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.academy.newsapp.controller.command.Command;
import by.academy.newsapp.entity.News;
import by.academy.newsapp.service.ServiceException;
import by.academy.newsapp.service.ServiceProvider;

public class ViewAllNewsCommand implements Command{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String page;
		
		try {
			List<News> newsList = ServiceProvider.getInstance().getNewsService().readAllNews();
			if (newsList.size() < 1) request.setAttribute("newsList", "no news");
			request.setAttribute("newsList", newsList);
			page = Page.NEWS_LIST_PAGE;
		} catch (ServiceException e) {
			page = Page.ERROR_PAGE;
			request.setAttribute("errorMessage", "Ooops, something went wrong");
		}
		
		request.getRequestDispatcher(page).forward(request, response);
		
	}

}
