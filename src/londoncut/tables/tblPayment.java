
package londoncut.tables;


public class tblPayment {
    private String type;
    private int qty;

    public tblPayment(String type,int qty){
        this.type=type;
        this.qty=qty;
    }
    
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
