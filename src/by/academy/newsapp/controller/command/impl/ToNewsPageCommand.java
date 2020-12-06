package by.academy.newsapp.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.academy.newsapp.controller.command.Command;
import by.academy.newsapp.entity.News;
import by.academy.newsapp.service.ServiceException;
import by.academy.newsapp.service.ServiceProvider;

public class ToNewsPageCommand implements Command{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String page;
		try {
			int newsId = Integer.parseInt(request.getParameter("newsId"));
			News news = ServiceProvider.getInstance().getNewsService().readNews(newsId);
			page = Page.NEWS_PAGE;
			request.setAttribute("news", news);
		} catch (ServiceException e) {
			page = Page.ERROR_PAGE;
			request.setAttribute("errorMessage", "Ooops, something went wrong");
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

}
