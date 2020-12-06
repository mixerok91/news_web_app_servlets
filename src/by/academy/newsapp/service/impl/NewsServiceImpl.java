package by.academy.newsapp.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;

import by.academy.newsapp.dao.DAOException;
import by.academy.newsapp.dao.DAOProvider;
import by.academy.newsapp.dao.NewsDAO;
import by.academy.newsapp.dao.impl.mysql.NewsDAOImpl;
import by.academy.newsapp.entity.News;
import by.academy.newsapp.service.NewsService;
import by.academy.newsapp.service.ServiceException;

public class NewsServiceImpl implements NewsService{
	
	private DAOProvider provider = DAOProvider.getInstance();
	private NewsDAO newsDAO = provider.getNewsDAO();
	
	private static Logger log = Logger.getLogger(NewsServiceImpl.class);
	
	@Override
	public void createNews(News news) throws ServiceException {
		
		if (("").equals(news.getTitle())) {
			news.setTitle("Title");
		}
		if (("").equals(news.getBrief())) {
			news.setBrief("Brief");
		}
		
		try {
			newsDAO.createNews(news);
			log.info("News created");
		} catch (DAOException e) {
			log.error("Service exception", e);
			throw new ServiceException(e);
		}
		
	}

	@Override
	public void updateNews(News news) throws ServiceException {
		try {
			if(news.getDate()==null) {
				news.setDate(LocalDate.now());
			}
			newsDAO.updateNews(news);
			log.info("News updated");
		} catch (DAOException e) {
			log.error("Service exception", e);
			throw new ServiceException(e);
		}
		
	}

	@Override
	public News readNews(int newsId) throws ServiceException {
		try {
			if(newsId == 0) {
				throw new ServiceException("News with that id does not exist");
			}
			News news = newsDAO.readNews(newsId);
			log.info("News read");
			return news;
		} catch (DAOException e) {
			log.error("Service exception", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> readAllNews() throws ServiceException {
		try {
			List<News> newsList = newsDAO.readAllNews();
			log.info("All news read");
			return newsList;
		} catch (DAOException e) {
			log.error("Service exception", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteNews(int newsId) throws ServiceException {
		try {
			newsDAO.deleteNews(newsId);
			log.info("News deleted");
		} catch (DAOException e) {
			log.error("Service exception", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteFewNews(List<String> newsIds) throws ServiceException {
		try {
			for (int i = 0; i < newsIds.size(); i++) {
				if(Character.isDigit((newsIds.get(i).charAt(0)))) {
					int newsId = Integer.valueOf(newsIds.get(i));
					newsDAO.deleteNews(newsId);
				}
			}
			log.info("Few news deleted");
		} catch (DAOException e) {
			log.error("Service exception", e);
			throw new ServiceException(e);
		}
	}
}
