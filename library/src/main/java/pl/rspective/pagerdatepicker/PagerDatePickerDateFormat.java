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

package pl.rspective.pagerdatepicker;

import java.text.SimpleDateFormat;

public interface PagerDatePickerDateFormat {

    SimpleDateFormat DATE_PICKER_DAY_FORMAT = new SimpleDateFormat("dd");
    SimpleDateFormat DATE_PICKER_MONTH_NAME_FORMAT = new SimpleDateFormat("MMMM");
    SimpleDateFormat DATE_PICKER_DAY_NAME_FORMAT = new SimpleDateFormat("EEEE");

    SimpleDateFormat DATE_PICKER_DD_MM_YYYY_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
}
