
package londoncut.models;


public class SellProduct {
    
    private String ordernumber;
    private String productname;
    private int qty;
    private int price;

    public SellProduct(String ordernumber,String productname,int qty,int price){
        this.ordernumber=ordernumber;
        this.productname=productname;
        this.qty=qty;
        this.price=price;
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
     * @return the productid
     */
    public String getProductname() {
        return productname;
    }

    /**
     * @param productid the productid to set
     */
    public void setProductname(String productname) {
        this.productname = productname;
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
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }
    
}
