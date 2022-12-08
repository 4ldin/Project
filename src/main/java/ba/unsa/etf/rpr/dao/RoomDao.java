package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.RoomTypes;
import ba.unsa.etf.rpr.domain.Rooms;

import java.util.List;

/**
 * Interface for Rooms
 * @author Aldin Islamagic
 */

public interface RoomDao extends Dao<Rooms>{

    /**
     * Returns all room numbers of the requested category.
     * @param roomType
     * @return list of room numbers of given category
     */
    List<Rooms> searchByRoomType(RoomTypes roomType);

}
