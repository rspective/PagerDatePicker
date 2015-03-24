package pl.rspective.pagerdatepicker.sample.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import pl.rspective.pagerdatepicker.sample.R;

public class SimplePageFragment extends Fragment {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("MMMM dd, yyyy");

    private static final String DATE_PICKER_DATE_KEY = "date_picker_date_key";
    private static final String DATE_PICKER_POSITION_KEY = "date_picker_position_key";

    private TextView tvDate;
    private TextView tvPosition;

    private int position;
    private long date;

    public static SimplePageFragment newInstance(int position, long date) {
        Bundle bundle = new Bundle();
        bundle.putInt(DATE_PICKER_POSITION_KEY, position);
        bundle.putLong(DATE_PICKER_DATE_KEY, date);

        SimplePageFragment simplePageFragment = new SimplePageFragment();
        simplePageFragment.setArguments(bundle);

        return simplePageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = getArguments().getInt(DATE_PICKER_POSITION_KEY, -1);
        date = getArguments().getLong(DATE_PICKER_DATE_KEY, -1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page_simple, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvDate = (TextView) view.findViewById(R.id.tv_date_label);
        tvPosition = (TextView) view.findViewById(R.id.tv_position_label);

        tvDate.setText(SIMPLE_DATE_FORMAT.format(date));
        tvPosition.setText(String.valueOf(position));
    }
}
