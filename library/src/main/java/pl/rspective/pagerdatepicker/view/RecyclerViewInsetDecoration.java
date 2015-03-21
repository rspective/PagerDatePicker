package pl.rspective.pagerdatepicker.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import pl.rspective.pagerdatepicker.R;

public class RecyclerViewInsetDecoration extends RecyclerView.ItemDecoration {

    private int mInsets;

    public RecyclerViewInsetDecoration(Context context) {
        mInsets = context.getResources().getDimensionPixelSize(R.dimen.date_card_insets);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(mInsets, mInsets, mInsets, mInsets);
    }
}