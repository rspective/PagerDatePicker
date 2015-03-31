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

        dateList.addItemDecoration(new RecyclerViewInsetDecoration(this, R.dimen.date_card_insets));

        Date start = null;
        Date end = null;

        try {
            start = PagerDatePickerDateFormat.DATE_PICKER_DD_MM_YYYY_FORMAT.parse("02-12-2000");
            end = PagerDatePickerDateFormat.DATE_PICKER_DD_MM_YYYY_FORMAT.parse("02-12-2020");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dateList.setAdapter(new DateAdapter(start, end));

        DatePagerFragmentAdapter fragmentAdapter = new DatePagerFragmentAdapter(getSupportFragmentManager(), dateList.getDateAdapter()) {
            @Override
            protected Fragment getFragment(int position, long date) {
                return SimplePageFragment.newInstance(position, date);
            }
        };

        pager.setAdapter(fragmentAdapter);
        dateList.setPager(pager);

        dateList.setDatePickerListener(new DateRecyclerView.DatePickerListener() {
            @Override
            public void onDatePickerItemClick(DateItem dateItem, int position) {
                Toast.makeText(MainActivity.this, "Clicked: " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDatePickerPageSelected(int position) {

            }

            @Override
            public void onDatePickerPageStateChanged(int state) {

            }

            @Override
            public void onDatePickerPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
        });

    }

}
