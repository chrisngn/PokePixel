package com.phongnomenal.pokepixel.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by chrisngn on 11/25/16.
 */

public class TileView extends ImageView {

    public TileView(Context context) {
        super(context);
    }

    public TileView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TileView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // This will make the height equivalent to the width
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}