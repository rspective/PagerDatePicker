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

package pl.rspective.pagerdatepicker.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

import java.text.ParseException;
import java.util.Date;

import pl.rspective.pagerdatepicker.PagerDatePickerDateFormat;
import pl.rspective.pagerdatepicker.R;
import pl.rspective.pagerdatepicker.adapter.AbsDateAdapter;
import pl.rspective.pagerdatepicker.adapter.DefaultDateAdapter;
import pl.rspective.pagerdatepicker.model.DateItem;

public class DateRecyclerView extends RecyclerView implements ViewPager.OnPageChangeListener, DefaultDateAdapter.DateItemListener {

    public static interface DatePickerListener {
        void onDatePickerItemClick(DateItem dateItem, int position);

        void onDatePickerPageSelected(int position);

        void onDatePickerPageStateChanged(int state);

        void onDatePickerPageScrolled(int position, float positionOffset, int positionOffsetPixels);
    }

    private static final String TAG = "DateRecyclerView";

    private AbsDateAdapter defaultDateAdapter;
    private DatePickerListener datePickerListener;
    private ViewPager pager;

    public DateRecyclerView(Context context) {
        this(context, null);
    }

    public DateRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DateRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initWidget(context, attrs);
    }

    private void initWidget(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DateRecyclerViewWidget);

        setDates(typedArray);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        setLayoutManager(layoutManager);

        if(defaultDateAdapter != null) {
            setAdapter(defaultDateAdapter);
        }
    }

    private void setDates(TypedArray typedArray) {
        String dateStart = typedArray.getString(R.styleable.DateRecyclerViewWidget_date_start);
        String dateEnd = typedArray.getString(R.styleable.DateRecyclerViewWidget_date_end);

        String defaultDaySelection = typedArray.getString(R.styleable.DateRecyclerViewWidget_default_day_selection);
        Date defaultDate = null;

        if(dateStart == null || dateEnd == null) {
            return;
        }

        try {
            if (defaultDaySelection != null && !defaultDaySelection.isEmpty()) {
                defaultDate = PagerDatePickerDateFormat.DATE_PICKER_DD_MM_YYYY_FORMAT.parse(defaultDaySelection);
            }
        } catch (ParseException e) {
            Log.w(TAG, "Problem to parse default date selection", e);
        }

        try {
            Date start = PagerDatePickerDateFormat.DATE_PICKER_DD_MM_YYYY_FORMAT.parse(dateStart);
            Date end = PagerDatePickerDateFormat.DATE_PICKER_DD_MM_YYYY_FORMAT.parse(dateEnd);
            defaultDateAdapter = new DefaultDateAdapter(start, end, defaultDate);
        } catch (ParseException e) {
            Log.e(TAG, "The start/end date is incorrect", e);
        }
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        if(!(layout instanceof LinearLayoutManager)) {
            throw new IllegalArgumentException("For now DateRecyclerView supports only LinearLayoutManager");
        }

        LinearLayoutManager linearLayout = (LinearLayoutManager) layout;
        if(linearLayout.getOrientation() != LinearLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("For now DateRecyclerView supports only horizontal scrolling");
        }

        super.setLayoutManager(layout);
    }

    @Override
    public Adapter getAdapter() {
        return defaultDateAdapter;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if(!(adapter instanceof AbsDateAdapter)) {
            throw new IllegalArgumentException("Your adapter has to be a DateAdapter type");
        }

        defaultDateAdapter = (AbsDateAdapter) adapter;
        defaultDateAdapter.setOnDateItemClickClistener(this);
        super.setAdapter(defaultDateAdapter);

        scrollToPosition(defaultDateAdapter.getCurrentPosition());
    }

    public void setDatePickerListener(DatePickerListener datePickerListener) {
        this.datePickerListener = datePickerListener;
    }

    public void setPager(ViewPager pager) {
        this.pager = pager;
        this.pager.setCurrentItem(defaultDateAdapter.getCurrentPosition(), false);

        this.pager.setOnPageChangeListener(this);

    }

    public @NonNull
    AbsDateAdapter getDateAdapter() {
        return defaultDateAdapter;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(datePickerListener != null) {
            datePickerListener.onDatePickerPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        smoothScrollToPosition(position);
        getDateAdapter().setSelectedDate(position);

        if(datePickerListener != null) {
            datePickerListener.onDatePickerPageSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if(datePickerListener != null) {
            datePickerListener.onDatePickerPageStateChanged(state);
        }
    }

    @Override
    public void onDateItemClick(DateItem dateItem, int position) {
        pager.setCurrentItem(position, true);

        if(datePickerListener != null) {
            datePickerListener.onDatePickerItemClick(dateItem, position);
        }
    }

}
