
package londoncut.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class Suit {

    
    private String suitnumber;
    private String type;
    private String material;
    private String quality;
    private String measurement;
    private int qty;
    private String deliverydate;
    private int price;
    

    public Suit(String suitnumber,String type,String material,String quality,String measurement,int qty,String deliverydate,int price){
        this.suitnumber=suitnumber;
        this.type=type;
        this.material=material;
        this.quality=quality;
        this.measurement=measurement;
        this.qty=qty;
        this.deliverydate=deliverydate;
        this.price=price;
        
        
        
    }
    
    
    public String getSuitnumber() {
        return suitnumber;
    }

    /**
     * @param suitnumber the suitnumber to set
     */
    public void setSuitnumber(String suitnumber) {
        this.suitnumber = suitnumber;
    }

   

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * @return the measurement
     */
    public String getMeasurement() {
        return measurement;
    }

    /**
     * @param measurement the measurement to set
     */
    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    /**
     * @return the deliverydate
     */
    public String getDeliverydate() {
        return deliverydate;
    }

    /**
     * @param deliverydate the deliverydate to set
     */
    public void setDeliverydate(String deliverydate) {
        this.deliverydate = deliverydate;
    }

   

    @Override
    public String toString() {
        return "Suit";
    }

    /**
     * @return the qty
     */
    public int getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(int qty) {
        this.qty = qty;
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
     * @return the quality
     */
    public String getQuality() {
        return quality;
    }

    /**
     * @param quality the quality to set
     */
    public void setQuality(String quality) {
        this.quality = quality;
    }
    
    
}
