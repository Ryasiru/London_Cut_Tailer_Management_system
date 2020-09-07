
package londoncut.tables;


public class tblDeleteCoat {
    private String coatnumber;
    private String colour;
    private int price;
    private String status;

    public tblDeleteCoat(String coatnumber,String colour,int price,String status){
        this.coatnumber=coatnumber;
        this.colour=colour;
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
     * @return the colour
     */
    public String getColour() {
        return colour;
    }

    /**
     * @param colour the colour to set
     */
    public void setColour(String colour) {
        this.colour = colour;
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
