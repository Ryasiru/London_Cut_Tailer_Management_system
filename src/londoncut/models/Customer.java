
package londoncut.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class Customer {
    
    private String customerid;
    private String fname;
    private String lname;
    private int contact;
    private String nic;

    public Customer(String id,String fname,String lname,int contact,String nic){
        this.customerid=id;
        this.fname=fname;
        this.lname=lname;
        this.contact=contact;
        this.nic=nic;
    }
    
    public String getCustomerid() {
        return customerid;
    }

    /**
     * @param customerid the customerid to set
     */
    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
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
     * @return the nic
     */
    public String getNic() {
        return nic;
    }

    /**
     * @param nic the nic to set
     */
    public void setNic(String nic) {
        this.nic = nic;
    }

    @Override
    public String toString() {
        return "Customer" ;
    }
    
    
    
}
