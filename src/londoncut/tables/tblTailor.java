
package londoncut.tables;


public class tblTailor {
    private String tailorid;
    private String tailorname;
    

    public tblTailor(String tailorid,String name){
        this.tailorid=tailorid;
        this.tailorname=name;
        
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
     * @return the tailorname
     */
    public String getTailorname() {
        return tailorname;
    }

    /**
     * @param tailorname the tailorname to set
     */
    public void setTailorname(String tailorname) {
        this.tailorname = tailorname;
    }

    
}
