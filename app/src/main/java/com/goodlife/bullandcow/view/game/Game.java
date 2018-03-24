package com.goodlife.bullandcow.view.game;

import android.util.Log;

import com.goodlife.bullandcow.model.BullCowNumber;
import com.goodlife.bullandcow.model.GuessResult;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private ArrayList<BullCowNumber> mUserBullCowNumbers = new ArrayList<>();
    private ArrayList<GuessResult> mUserGuessResults = new ArrayList<>();
    private int mDigit;
    private BullCowNumber mSecretNumber;

    private final String TAG = "GAME";

    public Game(int digit) {
        this.mDigit = digit;
        this.createSecretNumber();
        Log.e(TAG, mSecretNumber.getNumberInString());
    }

    private void createSecretNumber() {
        this.mSecretNumber = new BullCowNumber(mDigit);
        int[] number = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int idx = 10, i = 0, num;
        while (i < mDigit) {
            num = new Random().nextInt(idx); // Random a number in [0, ..., idx - 1]
            this.mSecretNumber.addNumber(number[num]);
            number[num] = number[idx - 1]; // Swap the last number to the selected number
            idx = idx > 1 ? idx - 1 : 1;
            i++;
        }
    }

    public GuessResult checkMyGuess(BullCowNumber bullCowNumber) {
        GuessResult result = this.mSecretNumber.isEqual(bullCowNumber);
        mUserBullCowNumbers.add(bullCowNumber);
        mUserGuessResults.add(result);
        return result;
    }

    // ---------------------------------------------------------------------------------------------
    // Getter and setter
    // ---------------------------------------------------------------------------------------------

    public ArrayList<BullCowNumber> getUserBullCowNumbers() {
        return mUserBullCowNumbers;
    }

    public ArrayList<GuessResult> getUserGuessResults() {
        return mUserGuessResults;
    }

    public int getDigit() {
        return mDigit;
    }

    public BullCowNumber getSecretNumber() {
        return mSecretNumber;
    }
}
