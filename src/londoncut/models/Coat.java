
package londoncut.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class Coat {
    
     private String coatnumber;
     private String coatcolour;
     private int price;
     private String status;

    public Coat(String coatnumber,String coatcolour,int price,String status){
        this.coatnumber=coatnumber;
        this.coatcolour=coatcolour;
        this.price=price;
        this.status=status;
    }
    
    public String getCoatnumber() {
        return coatnumber;
    }

    /**
     * @param coatnumber the coatnumber to set
     */
    public void setCoatnumber(String coatnumber) {
        this.coatnumber = coatnumber;
    }

    /**
     * @return the coatcolour
     */
    public String getCoatcolour() {
        return coatcolour;
    }

    /**
     * @param coatcolour the coatcolour to set
     */
    public void setCoatcolour(String coatcolour) {
        this.coatcolour = coatcolour;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
