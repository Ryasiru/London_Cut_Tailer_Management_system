
package londoncut.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class RentCoatDetail {
    
    private String coatnumeber;
    private String rentdate;
    private String returndate;
    private double rentprice;
    private double fine;

    public RentCoatDetail(String coatnumber,String rentdate,String returndate,double rentprice,double fine){
        this.coatnumeber=coatnumber;
        this.rentdate=rentdate;
        this.fine=fine;
        this.rentprice=rentprice;
        this.returndate=returndate;
                
    }
    
    public String getCoatnumeber() {
        return coatnumeber;
    }

    /**
     * @param coatnumeber the coatnumeber to set
     */
    public void setCoatnumeber(String coatnumeber) {
        this.coatnumeber = coatnumeber;
    }

    /**
     * @return the returndate
     */
    public String getReturndate() {
        return returndate;
    }

    /**
     * @param returndate the returndate to set
     */
    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }

    /**
     * @return the rentprice
     */
    public double getRentprice() {
        return rentprice;
    }

    /**
     * @param rentprice the rentprice to set
     */
    public void setRentprice(double rentprice) {
        this.rentprice = rentprice;
    }

    /**
     * @return the fine
     */
    public double getFine() {
        return fine;
    }

    /**
     * @param fine the fine to set
     */
    public void setFine(double fine) {
        this.fine = fine;
    }

    /**
     * @return the rentdate
     */
    public String getRentdate() {
        return rentdate;
    }

    /**
     * @param rentdate the rentdate to set
     */
    public void setRentdate(String rentdate) {
        this.rentdate = rentdate;
    }
}
