package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Guests;
import ba.unsa.etf.rpr.domain.Reservations;
import ba.unsa.etf.rpr.domain.RoomTypes;
import ba.unsa.etf.rpr.domain.Rooms;

import java.sql.*;
import java.util.List;

public class RoomDaoSQLimpl implements RoomDao{

    private Connection connection;

    /**
     * Constructor for GuestDao that connects to the database
     * @param connection
     */
    public RoomDaoSQLimpl(Connection connection) {
        try{
            this.connection = DriverManager.getConnection("jdbc://sql7.freemysqlhosting.net:3306/user", "user", "password");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
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
     * Method that returns reservation for given id of a room
     * @param id of room
     * @return Reservation with given id
     */
    public Reservations getReservationById(int id){
        String query = "SELECT * FROM Reservations WHERE id = (SELECT r.Reservations_id FROM Rooms r WHERE r.Room_id = ?)";
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
            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method that returns room type for given id of a room
     * @param id of room
     * @return Room type with given id
     */
    public RoomTypes getRoomTypeById(int id){
        String query = "SELECT * FROM Room_Types WHERE id = (SELECT r.Room_type_id FROM Rooms r WHERE r.Room_id = ?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                RoomTypes roomType = new RoomTypes();
                roomType.setId(rs.getInt("Room_type_id"));
                roomType.setRoomType(rs.getString("Room_type"));
                roomType.setPrice(rs.getInt("Room_price"));
                rs.close();
                return roomType;
            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method that finds Room with matching id
     * @param id primary key of entity
     * @return Room with given id
     */
    @Override
    public Rooms getById(int id) {
        String query = "SELECT * FROM Rooms WHERE Room_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Rooms room = new Rooms();
                room.setId(rs.getInt("Room_id"));
                room.setOccupancy(rs.getBoolean("Occupancy"));
                room.setReservation(getReservationById(id));
                room.setRoomType(getRoomTypeById(id));
                rs.close();
                return room;
            }else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Rooms add(Rooms item) {
        return null;
    }

    @Override
    public Rooms update(Rooms item, int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Rooms> getAll() {
        return null;
    }

    @Override
    public List<Rooms> searchByRoomType(RoomTypes roomType) {
        return null;
    }
}
