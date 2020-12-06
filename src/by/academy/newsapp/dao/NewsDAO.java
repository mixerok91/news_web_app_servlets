package by.academy.newsapp.dao;

import java.util.List;

import by.academy.newsapp.entity.News;

public interface NewsDAO {
	
	void createNews(News news) throws DAOException; 
	
	News updateNews(News news) throws DAOException;
	
	News readNews(int newsId) throws DAOException;
	
	List<News> readAllNews() throws DAOException;
	
	void deleteNews(int newsId) throws DAOException;
	
}
