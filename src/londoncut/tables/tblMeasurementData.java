package londoncut.tables;

public class tblMeasurementData {

    private String suitnumber;
    private String type;
    private String material;
    private String measurement;
    private int qty;
    private String deliverydate;
    

    public tblMeasurementData(String suitnumber, String type, String material, String measurement, int qty, String deliverydate) {
        this.suitnumber = suitnumber;
        this.type = type;
        this.material = material;
        this.measurement = measurement;
        this.qty = qty;
        this.deliverydate = deliverydate;
        

    }

    public tblMeasurementData(String string, String string0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
     * @return the materia
     */
    public String getMaterial() {
        return material;
    }

    /**
     * @param materia the materia to set
     */
    public void setMateria(String materia) {
        this.material = materia;
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

    

    
}
