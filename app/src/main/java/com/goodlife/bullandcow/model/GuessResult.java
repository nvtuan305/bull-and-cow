package com.goodlife.bullandcow.model;

public class GuessResult {
    private BullCowNumber mBullCowNumber;
    private int mBull;
    private int mCow;

    public GuessResult(BullCowNumber bullCowNumber, int bull, int cow) {
        this.mBullCowNumber = bullCowNumber != null ? bullCowNumber.clone() : new BullCowNumber(4);
        this.mBull = bull;
        this.mCow = cow;
    }

    public BullCowNumber getBullCowNumber() {
        return mBullCowNumber;
    }

    public int getBull() {
        return mBull;
    }

    public int getCow() {
        return mCow;
    }

    public boolean isCorrectAnswer() {
        return this.mBull == this.mBullCowNumber.getDigit();
    }

    public String toString() {
        return mBullCowNumber.getNumberInString() + ": (" + mBull + ", " + mCow + ")";
    }
}
