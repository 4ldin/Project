package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.RoomTypes;

import java.sql.Connection;
import java.sql.DriverManager;
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
    @Override
    public RoomTypes getById(int id) {
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
