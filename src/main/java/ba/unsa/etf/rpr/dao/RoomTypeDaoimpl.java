package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Reservations;
import ba.unsa.etf.rpr.domain.RoomTypes;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeDaoimpl implements RoomTypeDao{

    private Connection connection;

    /**
     * Constructor for RoomTypeDaoimpl that connects to the database
     */
    public RoomTypeDaoimpl() {
        String username = "";
        try {
            FileInputStream file = new FileInputStream("username.txt");
            int myData;
            while((myData = file.read()) != -1){
                username = username + (char)myData;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String password = "";
        try {
            FileInputStream file = new FileInputStream("password.txt");
            int myData;
            while((myData = file.read()) != -1){
                password = password + (char)myData;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/" + username, username, password);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method that finds Room_type with matching id
     * @param id primary key of entity
     * @return Room_type with given id
     */
    @Override
    public RoomTypes getById(int id) {
        String query = "SELECT * FROM Room_Types WHERE Room_type_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                RoomTypes roomType = new RoomTypes();
                roomType.setId(rs.getInt("Room_type_id"));
                roomType.setRoomType(rs.getString("Room_type"));
                roomType.setPrice(rs.getDouble("Room_price"));
                rs.close();
                return roomType;
            }else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method that adds given room type in database
     * @param item item to save in database
     * @return
     */
    @Override
    public RoomTypes add(RoomTypes item) {
        String insert = "INSERT INTO Room_Types (Room_type, Room_price) VALUES(?, ?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert);
            stmt.setString(1, item.getRoomType());
            stmt.setDouble(2, item.getPrice());
            stmt.executeUpdate(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method that updates a room type
     * @param item - item to be updated
     * @param id - id of room type to be updated
     * @return updated room type
     */
    @Override
    public RoomTypes update(RoomTypes item, int id) {
        String query = "UPDATE Room_Types SET Room_type = ?, Room_price = ? WHERE Room_type_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, item.getRoomType());
            stmt.setDouble(2, item.getPrice());
            stmt.setInt(3, id);
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method that deletes room type with matching id
     * @param id - primary key of entity
     */
    @Override
    public void delete(int id) {
        String query = "DELETE FROM Room_Types WHERE Room_type_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RoomTypes> getAll() {
        String query = "SELECT * FROM Room_Types ";
        List<RoomTypes> roomTypes = new ArrayList<>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                RoomTypes roomType = new RoomTypes();
                roomType.setId(rs.getInt("Room_type_id"));
                roomType.setRoomType(rs.getString("Room_type"));
                roomType.setPrice(rs.getDouble("Room_price"));
                roomTypes.add(roomType);
            }
            rs.close();
            return roomTypes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomTypes;
    }
}
