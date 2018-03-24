package com.goodlife.bullandcow.view.game;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.goodlife.bullandcow.R;
import com.goodlife.bullandcow.model.BullCowNumber;
import com.goodlife.bullandcow.model.GuessResult;
import com.goodlife.bullandcow.util.ToastUtil;
import com.goodlife.bullandcow.view.base.BaseActivity;
import com.goodlife.bullandcow.view.dialog.SimpleDialog;
import com.goodlife.bullandcow.view.dialog.WinnerDialog;

public class GameActivity extends BaseActivity {
    private TextView mTvInputNumber, mTvEmptyGuess;
    private RecyclerView mRcvGuessResult;

    private final int DIGIT = 4;
    private Game mGame;
    private BullCowNumber mInputNumber;
    private GuessResultAdapter mGuessResultAdapter;
    private boolean mIsWin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.initializeGame();
        this.setupViews();
    }

    @Override
    public void onBackPressed() {
        if (!mIsWin) {
            showQuitGameDialog();
            return;
        }
        super.onBackPressed();
    }

    /**
     * Initialize the game
     */
    private void initializeGame() {
        mInputNumber = new BullCowNumber(DIGIT);
        mGame = new Game(DIGIT);
    }

    /**
     * Setup views
     */
    private void setupViews() {
        // Empty guess TextView
        mTvEmptyGuess = findViewById(R.id.tvEmptyGuess);

        // Input number TextView
        mTvInputNumber = findViewById(R.id.tvInputNumber);
        showInputNumber();

        // Guess list
        mRcvGuessResult = findViewById(R.id.rcvGuess);
        mRcvGuessResult.setLayoutManager(new LinearLayoutManager(this));
        mRcvGuessResult.setNestedScrollingEnabled(false);
        mGuessResultAdapter = new GuessResultAdapter();
        mRcvGuessResult.setAdapter(mGuessResultAdapter);

        // Keyboard buttons
        Button[] keyboardNumbers = new Button[]{
                findViewById(R.id.btnNumber0),
                findViewById(R.id.btnNumber1),
                findViewById(R.id.btnNumber2),
                findViewById(R.id.btnNumber3),
                findViewById(R.id.btnNumber4),
                findViewById(R.id.btnNumber5),
                findViewById(R.id.btnNumber6),
                findViewById(R.id.btnNumber7),
                findViewById(R.id.btnNumber8),
                findViewById(R.id.btnNumber9)
        };
        for (int i = 0; i < keyboardNumbers.length; i++) {
            final int number = i;
            keyboardNumbers[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addNumber(number);
                }
            });
        }

        // Delete button
        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeNumber();
            }
        });

        // Check button
        Button btnCheck = findViewById(R.id.btnCheckTheGuess);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });
    }

    /**
     * Add a number to input number
     *
     * @param num next number
     */
    private void addNumber(int num) {
        if (!mInputNumber.addNumber(num)) {
            ToastUtil.showLong(this, getString(R.string.message_duplicate_number));
        }
        showInputNumber();
    }

    /**
     * Remove the last number from input number
     */
    private void removeNumber() {
        mInputNumber.removeLastNumber();
        showInputNumber();
    }

    /**
     * Show input number
     */
    private void showInputNumber() {
        String prefix = getString(R.string.you_guess);
        mTvInputNumber.setText(prefix + " " + mInputNumber.getNumberInString());
    }

    /**
     * Check the guess
     */
    private void checkGuess() {
        if (!mInputNumber.isValid()) {
            ToastUtil.showShort(this, "The number must have " + DIGIT + " numbers");
            return;
        }

        GuessResult result = mGame.checkMyGuess(mInputNumber);
        mTvEmptyGuess.setVisibility(View.GONE);
        mRcvGuessResult.setVisibility(View.VISIBLE);
        mGuessResultAdapter.add(result);
        mInputNumber.reset();
        showInputNumber();

        if (result.isCorrectAnswer()) {
            showWinner();
        }
    }

    /**
     * Reset the game
     */
    private void resetGame() {
        initializeGame();
        showInputNumber();
        mGuessResultAdapter.reset();
        mIsWin = false;
        mTvEmptyGuess.setVisibility(View.VISIBLE);
        mRcvGuessResult.setVisibility(View.GONE);
    }

    /**
     * Show the winner
     */
    private void showWinner() {
        mIsWin = true;
        final WinnerDialog winnerDialog = WinnerDialog.newInstance();
        winnerDialog.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        winnerDialog.dismiss();
                        resetGame();
                    }
                },
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToLeaderboard();
                    }
                });
        winnerDialog.setCancelable(false);
        showDialog(winnerDialog, "WinnerDialog");
    }

    /**
     * Show the quit dialog
     */
    private void showQuitGameDialog() {
        final SimpleDialog quitGameDialog = SimpleDialog.newInstance(
                getString(R.string.cancel_game),
                getString(R.string.action_give_up),
                getString(R.string.action_cancel)
        );
        quitGameDialog.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        quit();
                    }
                },
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        quitGameDialog.dismiss();
                    }
                });
        quitGameDialog.setCancelable(false);
        showDialog(quitGameDialog, "QuitGameDialog");
    }

    /**
     * Finish the activity
     */
    private void quit() {
        this.finish();
    }

    /**
     * Go to the Leaderboard activity
     */
    private void goToLeaderboard() {
        // TODO
        quit();
    }
}
