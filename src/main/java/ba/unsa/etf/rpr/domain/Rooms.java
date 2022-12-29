package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Class for representing different rooms
 * @author Aldin Islamagic
 */

public class Rooms implements Idable{

    private int id;
    private boolean occupancy;
    private Reservations reservation;
    private RoomTypes roomType;

    public Reservations getReservation(){
        return reservation;
    }

    public void setReservation(Reservations reservation){
        this.reservation = reservation;
    }

    public RoomTypes getRoomType(){
        return roomType;
    }

    public void setRoomType(RoomTypes roomType){
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Rooms{" +
                "id=" + id +
                ", occupancy=" + occupancy +
                ", reservation=" + reservation +
                ", roomType=" + roomType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rooms rooms = (Rooms) o;
        return id == rooms.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Boolean getOccupancy(){
        return occupancy;
    }

    public void setOccupancy(boolean occupancy){
        this.occupancy = occupancy;
    }

}
