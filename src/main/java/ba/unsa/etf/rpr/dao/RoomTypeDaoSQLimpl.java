package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.RoomTypes;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class RoomTypeDaoSQLimpl extends AbstractDao<RoomTypes> implements RoomTypeDao{

    /**
     * Constructor for RoomTypeDaoimpl that connects to the database
     */
    public RoomTypeDaoSQLimpl(){
        super("Room_Types");
    }

    /**
     * Method that sets parems. of room from given attribute
     * @param rs result set from database
     * @return room_type object
     */
    @Override
    public RoomTypes row2object(ResultSet rs) {
        try {
            RoomTypes roomType = new RoomTypes();
            roomType.setId(rs.getInt("Room_type_id"));
            roomType.setRoomType(rs.getString("Room_Type"));
            roomType.setPrice(rs.getDouble("Room_price"));
            return roomType;
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
    public Map<String, Object> object2row(RoomTypes object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("Room_type_id", object.getId());
        row.put("Room_type", object.getRoomType());
        row.put("Room_price", object.getPrice());
        return row;
    }
}
