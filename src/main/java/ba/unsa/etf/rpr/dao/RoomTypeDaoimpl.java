package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Reservations;
import ba.unsa.etf.rpr.domain.RoomTypes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class RoomTypeDaoimpl extends AbstractDao<RoomTypes> implements RoomTypeDao{

    /**
     * Constructor for RoomTypeDaoimpl that connects to the database
     */
    public RoomTypeDaoimpl(){
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
