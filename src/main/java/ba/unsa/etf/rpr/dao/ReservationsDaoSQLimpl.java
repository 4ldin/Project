package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Guests;
import ba.unsa.etf.rpr.domain.Reservations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class ReservationsDaoSQLimpl extends AbstractDao<Reservations> implements ReservationsDao{


    /**
     * Constructor for ReservationsDao that connects to the database
     */
    public ReservationsDaoSQLimpl(){
        super("Reservations");
    }


    /**
     * Method that sets parems. of guest from given attribute
     * @param rs result set from database
     * @return reservation object
     */
    @Override
    public Reservations row2object(ResultSet rs){
        try {
            Reservations reservation = new Reservations();
            reservation.setId(rs.getInt("Reservation_id"));
            reservation.setReservationDate(rs.getDate("Reservation_date"));
            reservation.setArrivalDate(rs.getDate("Arrival_date"));
            reservation.setCheckOutDate(rs.getDate("Check_out_date"));
            reservation.setNumPersons(rs.getInt("Num_persons"));
            reservation.setGuest(new GuestDaoSQLimpl().getById(rs.getInt("Guest_id")));
            return reservation;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Method that turns given object to sql query
     * @param object a bean object for a specific table
     * @return Map of object
     */
    @Override
    public Map<String, Object> object2row(Reservations object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("Reservation_id", object.getId());
        row.put("Reservation_date", object.getReservationDate());
        row.put("Arrival_date", object.getArrivalDate());
        row.put("Check_out_date", object.getCheckOutDate());
        row.put("Num_persons", object.getNumPersons());
        row.put("Guest_id", object.getGuest().getId());
        return row;
    }

    /**
     * Method that returns guest for given id of a reservation
     * @param id of guest
     * @return Guest with given id
     */
    public Guests getGuestById(int id){
        String query = "SELECT * FROM Guests WHERE id = (SELECT r.Guest_id FROM Reservations r WHERE r.Reservation_id = ?)";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Guests guest = new Guests();
                guest.setId(rs.getInt("id"));
                guest.setFirstName(rs.getString("First_name"));
                guest.setLastName(rs.getString("Last_Name"));
                rs.close();
                return guest;
            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
