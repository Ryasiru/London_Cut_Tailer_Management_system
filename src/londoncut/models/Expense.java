
package londoncut.models;

import javax.persistence.Entity;
import javax.persistence.Table;


public class Expense {
    private String date;
    private String description;
    private double expense;

    public Expense(String date,String desc,double expense){
        this.date=date;
        this.description=desc;
        this.expense=expense;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
