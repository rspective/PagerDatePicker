package pl.rspective.pagerdatepicker.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import pl.rspective.pagerdatepicker.model.DateItem;

public abstract class DatePagerFragmentAdapter<T extends Fragment> extends FragmentStatePagerAdapter {

    private DateAdapter dateAdapter;

    public DatePagerFragmentAdapter(FragmentManager fm, DateAdapter dateAdapter) {
        super(fm);
        this.dateAdapter = dateAdapter;
    }

    @Override
    public Fragment getItem(int position) {
        DateItem dateItem = dateAdapter.getItem(position);
        return getFragment(position, dateItem.getDate().getTime());
    }

    @Override
    public int getCount() {
        return dateAdapter.getItemCount();
    }

    protected abstract T getFragment(int position, long date);

}
