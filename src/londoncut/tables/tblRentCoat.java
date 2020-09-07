
package londoncut.tables;


public class tblRentCoat {
    private String number;
    private double price;

    public tblRentCoat(String number,double price){
        this.number=number;
        this.price=price;
    }
    
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
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
