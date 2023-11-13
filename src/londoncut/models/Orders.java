
package londoncut.models;

//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;


public class Orders {

    
    private String ordernumber;
    private String date;

    public Orders(String ordernumber,String date){
        this.ordernumber=ordernumber;
        this.date=date;
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
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
}
