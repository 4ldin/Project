package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import jdk.jshell.spi.ExecutionControl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * Abstract class that implements core DAO CRUD methods for every entity
 * @author Aldin Islamagic
 */

public abstract class AbstractDao<T extends Idable> implements Dao<T> {

    private Connection connection;
    private String tableName;

    /**
     * Constructor for abstract dao that makes connection to database with hidden db name and password
     * @param tableName name of table in database
     */
    public AbstractDao(String tableName){
        this.tableName = tableName;
        try {
            String fieldPath = "src/dataBase.properties";
            Properties pros = new Properties();
            FileInputStream ip = new FileInputStream(fieldPath);
            pros.load(ip);
            this.connection = DriverManager.getConnection(pros.getProperty("url"), pros.getProperty("username"), pros.getProperty("password"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method that gets connection from db
     * @return connection of the db
     */
    public Connection getConnection(){
        return this.connection;
    }

    /**
     * Method that sets the connection for the db
     * @param connection connection to be set
     */
    public void setConnection(Connection connection){
        this.connection = connection;
    }

    /**
     * Method for mapping ResultSet into Object
     * @param rs result set from database
     * @return a bean object for a specific table
     */
    public abstract T row2object(ResultSet rs) throws SQLException;

    /**
     * Mehtod for mapping Object into Map
     * @param object a bean object for a specific table
     * @return value of object in a sorted map
     */
    public abstract Map<String, Object> object2row(T object);

}
