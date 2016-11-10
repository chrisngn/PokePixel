package com.phongnomenal.pokepixel.common;

import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.phongnomenal.pokepixel.R;

public class FragmentLauncher {
    private static long lastClickTime = 0;

    public static void launchFragment(AppCompatActivity activity, Fragment fragment,
                                      int containerViewId, boolean addToBackStack) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                R.anim.slide_in_right, R.anim.slide_out_left);
        ft.replace(containerViewId, fragment);
        if (addToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    // Prevents multiple fragments from launching simultaneously
    public static void launchFragment(AppCompatActivity activity, Fragment fragment,
                                      int containerViewId, boolean addToBackStack, long time) {
        if (SystemClock.elapsedRealtime() - lastClickTime < 500) {
            lastClickTime = time;
            return;
        }
        lastClickTime = time;
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                R.anim.slide_in_right, R.anim.slide_out_left);
        ft.replace(containerViewId, fragment);
        if (addToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }
}
