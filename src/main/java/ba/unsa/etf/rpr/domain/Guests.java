package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Class for representing different guests of the hotel.
 * @author Aldin Islamagic
 */

public class Guests implements Idable{
    private int id;
    private String firstName;
    private String lastName;
    private String eMail;
    private String password;

    @Override
    public String toString() {
        return "Guests{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", eMail='" + eMail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guests guests = (Guests) o;
        return id == guests.id;
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

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String geteMail(){ return eMail;};

    public void seteMail(String eMail){ this.eMail = eMail;};

    public String getPassword(){return password;};

    public void setPassword(String password){this.password = password;};


}
