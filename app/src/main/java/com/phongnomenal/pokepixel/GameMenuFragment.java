package com.phongnomenal.pokepixel;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class GameMenuFragment extends Fragment {

    private MainActivity mainActivity;
    private Button mStartGameButton;

    public GameMenuFragment() {
        // Required empty public constructor
    }

    public static GameMenuFragment newInstance() {
        GameMenuFragment fragment = new GameMenuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_menu, container, false);

        mainActivity = (MainActivity) getActivity();
        mStartGameButton = (Button) view.findViewById(R.id.start_game_button);
        mStartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.fragmentLaunched(new GameMenuFragment(), false);
                FragmentLauncher.launchFragment(mainActivity, new GameFragment(), R.id.fragment_container, false);
            }
        });

        return view;
    }
}
