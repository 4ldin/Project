package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.GuestDao;
import ba.unsa.etf.rpr.dao.GuestDaoSQLimpl;
import ba.unsa.etf.rpr.domain.Guests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        Guests guest = new Guests();
        GuestDao dao = new GuestDaoSQLimpl();
        List<Guests> guests = dao.getAll();
        System.out.println(dao.getByEmailPassword("aislamagic1@gmail.com", "123").geteMail());
    }
}
