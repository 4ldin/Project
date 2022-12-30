package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.RoomTypes;

import java.sql.*;
import java.util.Map;

public class RoomTypeDaoSQLimpl extends AbstractDao<RoomTypes> implements RoomTypeDao{

    /**
     * Constructor for RoomTypeDaoimpl that connects to the database
     */
    public RoomTypeDaoSQLimpl(){
        super("Room_Types");
    }

    @Override
    public RoomTypes row2object(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Map<String, Object> object2row(RoomTypes object) {
        return null;
    }
}
