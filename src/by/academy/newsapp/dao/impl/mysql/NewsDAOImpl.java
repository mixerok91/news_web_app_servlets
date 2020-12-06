package by.academy.newsapp.dao.impl.mysql;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.academy.newsapp.dao.DAOException;
import by.academy.newsapp.dao.NewsDAO;
import by.academy.newsapp.dao.impl.mysql.connectionpool.ConnectionPool;
import by.academy.newsapp.dao.impl.mysql.connectionpool.ConnectionPoolException;
import by.academy.newsapp.entity.News;

public class NewsDAOImpl implements NewsDAO{
	private static final String CREATE_NEWS_PS = "INSERT INTO news_db.news(title, date, brief, text) VALUES(?,?,?,?)";
	private static final String UPDATE_NEWS_PS = "UPDATE news_db.news SET title=?, date=?, brief=?, text=? WHERE(id=?)";
	private static final String READ_NEWS_PS = "SELECT title, date, brief, text FROM news_db.news WHERE id=?";
	private static final String READ_ALL_NEWS_PS = "SELECT * FROM news_db.news ORDER BY date DESC;";
	private static final String DELETE_NEWS_PS = "DELETE FROM news_db.news WHERE id=?;";
	
	private static Logger log = Logger.getLogger(NewsDAOImpl.class);
	
	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	 
	
	@Override
	public void createNews(News news) throws DAOException {
		
		String title = news.getTitle();
		Date date = Date.valueOf(news.getDate());
		String brief = news.getBrief();
		String text = news.getText();
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(CREATE_NEWS_PS);
			
			ps.setString(1, title);
			ps.setDate(2, date);
			ps.setString(3, brief);
			ps.setString(4, text);

			ps.executeUpdate();
			
			log.info("News was create, news info: " + news);
			
		} catch (ConnectionPoolException e) {
			log.error("Connection pool exception", e);
			throw new DAOException("Error with take connection from pool", e);
		} catch (SQLException e) {
			log.error("SQL exception", e);
			throw new DAOException("Error with prepared statement", e);
		} finally{
			connectionPool.closeConnection(connection, ps);
		}
		
	}

	@Override
	public News updateNews(News news) throws DAOException {
		int id = news.getId();
		String title = news.getTitle();
		Date date = Date.valueOf(news.getDate());
		String brief = news.getBrief();
		String text = news.getText();
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_NEWS_PS);
			
			ps.setString(1, title);
			ps.setDate(2, date);
			ps.setString(3, brief);
			ps.setString(4, text);
			ps.setInt(5, id);

			ps.executeUpdate();
			
			log.info("News was update, news info: " + news);
			return news;
									
		} catch (ConnectionPoolException e) {
			log.error("Connection pool exception", e);
			throw new DAOException("Error with take connection from pool", e);
		} catch (SQLException e) {
			log.error("SQL exception", e);
			throw new DAOException("Error with prepared statement", e);
		} finally{
			connectionPool.closeConnection(connection, ps);
		}
		
	}
	
	@Override
	public News readNews(int newsId) throws DAOException {
		
		News news = new News();
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(READ_NEWS_PS);
			
			ps.setInt(1, newsId);

			rs = ps.executeQuery();
			if(rs.next()) {
				news.setId(newsId);
				news.setTitle(rs.getString(1));
				news.setDate(LocalDate.parse((rs.getDate(2).toString())));
				news.setBrief(rs.getString(3));
				news.setText(rs.getString(4));
			}
			
			log.info("News was read, news info: " + news);
			
		} catch (ConnectionPoolException e) {
			log.error("Connection pool exception", e);
			throw new DAOException("Error with take connection from pool", e);
		} catch (SQLException e) {
			log.error("SQL exception", e);
			throw new DAOException("Error with prepared statement", e);
		} finally{
			connectionPool.closeConnection(connection, ps, rs);
		}
		return news;
	}

	@Override
	public List<News> readAllNews() throws DAOException {

		List<News> newsList = new ArrayList<News>();
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(READ_ALL_NEWS_PS);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				News news = new News();
				
				news.setId(rs.getInt(1));
				news.setTitle(rs.getString(2));
				news.setDate(LocalDate.parse((rs.getDate(3).toString())));
				news.setBrief(rs.getString(4));
				news.setText(rs.getString(5));
				
				newsList.add(news);
			}
			
			log.info("All news were read");
			
		} catch (ConnectionPoolException e) {
			log.error("Connection pool exception", e);
			throw new DAOException("Error with take connection from pool", e);
		} catch (SQLException e) {
			log.error("SQL exception", e);
			throw new DAOException("Error with prepared statement", e);
		} finally{
			connectionPool.closeConnection(connection, ps, rs);
		}
		return newsList;
	}

	@Override
	public void deleteNews(int newsId) throws DAOException {
		
		Connection connection = null;
		PreparedStatement ps = null;
				
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(DELETE_NEWS_PS);
			
			ps.setInt(1, newsId);
			ps.execute();
			
			log.info("News was delete, news id: " + newsId);
			
		} catch (ConnectionPoolException e) {
			log.error("Connection pool exception", e);
			throw new DAOException("Error with take connection from pool", e);
		} catch (SQLException e) {
			log.error("SQL exception", e);
			throw new DAOException("Error with prepared statement", e);
		} finally{
			connectionPool.closeConnection(connection, ps);
		}
	}
}