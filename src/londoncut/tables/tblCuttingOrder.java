
package londoncut.tables;


public class tblCuttingOrder {
    private String ordernumber;
    private String suitnumber;
    private String type;
    private String measurement;
    private String material;
    private String quality;
    private int qty;
    private String deliverydate;

    public tblCuttingOrder(String order,String suitnumber,String type,String measurement,String material,String quality,int qty,String deliverydate){
        this.ordernumber=order;
        this.suitnumber=suitnumber;
        this.type=type;
        this.measurement=measurement;
        this.material=material;
        this.quality=quality;
        this.qty=qty;
        this.deliverydate=deliverydate;
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

    /**
     * @return the ordernumber
     */
    public String getOrdernumber() {
        return ordernumber;
    }

    /**
     * @param ordernumber the ordernumber to set
     */
    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

}
