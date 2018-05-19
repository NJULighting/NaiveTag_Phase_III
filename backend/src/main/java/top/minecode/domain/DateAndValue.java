package top.minecode.domain;

import java.util.Date;

/**
 * Created on 2018/5/19.
 * Description:
 *
 * @author iznauy
 */
public class DateAndValue {

    private Date date;

    private double value;

    public DateAndValue(Date date, double value) {
        this.date = date;
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }

    public void addValue(double append) {
        value += append;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
