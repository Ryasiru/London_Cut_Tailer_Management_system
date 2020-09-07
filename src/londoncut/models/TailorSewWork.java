
package londoncut.models;


public class TailorSewWork {
    private String ordernumber;
    private String suitnumber;
    private String type;
    private String measurement;
    private String deliverydate;
    private int qty;

    public TailorSewWork(String order,String suitnumber,String type,String measurement,String deliverydate,int qty){
        this.ordernumber=order;
        this.suitnumber=suitnumber;
        this.type=type;
        this.measurement=measurement;
        this.deliverydate=deliverydate;
        this.qty=qty;
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
