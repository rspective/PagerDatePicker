package pl.rspective.pagerdatepicker.model;

import java.util.Date;

public class DateItem {
    private Date date;

    public DateItem(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        DateItem item = (DateItem) o;
        return this.date.getTime() == item.getDate().getTime();
    }

    @Override
    public int hashCode() {
        return Long.valueOf(date.getTime()).hashCode();
    }
}
