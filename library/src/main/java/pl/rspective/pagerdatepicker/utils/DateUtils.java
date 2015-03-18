package pl.rspective.pagerdatepicker.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pl.rspective.pagerdatepicker.model.DateItem;

public final class DateUtils {

    public static List<DateItem> getDaysBetweenStartAndEnd(Date startDate, Date endDate) {
        List<DateItem> dates = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        while (calendar.getTime().before(endDate)) {
            Date result = calendar.getTime();
            dates.add(new DateItem(result));
            calendar.add(Calendar.DATE, 1);
        }

        return dates;
    }


}
