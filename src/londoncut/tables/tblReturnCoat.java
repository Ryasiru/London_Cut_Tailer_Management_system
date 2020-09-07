
package londoncut.tables;


public class tblReturnCoat {
    private String nic;
    private String name;
    private String coatnumber;
    private String rentdate;
    private String returndate;

    public tblReturnCoat(String nic,String name,String coatnumber,String rentdate,String returndate){
        this.nic=nic;
        this.name=name;
        this.coatnumber=coatnumber;
        this.rentdate=rentdate;
        this.returndate=returndate;
    }
    
    public String getNic() {
        return nic;
    }

    /**
     * @param nic the nic to set
     */
    public void setNic(String nic) {
        this.nic = nic;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the coatnumber
     */
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
    
}
