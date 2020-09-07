
package londoncut.models;


public class SuitOrder {
    private String suitnumber;
    private String ordernumber;

    public SuitOrder(String suitnumber,String ordernumber){
        this.suitnumber=suitnumber;
        this.ordernumber=ordernumber;
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
