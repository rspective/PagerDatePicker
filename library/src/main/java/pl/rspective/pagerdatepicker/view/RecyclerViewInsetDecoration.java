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
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import pl.rspective.pagerdatepicker.R;

public class RecyclerViewInsetDecoration extends RecyclerView.ItemDecoration {

    private int insets;

    public RecyclerViewInsetDecoration(Context context) {
        insets = context.getResources().getDimensionPixelSize(R.dimen.date_card_insets_default);
    }

    public RecyclerViewInsetDecoration(Context context, @DimenRes int insetsResId) {
        insets = context.getResources().getDimensionPixelSize(insetsResId);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(insets, insets, insets, insets);
    }
}