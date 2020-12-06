package by.academy.newsapp.service;

import java.util.List;

import by.academy.newsapp.entity.News;

public interface NewsService {
	
	public void createNews(News news) throws ServiceException;
	
	public void updateNews(News news) throws ServiceException;
	
	public News readNews(int newsId) throws ServiceException;
	
	public List<News> readAllNews() throws ServiceException;
	
	public void deleteNews(int newsId) throws ServiceException;
	
	public void deleteFewNews(List<String> newsIds) throws ServiceException;
}
