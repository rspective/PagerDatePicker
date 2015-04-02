package pl.rspective.pagerdatepicker.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import pl.rspective.pagerdatepicker.sample.ui.fragments.DatePickerFragment;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_main_container, DatePickerFragment.newInstance())
                .commit();
    }

}
