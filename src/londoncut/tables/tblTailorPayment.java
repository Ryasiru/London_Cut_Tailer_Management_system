
package londoncut.tables;


public class tblTailorPayment {
    
    private String id;
    private String fname;
    private String lname;
    private int work;
    private double payment;

    public tblTailorPayment(String id,String fname,String lname,int work,double payment){
        this.id=id;
        this.fname=fname;
        this.lname=lname;
        this.work=work;
        this.payment=payment;
                
    }
    
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
     * @return the work
     */
    public int getWork() {
        return work;
    }

    /**
     * @param work the work to set
     */
    public void setWork(int work) {
        this.work = work;
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
}
