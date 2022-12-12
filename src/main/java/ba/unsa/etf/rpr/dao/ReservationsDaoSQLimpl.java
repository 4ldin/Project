package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Guests;
import ba.unsa.etf.rpr.domain.Reservations;

import java.sql.*;
import java.util.List;

public class ReservationsDaoSQLimpl implements ReservationsDao{

    private Connection connection;

    /**
     * Constructor for ReservationsDaoSQLimpl that connects to the database
     * @param connection
     */
    public ReservationsDaoSQLimpl(Connection connection) {
        try{
            this.connection = DriverManager.getConnection("jdbc://sql7.freemysqlhosting.net:3306/user", "user", "password");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method that returns guest for given id of a reservation
     * @param id of guest
     * @return Guest with given id
     */
    public Guests getGuestById(int id){
        String query = "SELECT * FROM Guests WHERE id = (SELECT r.Guest_id FROM Reservations r WHERE r.Reservation_id = ?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Guests guest = new Guests();
                guest.setId(rs.getInt("id"));
                guest.setFirstName(rs.getString("First_name"));
                guest.setLastName(rs.getString("Last_Name"));
                guest.setPhone(rs.getInt("Phone"));
                guest.setPassportNumber(rs.getInt("Passport_number"));
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

    /**
     * Method that finds Reservation with mathcing id
     * @param id primary key of entity
     * @return Reservation with given id
     */
    @Override
    public Reservations getById(int id) {
        String query = "SELECT * FROM reservations WHERE Reservation_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Reservations reservation = new Reservations();
                reservation.setId(rs.getInt("Reservation_id"));
                reservation.setReservationDate(rs.getDate("Reservation_date"));
                reservation.setArrivalDate(rs.getDate("Arrival_date"));
                reservation.setCheckOutDate(rs.getDate("Check_out_date"));
                reservation.setNumPersons(rs.getInt("Num_persons"));
                reservation.setGuest(getGuestById(id));
                rs.close();
                return reservation;
            }else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method that adds given reservation in database
     * @param item item to save in database
     * @return
     */
    @Override
    public Reservations add(Reservations item) {
        String insert = "INSERT INTO Reservations (Reservation_date, Arrival_date, Check_out_date, Num_persons, Guest_id) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert);
            stmt.setDate(1, (Date) item.getReservationDate());
            stmt.setDate(2, (Date) item.getArrivalDate());
            stmt.setDate(3, (Date) item.getCheckOutDate());
            stmt.setInt(4, item.getNumPersons());
            stmt.setInt(5, item.getGuest().getId());
            stmt.executeUpdate(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method that updates a reservation
     * @param item - item to be updated
     * @param id - id of reservation to be updated
     * @return updated reservation
     */
    @Override
    public Reservations update(Reservations item, int id) {
        String query = "UPDATE Reservations SET Reservation_date = ?, Arrival_date = ?, Check_out_date = ?, Num_persons = ?, Guest_id = ? WHERE Reservation_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setDate(1, (Date) item.getReservationDate());
            stmt.setDate(2, (Date) item.getArrivalDate());
            stmt.setDate(3, (Date) item.getCheckOutDate());
            stmt.setInt(4, item.getNumPersons());
            stmt.setInt(5, item.getGuest().getId());
            stmt.setInt(6, id);
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method that deletes reservation with matching id
     * @param id - primary key of entity
     */
    @Override
    public void delete(int id) {
        String query = "DELETE FROM Reservations WHERE Reservation_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Reservations> getAll() {
        return null;
    }
}
