package by.academy.newsapp.dao.impl.mysql.connectionpool;

import java.util.ResourceBundle;

public class DBResourceManager {
    private final static DBResourceManager instance = new DBResourceManager();

    private ResourceBundle bundle = ResourceBundle.getBundle("by.academy.newsapp.dao.impl.mysql.connectionpool.properties.db");

    public static DBResourceManager getInstance(){
        return instance;
    }

    public String getValue(String key){
        return bundle.getString(key);
    }
}
