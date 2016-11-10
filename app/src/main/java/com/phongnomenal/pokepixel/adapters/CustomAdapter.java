package com.phongnomenal.pokepixel.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.phongnomenal.pokepixel.R;
import com.phongnomenal.pokepixel.fragments.GameFragment;
import com.phongnomenal.pokepixel.views.TileView;

import java.util.Random;

/**
 * Created by chrisngn on 11/25/16.
 */

public class CustomAdapter extends BaseAdapter {
    private static final double MIN_TINT = 0.1;
    private static final double MAX_TINT = 0.2;
    private static final double MIN_SHADE = 0.8;
    private static final double MAX_SHADE = 0.9;

    private static Random random;
    private static LayoutInflater inflater;

    private Context mContext;
    private GameFragment fragment;

    private int mSize;
    private int mColor, mHue;
    private int mRed, mGreen, mBlue;

    public CustomAdapter(Context context, int n) {
        random = new Random();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
        mSize = n;
        mColor = getColor();
        mHue = getHue();
        Log.d("mColor", String.valueOf(mColor));
        Log.d("mHue", String.valueOf(mHue));
    }

    public void setFragment(GameFragment f) {
        fragment = f;
    }

    public int getCount() {
        return mSize;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // Create a new tile for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        // Both ways are valid
        //TileView tile = (TileView) inflater.inflate(R.layout.tile, null);
        TileView tile = new TileView(mContext);

        // Stylistic preferences
        int color;
        Drawable d = mContext.getResources().getDrawable(R.drawable.rounded_corners);
        if (fragment.randomAnswers.contains(position)) {
            color = mHue;
            d.setColorFilter(new PorterDuffColorFilter(mHue, PorterDuff.Mode.MULTIPLY));
        } else {
            color = mColor;
            d.setColorFilter(new PorterDuffColorFilter(mColor, PorterDuff.Mode.MULTIPLY));
        }

        tile.setBackgroundColor(color);
        //tile.setBackground(d);

        return tile;
    }

    /**
     * Generate a color in the range of [-16777216, -1 (0 if includes alpha)]
     *
     * @return a color identified by an int
     */
    private int getColor() {
        int alpha = 240 + random.nextInt(16);
        mRed = random.nextInt(256);
        mGreen = random.nextInt(256);
        mBlue = random.nextInt(256);
        return Color.rgb(/*alpha, */mRed, mGreen, mBlue);
    }

    /**
     * Generate a shade of the color at least 20 but not more than 30 points away
     *
     * @return a shade
     */
    private int getHue() {
        double factor = getHueFactor();
        Log.d("factor", String.valueOf(factor));

        if (0.05 <= factor && factor <= 0.2) {
            int tint = getTint(factor);
            Log.d("tint", String.valueOf(tint));
            return tint;
        } else {
            int shade = getShade(factor);
            Log.d("shade", String.valueOf(shade));
            return shade;
        }
    }

    /**
     * Produce a lighter hue of the color
     *
     * @param factor  how light the tint will be
     * @return  the tint
     */
    private int getTint(double factor) {
        int rt = (int) (mRed + (255 - mRed) * factor);
        int gt = (int) (mGreen + (255 - mGreen) * factor);
        int bt = (int) (mBlue + (255 - mBlue) * factor);
        int tint = Color.rgb(rt, gt, bt);
        return tint;
    }

    /**
     * Produce a darker shade of the color
     *
     * @param factor  how dark the shade will be
     * @return  the shade
     */
    private int getShade(double factor) {
        int rs = (int) (mRed * factor);
        int gs = (int) (mGreen * factor);
        int bs = (int) (mBlue * factor);
        int shade = Color.rgb(rs, gs, bs);
        return shade;
    }

    /**
     * Return the factor that will produce a tint or shade that is not too different from the original
     *
     * @return the factor
     */
    private double getHueFactor() {
        double f = random.nextDouble();
        while (true) {
            if ((MIN_TINT <= f && f <= MAX_TINT) || (MIN_SHADE <= f && f <= MAX_SHADE)) {
                return f;
            }
            f = random.nextDouble();
        }
    }
}