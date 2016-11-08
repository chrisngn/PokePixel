package com.phongnomenal.pokepixel;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Launch the game menu
        FragmentLauncher.launchFragment(this, new GameMenuFragment(), R.id.fragment_container, false);
    }

    public void fragmentLaunched(Fragment fragment, boolean addToBackStack) {
        // This function is responsible for adding a new fragment to the main fragment container,
        // adding an animation to the transaction, and adding the new fragment to the backstack.
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                R.anim.slide_in_right,
                R.anim.slide_out_left);
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}
