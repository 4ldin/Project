package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Guests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class GuestDaoSQLimpl extends AbstractDao<Guests> implements GuestDao{

    public GuestDaoSQLimpl(){
        super("Guests");
    }

    /**
     * Method that sets parems. of guest from given attribute
     * @param rs result set from database
     * @return guest object
     */
    @Override
    public Guests row2object(ResultSet rs){
        try {
            Guests guest = new Guests();
            guest.setId(rs.getInt("Guest_id"));
            guest.setFirstName(rs.getString("First_name"));
            guest.setLastName(rs.getString("Last_name"));
            guest.seteMail(rs.getString("eMail"));
            guest.setPassword(rs.getString("password"));
            return guest;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Map<String, Object> object2row(Guests object) {
        return null;
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
            PreparedStatement stmt = getConnection().prepareStatement(query);
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
