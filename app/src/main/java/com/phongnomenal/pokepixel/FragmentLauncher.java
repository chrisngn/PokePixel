package com.phongnomenal.pokepixel;

import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class FragmentLauncher {
    private static long lastClickTime = 0;

    public static void launchFragment(AppCompatActivity activity, Fragment fragmentToLaunch, int containerViewId, boolean addToBackStack) {
        // Utility for launching fragments
        // Pass in the activity to launch the fragment in, the fragment to launch, the view to launch it into and whether to add it to back stack
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                R.anim.slide_in_right,
                R.anim.slide_out_left);
        fragmentTransaction.replace(containerViewId, fragmentToLaunch);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    public static void launchFragment(AppCompatActivity activity, Fragment fragmentToLaunch, int containerViewId, boolean addToBackStack, long ClickTime) {
        // Overloads. Adds a check to prevent multiple fragments from launching at the same time.
        // This is for preventing multiple segment detail views from launching simultaneously.
        if (SystemClock.elapsedRealtime() - lastClickTime < 500) {
            return;
        }
        lastClickTime = ClickTime;
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
        fragmentTransaction.replace(containerViewId, fragmentToLaunch);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}
