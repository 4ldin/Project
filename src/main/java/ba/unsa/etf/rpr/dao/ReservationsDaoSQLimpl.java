package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Reservations;

import java.sql.Connection;
import java.sql.DriverManager;
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

    @Override
    public Reservations getById(int id) {
        return null;
    }

    @Override
    public Reservations add(Reservations item) {
        return null;
    }

    @Override
    public Reservations update(Reservations item, int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Reservations> getAll() {
        return null;
    }
}
