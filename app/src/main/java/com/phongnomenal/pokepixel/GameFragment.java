package com.phongnomenal.pokepixel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.vision.Frame;

public class GameFragment extends Fragment {

    FrameLayout mGameFragment;
    FrameLayout mGameContainer;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mGameFragment = (FrameLayout) getActivity().findViewById(R.id.fragment_game);
        mGameContainer = (FrameLayout) mGameFragment.findViewById(R.id.container_gamer);

        int n = (int) Math.sqrt(mGameContainer.getChildCount());
        for (int i = 0; i < n*n; i++) {
            ((FrameLayout) mGameFragment.getChildAt(i)).setrow;
        }
        return inflater.inflate(R.layout.fragment_game, container, false);
    }
}
