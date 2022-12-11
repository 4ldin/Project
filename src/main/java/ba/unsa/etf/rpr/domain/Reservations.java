package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

/**
 * Class for recording different reservations.
 * @author Aldin Islamagic
 */

public class Reservations {
    private int id;
    private Date reservationDate;
    private Date arrivalDate;
    private Date checkOutDate;
    private int numPersons;
    private Guests guest;

    public Guests getGuest(){
        return guest;
    }

    public void setGuest(Guests guest){
        this.guest = guest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservations that = (Reservations) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String
    toString() {
        return "Reservations{" +
                "id=" + id +
                ", reservationDate=" + reservationDate +
                ", arrivalDate=" + arrivalDate +
                ", checkOutDate=" + checkOutDate +
                ", numPersons=" + numPersons +
                '}';
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Date getReservationDate(){
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate){
        this.reservationDate = reservationDate;
    }

    public Date getArrivalDate(){
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate){
        this.arrivalDate = arrivalDate;
    }

    public Date getCheckOutDate(){
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate){
        this.checkOutDate = checkOutDate;
    }

    public int getNumPersons(){
        return numPersons;
    }

    public void setNumPersons(int numPersons){
        this.numPersons = numPersons;
    }

}
