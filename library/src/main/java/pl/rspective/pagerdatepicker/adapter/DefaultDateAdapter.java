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

package pl.rspective.pagerdatepicker.adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Date;

import pl.rspective.pagerdatepicker.PagerDatePickerDateFormat;
import pl.rspective.pagerdatepicker.R;
import pl.rspective.pagerdatepicker.model.DateItem;

public class DefaultDateAdapter extends AbsDateAdapter<DefaultDateAdapter.DateItemHolder> {

    public DefaultDateAdapter(Date start, Date end) {
        this(start, end, null);
    }

    public DefaultDateAdapter(Date start, Date end, Date defaultDate) {
        super(start, end, defaultDate);
    }

    @Override
    public DateItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(R.layout.item_view_default_date, viewGroup, false);

        return new DateItemHolder(root, this);
    }

    @Override
    public void onBindViewHolder(DateItemHolder dateItemHolder, int i) {
        DateItem dateItem = dateItems.get(i);

        dateItemHolder.setDay(dateItem.getDate());
        dateItemHolder.setMonthName(dateItem.getDate());
        dateItemHolder.setDayName(dateItem.getDate());

        dateItemHolder.itemView.setSelected(true);

        if (isDateSelected(dateItem)) {
            dateItemHolder.updateDateItemView(true);
            selectedDateView = dateItemHolder;
        } else {
            dateItemHolder.updateDateItemView(false);
        }
    }

    @Override
    public void onDateItemHolderClick(DateItemHolder itemHolder) {
        if (onDateItemListener != null) {
            onDateItemListener.onDateItemClick(getItem(itemHolder.getPosition()), itemHolder.getPosition());
        }

        if (selectedDate != -1 && selectedDateView != null) {
            selectedDateView.changeDateIndicatorColor(false);
            selectedDateView.changeTextColor(false);
        }

        selectedDateView = itemHolder;
        selectedDate = dateItems.get(itemHolder.getPosition()).getDate().getTime();

        selectedDateView.updateDateItemView(true);

    }

    static class DateItemHolder extends AbsDateItemHolder {

        TextView tvDay;
        TextView tvMonth;
        TextView tvDayName;
        View viewDateIndicator;
        RelativeLayout rlDateItem;

        Resources resources;

        public DateItemHolder(View itemView, DefaultDateAdapter adapter) {
            super(itemView, adapter);

            this.resources = itemView.getResources();

            tvDay = (TextView) itemView.findViewById(R.id.tv_date_picker_day);
            tvMonth = (TextView) itemView.findViewById(R.id.tv_date_picker_month_name);
            tvDayName = (TextView) itemView.findViewById(R.id.tv_date_picker_day_name);
            rlDateItem = (RelativeLayout) itemView.findViewById(R.id.rl_date_picker_item);
            viewDateIndicator = itemView.findViewById(R.id.view_date_indicator);
        }

        @Override
        protected void changeTextColor(boolean isSelected) {
            if (isSelected) {
                tvDay.setTextColor(resources.getColor(R.color.date_item_selected_indicator));
            } else {
                tvDay.setTextColor(resources.getColor(R.color.date_item_unselected_indicator));
            }
        }

        @Override
        public void setDay(Date date) {
            tvDay.setText(PagerDatePickerDateFormat.DATE_PICKER_DAY_FORMAT.format(date));
        }

        @Override
        public void setMonthName(Date date) {
            tvMonth.setText(PagerDatePickerDateFormat.DATE_PICKER_MONTH_NAME_FORMAT.format(date));
        }

        @Override
        public void setDayName(Date date) {
            tvDayName.setText(PagerDatePickerDateFormat.DATE_PICKER_DAY_NAME_FORMAT.format(date));
        }

        @Override
        protected void changeDateIndicatorColor(boolean isSelected) {
            if (isSelected) {
                viewDateIndicator.setBackgroundResource(R.color.date_item_selected_indicator);
            } else {
                viewDateIndicator.setBackgroundResource(R.color.date_item_unselected_indicator);
            }
        }

        @Override
        protected View getCurrentViewToAnimate() {
            return null;
        }

    }

}
