package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Guests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GuestDaoSQLimpl implements GuestDao{

    private Connection connection;

    /**
     * Constructor for GuestDao that connects to the database with hidden database name and password
     */
    public GuestDaoSQLimpl() throws IOException {
        String fieldPath = "src/dataBase.properties";
        Properties pros = new Properties();
        FileInputStream ip = new FileInputStream(fieldPath);
        pros.load(ip);
        try{
            this.connection = DriverManager.getConnection(pros.getProperty("url"), pros.getProperty("username"), pros.getProperty("password"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Guests getById(int id) {
        String query = "SELECT * FROM Guests WHERE Guest_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Guests guest = new Guests();
                guest.setId(rs.getInt("id"));
                guest.setFirstName(rs.getString("First_name"));
                guest.setLastName(rs.getString("Last_Name"));
                guest.seteMail(rs.getString("eMail"));
                guest.setPassword(rs.getString("password"));
                rs.close();
                return guest;
            }else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Insert item in database
     * @param item item to save in database
     */
    @Override
    public Guests add(Guests item) {
        String insert = "INSERT INTO Guests (First_name, Last_name, eMail, password) VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert);
            stmt.setString(1, item.getFirstName());
            stmt.setString(2, item.getLastName());
            stmt.setString(3,item.geteMail());
            stmt.setString(4,item.getPassword());
            stmt.executeUpdate(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method that updates Guest
     * @param item - item from which values are taken
     * @param id - id of guest that gets updated
     * @return updated Guest
     */
    @Override
    public Guests update(Guests item, int id) {
        String query = "UPDATE Guests SET First_name = ?, Last_name = ?, eMail = ?, password = ? WHERE Guest_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, item.getFirstName());
            stmt.setString(2, item.getLastName());
            stmt.setString(3, item.geteMail());
            stmt.setString(4, item.getPassword());
            stmt.setInt(5, id);
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method that deletes guest with matching id
     * @param id - id of guest to be deleted
     */
    @Override
    public void delete(int id) {
        String query = "DELETE FROM Guests WHERE Guest_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lists all guests from database
     * @return List of all guests from database
     */
    @Override
    public List<Guests> getAll() {
        String query = "SELECT * FROM Guests";
        List<Guests> guests = new ArrayList<>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Guests guest = new Guests();
                guest.setId(rs.getInt("Guest_id"));
                guest.setFirstName(rs.getString("First_name"));
                guest.setLastName(rs.getString("Last_name"));
                guest.seteMail(rs.getString("eMail"));
                guest.setPassword(rs.getString("password"));
                guests.add(guest);
            }
            rs.close();
            return guests;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guests;
    }

    /**
     * Method that finds guest with given email and password
     * @param email email of guest
     * @param password password of guest
     * @return guest
     */

    @Override
    public Guests getByEmailPassword(String email, String password){
        String query = "SELECT * FROM Guests WHERE eMail = ? AND password = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1,email);
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery();
            Guests guest = new Guests();
            while(rs.next()){
                guest.setId(rs.getInt("Guest_id"));
                guest.setFirstName(rs.getString("First_name"));
                guest.setLastName(rs.getString("Last_name"));
                guest.seteMail(rs.getString("eMail"));
                guest.setPassword(rs.getString("password"));
            }
            rs.close();
            return guest;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
