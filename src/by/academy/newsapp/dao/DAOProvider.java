package by.academy.newsapp.dao;

import by.academy.newsapp.dao.impl.mysql.NewsDAOImpl;

public class DAOProvider {
	
	private static final DAOProvider instance = new DAOProvider();
	
	private final NewsDAO newsDAO = new NewsDAOImpl();
	
	private DAOProvider() {};
	
	public static DAOProvider getInstance() {
		return instance;
	}
	
	public NewsDAO getNewsDAO() {
		return newsDAO;
	}
}
