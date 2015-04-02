package pl.rspective.pagerdatepicker.sample.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.rspective.pagerdatepicker.sample.R;

public class DatePickerFragment extends Fragment implements View.OnClickListener {

    public static DatePickerFragment newInstance() {
        return new DatePickerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_date_picker, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_default_date_picker).setOnClickListener(this);
        view.findViewById(R.id.btn_custom_date_picker).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.btn_default_date_picker:
                transaction.replace(R.id.fl_main_container, DatePickerDefaultFragment.newInstance());
                break;
            case R.id.btn_custom_date_picker:
                transaction.replace(R.id.fl_main_container, DatePickerCustomFragment.newInstance());
                break;
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
