
package londoncut.models;

//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;


public class Tailor {

    
    private String tailorid;
    private String fname;
    private String lname;
    private String address;
    private int home;
    private int mobile;
    private String nic;

    private double salary;

    public Tailor(String tailorid,String fname,String lname,String address,int home,int mobile,String nic){
       this.tailorid=tailorid;
       this.fname=fname;
       this.lname=lname;
       this.address=address;
       this.home=home;
       this.mobile=mobile;
       this.nic=nic;
    }
    
    public String getTailorid() {
        return tailorid;
    }

    /**
     * @param tailorid the tailorid to set
     */
    public void setTailorid(String tailorid) {
        this.tailorid = tailorid;
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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the home
     */
    public int getHome() {
        return home;
    }

    /**
     * @param home the home to set
     */
    public void setHome(int home) {
        this.home = home;
    }

    /**
     * @return the mobile
     */
    public int getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(int mobile) {
        this.mobile = mobile;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
