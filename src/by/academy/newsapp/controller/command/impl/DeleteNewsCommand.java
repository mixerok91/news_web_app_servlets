package by.academy.newsapp.controller.command.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.academy.newsapp.controller.command.Command;
import by.academy.newsapp.service.NewsService;
import by.academy.newsapp.service.ServiceException;
import by.academy.newsapp.service.ServiceProvider;

public class DeleteNewsCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String page;
		String name;
		String value;
		
		try {
			int newsId = Integer.parseInt(request.getParameter("newsId"));
			
			NewsService service = ServiceProvider.getInstance().getNewsService();
			service.deleteNews(newsId);
			
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
