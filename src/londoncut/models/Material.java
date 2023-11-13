
package londoncut.models;

//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;


public class Material {
    
    private String materialnumber;
    private String material;
    private String quality;
    private double quantity;

    public Material(String number,String material,String quality,double qty){
        this.materialnumber=number;
        this.material=material;
        this.quality=quality;
        this.quantity=qty;
    }
    
    public String getMaterialnumber() {
        return materialnumber;
    }

    /**
     * @param materialnumber the materialnumber to set
     */
    public void setMaterialnumber(String materialnumber) {
        this.materialnumber = materialnumber;
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
     * @return the quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
