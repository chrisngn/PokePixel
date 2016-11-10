package com.phongnomenal.pokepixel.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phongnomenal.pokepixel.R;
import com.phongnomenal.pokepixel.activities.MainActivity;
import com.phongnomenal.pokepixel.common.FragmentLauncher;

public class ScoreFragment extends Fragment {
    private static final String key = "score";
    private int mScore;

    private View mFragment;
    private TextView mFinalScore;

    private Handler handler;
    private Runnable runnable;

    public ScoreFragment() {
        // Required empty public constructor
    }

    public static ScoreFragment newInstance(int score) {
        ScoreFragment fragment = new ScoreFragment();
        Bundle args = new Bundle();
        args.putInt(key, score);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mScore = getArguments().getInt(key);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return mFragment = inflater.inflate(R.layout.fragment_score, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFinalScore = (TextView) mFragment.findViewById(R.id.final_score);
        mFinalScore.setText(String.valueOf(mScore));

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                // Load the menu fragment
                FragmentLauncher.launchFragment((MainActivity) getActivity(),
                        MenuFragment.newInstance(), R.id.fragment_container, false);
            }
        };

        // Show this screen for 2 seconds before loading the menu fragment
        handler.postDelayed(runnable, 2000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
