package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Guests;

import java.sql.*;
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
        String query = "SELECT * FROM Guests WHERE id = ?";
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Guests update(Guests item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Guests> getAll() {
        return null;
    }
}
