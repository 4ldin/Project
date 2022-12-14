package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Reservations;
import ba.unsa.etf.rpr.domain.RoomTypes;

import java.sql.*;
import java.util.List;

public class RoomTypeDaoimpl implements RoomTypeDao{

    private Connection connection;

    /**
     * Constructor for RoomTypeDaoimpl that connects to the database
     * @param connection
     */
    public RoomTypeDaoimpl(Connection connection) {
        try{
            this.connection = DriverManager.getConnection("jdbc://sql7.freemysqlhosting.net:3306/user", "user", "password");
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
        String query = "SELECT * FROM Room_Types WHERE RRoom_type_id = ?";
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

    @Override
    public RoomTypes add(RoomTypes item) {
        return null;
    }

    @Override
    public RoomTypes update(RoomTypes item, int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<RoomTypes> getAll() {
        return null;
    }
}
