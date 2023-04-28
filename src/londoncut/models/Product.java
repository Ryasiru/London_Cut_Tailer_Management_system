
package londoncut.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class Product {
    
    private String productid;
    private String productname;
    private int unitprice;
    private int qty;
    private String date;

    public Product(String productid,String productname,int unitprice,int qty,String date){
        this.productid=productid;
        this.productname=productname;
        this.unitprice=unitprice;
        this.qty=qty;
        this.date=date;
    }
    
    public String getProductid() {
        return productid;
    }

    /**
     * @param productid the productid to set
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }

    /**
     * @return the productname
     */
    public String getProductname() {
        return productname;
    }

    /**
     * @param productname the productname to set
     */
    public void setProductname(String productname) {
        this.productname = productname;
    }

    /**
     * @return the unitprice
     */
    public int getUnitprice() {
        return unitprice;
    }

    /**
     * @param unitprice the unitprice to set
     */
    public void setUnitprice(int unitprice) {
        this.unitprice = unitprice;
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
