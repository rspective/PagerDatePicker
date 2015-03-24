/*
 * Copyright 2015 RSPECTIVE P RYCHLIK SPÓŁKA JAWNA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
