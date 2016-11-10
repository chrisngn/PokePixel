package com.phongnomenal.pokepixel.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.phongnomenal.pokepixel.common.FragmentLauncher;
import com.phongnomenal.pokepixel.R;
import com.phongnomenal.pokepixel.activities.MainActivity;
import com.phongnomenal.pokepixel.enums.Difficulty;

public class MenuFragment extends Fragment {
    public static Difficulty difficulty;
    private MainActivity mainActivity;
    private Button beginner, intermediate, advanced, master;

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

        beginner = (Button) view.findViewById(R.id.beginner);
        intermediate = (Button) view.findViewById(R.id.intermediate);
        advanced = (Button) view.findViewById(R.id.advanced);
        //master = (Button) view.findViewById(R.id.master);

        setUpButton(beginner, Difficulty.BEGINNER);
        setUpButton(intermediate, Difficulty.INTERMEDIATE);
        setUpButton(advanced, Difficulty.ADVANCED);
        //setUpButton(master, Difficulty.MASTER);

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
