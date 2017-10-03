package com.phongnomenal.pokepixel.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.phongnomenal.pokepixel.common.FragmentLauncher;
import com.phongnomenal.pokepixel.R;
import com.phongnomenal.pokepixel.activities.MainActivity;
import com.phongnomenal.pokepixel.enums.Difficulty;

public class MenuFragment extends Fragment {
    private MainActivity mainActivity;
    private Button blind, impaired, twentyTwenty;
    private TextView selectBlindness;

    public MenuFragment() {
        // Required empty public constructor
    }

    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        mainActivity = (MainActivity) getActivity();

        selectBlindness = (TextView) view.findViewById(R.id.select_blindness);

        // Make text blink
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        //You can manage the time of the blink with this parameter
        //Higher = slower blink rate
        anim.setDuration(400);
        anim.setRepeatMode(Animation.RESTART);
        anim.setRepeatCount(15);
        selectBlindness.startAnimation(anim);

        blind = (Button) view.findViewById(R.id.beginner);
        impaired = (Button) view.findViewById(R.id.intermediate);
        twentyTwenty = (Button) view.findViewById(R.id.advanced);

        setUpButton(blind, Difficulty.BLIND);
        setUpButton(impaired, Difficulty.IMPAIRED);
        setUpButton(twentyTwenty, Difficulty.TWENTY_TWENTY);

        return view;
    }

    private void setUpButton(final Button b, final Difficulty diff) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.setEnabled(false);

                Bundle args = new Bundle();
                args.putSerializable("difficulty", diff);

                GameFragment frag = GameFragment.newInstance();
                frag.setArguments(args);

                FragmentLauncher.launchFragment(mainActivity, frag, R.id.fragment_container, false);

                b.setEnabled(true);
            }
        });
    }
}
