
package londoncut.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class CoatRent {

    
    private String ordernumber;
    private String nic;
    private String customername;
    private int contact;
    private String coatnumber;
    private String rentdate;
    private String returndate;
    private Double rentprice;

   public CoatRent(String ordernumber,String nic,String name,int contact,String coatnumber,String rentdate,String returndate,double rentprice ){
       this.ordernumber=ordernumber;
       this.nic=nic;
       this.customername=name;
       this.contact=contact;
       this.coatnumber=coatnumber;
       this.rentdate=rentdate;
       this.returndate=returndate;
       this.rentprice=rentprice;
       
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
     * @return the customername
     */
    public String getCustomername() {
        return customername;
    }

    /**
     * @param customername the customername to set
     */
    public void setCustomername(String customername) {
        this.customername = customername;
    }

    /**
     * @return the contact
     */
    public int getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(int contact) {
        this.contact = contact;
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
