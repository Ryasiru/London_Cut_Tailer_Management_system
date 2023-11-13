
package londoncut.models;

//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;


public class OrderDetail {

    
    private String ordernumber;
    private String customerid;

    public OrderDetail(String ordernumber,String customerid){
        this.ordernumber=ordernumber;
        this.customerid=customerid;
    }
    
    
    public String getOrdernumber() {
        return ordernumber;
    }

    /**
     * @param ordernumber the ordernumber to set
     */
    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    /**
     * @return the customerid
     */
    public String getCustomerid() {
        return customerid;
    }

    /**
     * @param customerid the customerid to set
     */
    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }
}
