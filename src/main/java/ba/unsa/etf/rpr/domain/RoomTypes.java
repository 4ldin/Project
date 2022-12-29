package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Class for representing different types of rooms
 * @author Aldin Islamagic
 */

public class RoomTypes implements Idable{
    private int id;
    private String roomType;
    private double price;

    @Override
    public String toString() {
        return "RoomTypes{" +
                "id=" + id +
                ", roomType='" + roomType + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomTypes roomTypes = (RoomTypes) o;
        return id == roomTypes.id;
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

    public String getRoomType(){
        return roomType;
    }

    public void setRoomType(String roomType){
        this.roomType = roomType;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }
}
