package pl.rspective.pagerdatepicker.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class DateRecyclerView extends RecyclerView {

    public DateRecyclerView(Context context) {
        this(context, null);
    }

    public DateRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DateRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
