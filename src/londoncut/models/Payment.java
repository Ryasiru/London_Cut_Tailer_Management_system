
package londoncut.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class Payment {

    
    private String ordernumber;
    private String paymentdate;
    private double payment;
    private double total;

    public Payment(String ordernumber,String paymentdate,double payment,double total){
        this.ordernumber=ordernumber;
        this.paymentdate=paymentdate;
        this.payment=payment;
        this.total=total;
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
     * @return the paymentdate
     */
    public String getPaymentdate() {
        return paymentdate;
    }

    /**
     * @param paymentdate the paymentdate to set
     */
    public void setPaymentdate(String paymentdate) {
        this.paymentdate = paymentdate;
    }

    /**
     * @return the payment
     */
    public double getPayment() {
        return payment;
    }

    /**
     * @param payment the payment to set
     */
    public void setPayment(double payment) {
        this.payment = payment;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }
}
