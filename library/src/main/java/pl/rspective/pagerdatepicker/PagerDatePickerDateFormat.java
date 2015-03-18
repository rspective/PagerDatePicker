package pl.rspective.pagerdatepicker;

import java.text.SimpleDateFormat;

public interface PagerDatePickerDateFormat {

    SimpleDateFormat DATE_PICKER_DAY_FORMAT = new SimpleDateFormat("dd");
    SimpleDateFormat DATE_PICKER_MONTH_NAME_FORMAT = new SimpleDateFormat("MMMM");
    SimpleDateFormat DATE_PICKER_DAY_NAME_FORMAT = new SimpleDateFormat("EEEE");

}
