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

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Date;

public abstract class AbsDateItemHolder<T extends AbsDateAdapter> extends RecyclerView.ViewHolder implements View.OnClickListener {

    private T dateAdapter;

    public AbsDateItemHolder(View itemView, T dateAdapter) {
        super(itemView);

        this.dateAdapter = dateAdapter;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        dateAdapter.onDateItemHolderClick(this);
    }

    public void updateDateItemView(boolean isSelected) {
        changeTextColor(isSelected);
        changeDateIndicatorColor(isSelected);

        if(dateAdapter.hasCurrentViewAnimation()) {
            if (isSelected) {
                getCurrentViewToAnimate().startAnimation(dateAdapter.getCurrentViewAnimation());
            } else {
                getCurrentViewToAnimate().clearAnimation();
            }
        }
    }

    public abstract void setDay(Date date);

    public abstract void setMonthName(Date date);

    public abstract  void setDayName(Date date);

    protected abstract void changeTextColor(boolean isSelected);

    protected abstract void changeDateIndicatorColor(boolean isSelected);

    protected abstract View getCurrentViewToAnimate();

}
