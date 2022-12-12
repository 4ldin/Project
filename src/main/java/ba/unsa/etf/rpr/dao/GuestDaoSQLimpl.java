package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Guests;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestDaoSQLimpl implements GuestDao{

    private Connection connection;

    /**
     * Constructor for GuestDao that connects to the database
     * @param connection
     */
    public GuestDaoSQLimpl(Connection connection) {
        try{
            this.connection = DriverManager.getConnection("jdbc://sql7.freemysqlhosting.net:3306/user", "user", "password");
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
                guest.setFirstName(rs.getString("firstName"));
                guest.setLastName(rs.getString("lastName"));
                guest.setPhone(rs.getInt("phone"));
                guest.setPassportNumber(rs.getInt("passportNumber"));
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
        String insert = "INSERT INTO Guests (First_name, Last_name, Phone, Passport_number) VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert);
            stmt.setString(1, item.getFirstName());
            stmt.setString(2, item.getLastName());
            stmt.setInt(3, item.getPhone());
            stmt.setInt(4, item.getPassportNumber());
            stmt.executeUpdate();
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
        String query = "UPDATE Guests SET First_name = ?, Last_name = ?, Phone = ?, Passport_number = ? WHERE Guest_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, item.getFirstName());
            stmt.setString(2, item.getLastName());
            stmt.setInt(3, item.getPhone());
            stmt.setInt(4, item.getPassportNumber());
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Deletes that deletes guest with matching id
     * @param id - id of guest to be deleted
     */
    @Override
    public void delete(int id) {
        String query = "DELETE FROM Guests WHERE Guest_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
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
        String query = "SELECT * FROM Guests WHERE Guest_id = ?";
        List<Guests> guests = new ArrayList<>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Guests guest = new Guests();
                guest.setId(rs.getInt("Guest_id"));
                guest.setFirstName(rs.getString("First_name"));
                guest.setLastName(rs.getString("Last_name"));
                guest.setPhone(rs.getInt("Phone"));
                guest.setPassportNumber(rs.getInt("Passport_number"));
                guests.add(guest);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guests;
    }
}
