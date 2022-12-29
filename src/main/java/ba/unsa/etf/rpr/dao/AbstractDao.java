package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import jdk.jshell.spi.ExecutionControl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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

    /**
     * Method that searches object by given id
     * @param id primary key of entity
     * @return object with given id
     */
    public T getById(int id){
        return executeQueryUnique("SELECT * FROM " + this.tableName + "WHERE id = ?", new Object[]{id});
    }

    /**
     * Method that returns gets all objects from table in db
     * @return list of object
     */
    public List<T> getAll(){
        return executeQuery("SELECT * FROM" + this.tableName, null);
    }

    /**
     * Method that deletes object with given id
     * @param id - primary key of entity
     */
    public void delete(int id){
        String query = "DELETE FROM" + this.tableName + "WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setObject(1, id);
            stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that executes any kind of query
     * @param query query to be executed
     * @param params parameters for query
     * @return list of results of the given query
     */
    public List<T> executeQuery(String query, Object[] params){
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            if(params != null){
                for(int i = 1; i <= params.length; i++){
                    stmt.setObject(i, params[i-1]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            ArrayList<T> resultList = new ArrayList<>();
            if(rs.next()){
                resultList.add(row2object(rs));
            }
            return resultList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that executes query that witch result is only one object
     * @param query query to be executed
     * @param params parameters for query
     * @return result of the query
     */
    public T executeQueryUnique(String query, Object[] params){
        List<T> result = executeQuery(query, params);
        if(result != null && result.size() == 1){
            return result.get(0);
        }else{
            return null;
        }
    }

}
