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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pl.rspective.pagerdatepicker.PagerDatePickerDateFormat;
import pl.rspective.pagerdatepicker.R;
import pl.rspective.pagerdatepicker.model.DateItem;
import pl.rspective.pagerdatepicker.utils.DateUtils;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.DateItemHolder> {

    public static interface DateItemListener {
        void onDateItemClick(DateItem dateItem, int position);
    }

    private List<DateItem> dateItems;
    private DateItemListener onDateItemListener;

    private long selectedDate = -1;
    private DateItemHolder selectedDateView = null;

    public DateAdapter(Date start, Date end) {
        this(start, end, null);
    }

    public DateAdapter(Date start, Date end, Date defaultDate) {
        if (start.getTime() > end.getTime()) {
            throw new IllegalArgumentException("Wrong dates : StartDate > EndDate");
        }

        this.dateItems = new ArrayList<>();
        this.dateItems.addAll(DateUtils.getDaysBetweenStartAndEnd(start, end));

        if(defaultDate != null && ((defaultDate.getTime() <= end.getTime() && defaultDate.getTime() >= start.getTime() ))) {
            setSelectedDate(getPosition(defaultDate.getTime()));
        }
    }

    public void setOnDateItemClickClistener(DateItemListener onDateItemListener) {
        this.onDateItemListener = onDateItemListener;
    }

    @Override
    public DateItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(R.layout.item_view_date, viewGroup, false);

        return new DateItemHolder(root, this);
    }

    @Override
    public void onBindViewHolder(DateItemHolder dateItemHolder, int i) {
        DateItem dateItem = dateItems.get(i);

        dateItemHolder.setDay(dateItem.getDate());
        dateItemHolder.setMonthName(dateItem.getDate());
        dateItemHolder.setDayName(dateItem.getDate());

        dateItemHolder.itemView.setSelected(true);

        if (selectedDate == dateItem.getDate().getTime()) {
            dateItemHolder.changeDateIndicatorColor(true);
            dateItemHolder.changeTextColor(true);
            selectedDateView = dateItemHolder;
        } else {
            dateItemHolder.changeDateIndicatorColor(false);
            dateItemHolder.changeTextColor(false);
        }
    }

    @Override
    public int getItemCount() {
        return dateItems != null ? dateItems.size() : 0;
    }

    public DateItem getItem(int position) {
        return dateItems.get(position);
    }

    public void setSelectedDate(int position) {
        notifyItemChanged(getPosition(selectedDate));
        selectedDate = dateItems.get(position).getDate().getTime();
        notifyItemChanged(position);
    }

    public int getPosition(long dateInMiliseconds) {
        DateItem dateItem = new DateItem(new Date(dateInMiliseconds));
        return dateItems.indexOf(dateItem);
    }

    public int getCurrentPosition() {
        return getPosition(selectedDate);
    }

    private void onDateItemHolderClick(DateItemHolder itemHolder) {
        if (onDateItemListener != null) {
            onDateItemListener.onDateItemClick(getItem(itemHolder.getPosition()), itemHolder.getPosition());
        }

        if (selectedDate != -1 && selectedDateView != null) {
            selectedDateView.changeDateIndicatorColor(false);
            selectedDateView.changeTextColor(false);
        }

        selectedDateView = itemHolder;
        selectedDate = dateItems.get(itemHolder.getPosition()).getDate().getTime();

        updateSelectedDateCard();

    }

    private void updateSelectedDateCard() {
        selectedDateView.changeDateIndicatorColor(true);
        selectedDateView.changeTextColor(true);
    }

    static class DateItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        DateAdapter adapter;

        TextView tvDay;
        TextView tvMonth;
        TextView tvDayName;
        View viewDateIndicator;
        RelativeLayout rlDateItem;

        Resources resources;

        public DateItemHolder(View itemView, DateAdapter adapter) {
            super(itemView);
            itemView.setOnClickListener(this);

            this.adapter = adapter;
            this.resources = itemView.getResources();

            tvDay = (TextView) itemView.findViewById(R.id.tv_date_picker_day);
            tvMonth = (TextView) itemView.findViewById(R.id.tv_date_picker_month_name);
            tvDayName = (TextView) itemView.findViewById(R.id.tv_date_picker_day_name);
            rlDateItem = (RelativeLayout) itemView.findViewById(R.id.rl_date_picker_item);
            viewDateIndicator = itemView.findViewById(R.id.view_date_indicator);
        }

        public void changeTextColor(boolean isSelected) {
            if (isSelected) {
                tvDay.setTextColor(resources.getColor(R.color.date_item_selected_indicator));
            } else {
                tvDay.setTextColor(resources.getColor(R.color.date_item_unselected_indicator));
            }
        }

        public void setDay(Date date) {
            tvDay.setText(PagerDatePickerDateFormat.DATE_PICKER_DAY_FORMAT.format(date));
        }

        public void setMonthName(Date date) {
            tvMonth.setText(PagerDatePickerDateFormat.DATE_PICKER_MONTH_NAME_FORMAT.format(date));
        }

        public void setDayName(Date date) {
            tvDayName.setText(PagerDatePickerDateFormat.DATE_PICKER_DAY_NAME_FORMAT.format(date));
        }

        public void changeDateIndicatorColor(boolean isSelected) {
            if (isSelected) {
                viewDateIndicator.setBackgroundResource(R.color.date_item_selected_indicator);
            } else {
                viewDateIndicator.setBackgroundResource(R.color.date_item_unselected_indicator);
            }
        }

        @Override
        public void onClick(View view) {
            adapter.onDateItemHolderClick(this);
        }
    }

}
