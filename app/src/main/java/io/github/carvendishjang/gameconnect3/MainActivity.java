package io.github.carvendishjang.gameconnect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    // 0 is yellow and 1 is read, 2 is empty

    int activePlayer = 0;

    int[] state = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    boolean gameActive = true;

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {3, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tag = Integer.parseInt(counter.getTag().toString());

        Log.i("Tag", counter.getTag().toString());

        if (state[tag] != 2 || !gameActive) {

            return;
        }

        state[tag] = activePlayer;

        counter.setTranslationY(-1500);
        if (activePlayer == 0) {
            counter.setImageResource(R.drawable.yellow);
            activePlayer = 1;

        }
        else {
            counter.setImageResource(R.drawable.red);
            activePlayer = 0;

        }
        counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

        for (int[] winningPosition : winningPositions) {
            if ((state[winningPosition[0]] == state[winningPosition[1]]) && (state[winningPosition[1]] == state[winningPosition[2]]) && state[winningPosition[0]] != 2) {
                gameActive = false;
                String winner = "";
                if (state[winningPosition[0]] == 0) winner = "Yellow";
                else winner = "Red";

                Button gameRestartButton = (Button) findViewById(R.id.gameRestartButton);
                TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                winnerTextView.setText(winner + " has won!");
                gameRestartButton.setVisibility(View.VISIBLE);
                winnerTextView.setVisibility((View.VISIBLE));


                Log.i("Winner", "Someone has won!");

            }
        }
    }

    public void playAgain(View view) {
        Button gameRestartButton = (Button) findViewById(R.id.gameRestartButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        gameRestartButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility((View.INVISIBLE));
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        // update the initial state
        activePlayer = 0;

        for (int i = 0; i < state.length; i++) {
            state[i] = 2;
        }

        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
