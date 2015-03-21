package pl.rspective.pagerdatepicker.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
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

    @Override
    public void setLayoutManager(LayoutManager layout) {
        if(!(layout instanceof LinearLayoutManager)) {
            throw new IllegalArgumentException("For now DateRecyclerView supports only LinearLayoutManager");
        }

        LinearLayoutManager linearLayout = (LinearLayoutManager) layout;
        if(linearLayout.getOrientation() != LinearLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("For now DateRecyclerView supports only horizontal scrolling");
        }

        super.setLayoutManager(layout);
    }
}
