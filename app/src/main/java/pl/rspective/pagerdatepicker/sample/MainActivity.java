package pl.rspective.pagerdatepicker.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;

import pl.rspective.pagerdatepicker.PagerDatePickerDateFormat;
import pl.rspective.pagerdatepicker.adapter.DateAdapter;
import pl.rspective.pagerdatepicker.adapter.DatePagerFragmentAdapter;
import pl.rspective.pagerdatepicker.model.DateItem;
import pl.rspective.pagerdatepicker.sample.ui.fragments.SimplePageFragment;
import pl.rspective.pagerdatepicker.view.DateRecyclerView;
import pl.rspective.pagerdatepicker.view.RecyclerViewInsetDecoration;


public class MainActivity extends ActionBarActivity {

    private DateRecyclerView dateList;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.pager);
        dateList = (DateRecyclerView) findViewById(R.id.date_list);

        dateList.addItemDecoration(new RecyclerViewInsetDecoration(this));

        Date start = null;
        Date end = null;

        try {
            start = PagerDatePickerDateFormat.DATE_PICKER_DD_MM_YYYY_FORMAT.parse("02-12-2000");
            end = PagerDatePickerDateFormat.DATE_PICKER_DD_MM_YYYY_FORMAT.parse("02-12-2020");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dateList.setAdapter(new DateAdapter(start, end));
        dateList.setPager(pager);

        dateList.setDateRecyclerViewListener(new DateRecyclerView.DateRecyclerViewListener() {
            @Override
            public void onDateItemClick(DateItem dateItem, int position) {
                Toast.makeText(MainActivity.this, "Clicked: " + position, Toast.LENGTH_SHORT).show();
                pager.setCurrentItem(position, true);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                dateList.smoothScrollToPosition(position);
                dateList.getDateAdapter().setSelectedDate(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        DatePagerFragmentAdapter fragmentAdapter = new DatePagerFragmentAdapter(getSupportFragmentManager(), dateList.getDateAdapter()) {
            @Override
            protected Fragment getFragment(int position, long date) {
                return SimplePageFragment.newInstance(position, date);
            }
        };

        pager.setAdapter(fragmentAdapter);

    }

}
