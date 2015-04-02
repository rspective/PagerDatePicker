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

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import pl.rspective.pagerdatepicker.model.DateItem;

public abstract class DatePagerFragmentAdapter<T extends Fragment> extends FragmentStatePagerAdapter {

    private AbsDateAdapter defaultDateAdapter;

    public DatePagerFragmentAdapter(FragmentManager fm, AbsDateAdapter defaultDateAdapter) {
        super(fm);
        this.defaultDateAdapter = defaultDateAdapter;
    }

    @Override
    public Fragment getItem(int position) {
        DateItem dateItem = defaultDateAdapter.getItem(position);
        return getFragment(position, dateItem.getDate().getTime());
    }

    @Override
    public int getCount() {
        return defaultDateAdapter.getItemCount();
    }

    protected abstract T getFragment(int position, long date);

}
