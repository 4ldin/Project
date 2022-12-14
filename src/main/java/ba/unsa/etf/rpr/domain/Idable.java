package ba.unsa.etf.rpr.domain;

/**
 * Interface that forces all POJO beans to have ID field
 * @author Aldin Islamagic
 */

public interface Idable {
    void setId(int id);

    int getId();
}
