package id.go.sukabumikota.clink.SupportLayout;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import id.go.sukabumikota.clink.R;

public class StaggeredItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpanCount;
    private int mMargin;

    public StaggeredItemDecoration(Context context, int spanCount) {
        mSpanCount = spanCount;
        mMargin = (int) context.getResources().getDimension(R.dimen.list_margin_stagged);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.right = mMargin;
        outRect.bottom = mMargin;
        int spanIndex = ((StaggeredGridLayoutManager.LayoutParams)view.getLayoutParams()).getSpanIndex();
        if (parent.getChildAdapterPosition(view)<mSpanCount)
            outRect.top = mMargin;

        if ((spanIndex+1)%mSpanCount==1)
            outRect.left = mMargin;


    }

}
