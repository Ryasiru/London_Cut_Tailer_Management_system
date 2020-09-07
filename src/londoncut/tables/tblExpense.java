
package londoncut.tables;


public class tblExpense {
    private String date;
    private String desc;
    private double expense;

    public tblExpense(String date,String desc,double exp){
        this.date=date;
        this.desc=desc;
        this.expense=exp;
    }
    
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the expense
     */
    public double getExpense() {
        return expense;
    }

    /**
     * @param expense the expense to set
     */
    public void setExpense(double expense) {
        this.expense = expense;
    }
}
