package by.academy.newsapp.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.academy.newsapp.controller.command.Command;
import by.academy.newsapp.controller.command.ParameterName;

public class ChangeLocaleCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String page = Page.ERROR_PAGE;
				
		request.getSession(true).setAttribute("local", request.getParameter("local"));
		
		String fromPage = request.getParameter("fromPage").toLowerCase();
				
		if(fromPage.equalsIgnoreCase(Page.NEWS_LIST_PAGE)) {
			page = Page.CONTROLLER + "?command=view_all_news";
		}
		
		if (fromPage.equalsIgnoreCase(Page.NEWS_PAGE)) {
			int newsId = Integer.parseInt(request.getParameter("newsId"));
			page = Page.CONTROLLER +"?command=to_news_page&newsId=" + newsId;
		}
		
		if (fromPage.equalsIgnoreCase(Page.EDIT_PAGE)) {
			if (!("").equals(request.getParameter("newsId"))) {
				int newsId = Integer.parseInt(request.getParameter("newsId"));
				page = Page.CONTROLLER +"?command=to_edit_page&newsId=" + newsId;
			} else {
				page = "controller?command=to_edit_page";
			}
		}
		response.sendRedirect(page);
	}

}
