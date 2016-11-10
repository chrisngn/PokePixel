package com.phongnomenal.pokepixel.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.phongnomenal.pokepixel.R;
import com.phongnomenal.pokepixel.common.FragmentLauncher;
import com.phongnomenal.pokepixel.fragments.MenuFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Launch the game menu
        FragmentLauncher.launchFragment(this, MenuFragment.newInstance(), R.id.fragment_container, false);
    }
}
