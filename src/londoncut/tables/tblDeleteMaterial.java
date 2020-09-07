
package londoncut.tables;


public class tblDeleteMaterial {
    private String materialnumber;
    private String material;
    private String quality;
    private double qty;

    public tblDeleteMaterial(String id,String name,String quality,double qty){
        this.material=name;
        this.materialnumber=id;
        this.qty=qty;
        this.quality=quality;
    }
    
    public String getMaterialnumber() {
        return materialnumber;
    }

    /**
     * @param materialnumebr the materialnumebr to set
     */
    public void setMaterialnumber(String materialnumebr) {
        this.materialnumber = materialnumebr;
    }

    /**
     * @return the material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * @return the quality
     */
    public String getQuality() {
        return quality;
    }

    /**
     * @param quality the quality to set
     */
    public void setQuality(String quality) {
        this.quality = quality;
    }

    /**
     * @return the qty
     */
    public double getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(double qty) {
        this.qty = qty;
    }
    
}
