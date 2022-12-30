package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Guests;
import ba.unsa.etf.rpr.domain.Reservations;
import ba.unsa.etf.rpr.domain.RoomTypes;
import ba.unsa.etf.rpr.domain.Rooms;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RoomDaoSQLimpl extends AbstractDao<Rooms> implements RoomDao{


    /**
     * Constructor for RoomDao that connects to the database
     */
    public RoomDaoSQLimpl(){
        super("Rooms");
    }

    /**
     * Method that sets parems. of room from given attribute
     * @param rs result set from database
     * @return room object
     */
    @Override
    public Rooms row2object(ResultSet rs) throws SQLException {
        try {
            Rooms room = new Rooms();
            room.setId(rs.getInt("Room_id"));
            room.setOccupancy(rs.getBoolean("Occupancy"));
            room.setReservation(new ReservationsDaoSQLimpl().getById(rs.getInt("Reservations_id")));
            room.setRoomType(new RoomTypeDaoSQLimpl().getById(rs.getInt("Room_type_id")));
            return room;
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
    public Map<String, Object> object2row(Rooms object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("Room_id", object.getId());
        row.put("Occupancy", object.getOccupancy());
        row.put("Reservations_id", object.getReservation().getId());
        row.put("Room_Type_id", object.getRoomType().getId());
        return row;
    }

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

    /**
     * Method that returns reservation for given id of a room
     * @param id of room
     * @return Reservation with given id
     */
    public Reservations getReservationById(int id){
        String query = "SELECT * FROM Reservations WHERE id = (SELECT r.Reservations_id FROM Rooms r WHERE r.Room_id = ?)";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
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
            PreparedStatement stmt = getConnection().prepareStatement(query);
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


    @Override
    public List<Rooms> searchByRoomType(RoomTypes roomType) {
        return null;
    }
}
