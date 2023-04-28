package londoncut.models;

public class TailorWork {

    private String suitnumber;
    private String tailotid;
    private int qty;
    private double price;

    public String getSuitnumber() {
        return suitnumber;
    }

    public void setSuitnumber(String suitnumber) {
        this.suitnumber = suitnumber;
    }

    public String getTailotid() {
        return tailotid;
    }

    public void setTailotid(String tailotid) {
        this.tailotid = tailotid;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
