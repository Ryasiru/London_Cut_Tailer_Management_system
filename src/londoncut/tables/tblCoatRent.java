
package londoncut.tables;


public class tblCoatRent {

    private String coatnumber;
    private String coatcolour;
    private double price;

    public tblCoatRent(String number,String colour,double price){
        this.coatnumber=number;
        this.coatcolour=colour;
        this.price=price;
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
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
