package com.phongnomenal.pokepixel.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.phongnomenal.pokepixel.R;
import com.phongnomenal.pokepixel.activities.MainActivity;
import com.phongnomenal.pokepixel.adapters.CustomAdapter;
import com.phongnomenal.pokepixel.common.FragmentLauncher;
import com.phongnomenal.pokepixel.enums.Difficulty;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameFragment extends Fragment {
    private static final int MAX_COL = 7;

    public static Difficulty difficulty;

    public static Set<Integer> randomAnswers;

    private View mFragment;
    private GridView mGameGrid;
    private TextView mTimer, mScore, mClicks;
    private Button mButton;

    private Handler handler;
    private Runnable runnable;

    private int mScoreCounter, mClickCounter;
    private int mCols = 2;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        difficulty = (Difficulty) args.get("difficulty");
    }

    public static GameFragment newInstance() {
        GameFragment fragment = new GameFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragment = inflater.inflate(R.layout.fragment_game, container, false);
        return mFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mScore = (TextView) mFragment.findViewById(R.id.score);
        mClicks = (TextView) mFragment.findViewById(R.id.clicks);
        mButton = (Button) mFragment.findViewById(R.id.quit);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopGame();
            }
        });
        initGame(mCols);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    private void initGame(int size) {
        setupGrid(size);
        switch(difficulty) {
            case BEGINNER:
                setupTimer(60);
                break;
            case INTERMEDIATE:
                setupTimer(50);
                break;
            case ADVANCED:
                setupTimer(40);
                break;
        }
    }

    private void setupGrid(int n) {
        randomAnswers = generateRandomAnswers();

        mGameGrid = (GridView) mFragment.findViewById(R.id.game_grid);
        //mGameGrid.setBackgroundResource(R.drawable.rounded_corners_container); // style
        mGameGrid.setNumColumns(n);

        CustomAdapter adapter = new CustomAdapter(getActivity(), n * n);
        adapter.setFragment(this);
        mGameGrid.setAdapter(adapter);

        mGameGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //Set<Integer> answers = randomAnswers;
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if (randomAnswers.contains(position)) {
                    randomAnswers.remove(position);
                    if (randomAnswers.size() == 0) {
                        updateScore();
                        setupGrid(mCols < MAX_COL ? ++mCols : MAX_COL);
                    }
                }
                updateClicks();
                Log.d("onItemClick", "" + position);
            }
        });
    }

    private void setupTimer(final int secs) {
        mTimer = (TextView) getActivity().findViewById(R.id.timer);
        handler = new Handler();
        runnable = new Runnable() {
            int t = secs;
            @Override
            public void run() {
                mTimer.setText(String.valueOf(t--));
                if (t < 10) {
                    mTimer.setTextColor(Color.RED);
                } else if (t < 20) {
                    mTimer.setTextColor(Color.YELLOW);
                } else {
                    mTimer.setTextColor(Color.GREEN);
                }
                if (t == -1) {
                    stopGame();
                    return;
                }
                handler.postDelayed(this, 1000);
            }
        };
        runnable.run();
    }

    private void stopGame() {
        FragmentLauncher.launchFragment((MainActivity) getActivity(),
                ScoreFragment.newInstance(mScoreCounter), R.id.fragment_container, false);
    }

    private void updateScore() {
        mScore.setText(String.valueOf(++mScoreCounter));
    }

    private void updateClicks() {
        mClicks.setText(String.valueOf(++mClickCounter));
    }

    private HashSet<Integer> generateRandomAnswers() {
        Random random = new Random();
        HashSet<Integer> s = new HashSet<>();
        int n = GameFragment.difficulty == Difficulty.ADVANCED ? 2 : 1;
        while (n > 0) {
            int r = random.nextInt(mCols * mCols);
            if (!s.contains(r)) {
                s.add(r);
                n--;
            }
        }
        Log.d("randomAnswers", s.toString());
        return s;
    }
}
