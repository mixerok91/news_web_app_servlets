package by.academy.newsapp.service;

import by.academy.newsapp.service.impl.NewsServiceImpl;

public class ServiceProvider {
	
	private static final ServiceProvider instance = new ServiceProvider();
	
	private final NewsService newsService = new NewsServiceImpl();
	
	private ServiceProvider() {}
	
	public static ServiceProvider getInstance() {
		return instance;
	}
	
	public NewsService getNewsService() {
		return newsService;
	}
}
