package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.RoomTypes;
import ba.unsa.etf.rpr.domain.Rooms;

import java.sql.Connection;
import java.sql.DriverManager;
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

    @Override
    public Rooms getById(int id) {
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
