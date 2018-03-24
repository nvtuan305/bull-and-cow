package com.goodlife.bullandcow.model;

import java.util.Arrays;

public class BullCowNumber {
    private int[] mNumbers;
    private int mCurrentPosition = 0;

    public BullCowNumber(int digit) {
        this.mNumbers = new int[digit];
        Arrays.fill(this.mNumbers, -1);
    }

    public int getDigit() {
        return mNumbers.length;
    }

    public int[] getNumbers() {
        return mNumbers;
    }

    private boolean isExist(int num) {
        for (int i = 0; i < mNumbers.length; i++) {
            if (mNumbers[i] == num) return true;
        }
        return false;
    }

    public boolean isValid() {
        return !isExist(-1);
    }

    public boolean addNumber(int num) {
        if (isExist(num) && mCurrentPosition < getDigit()) {
            return false;
        }

        if (mCurrentPosition < getDigit()) {
            this.mNumbers[mCurrentPosition] = num;
            this.mCurrentPosition++;
        }
        return true;
    }

    public void removeLastNumber() {
        if (this.mCurrentPosition > 0) {
            this.mCurrentPosition--;
        }
        this.mNumbers[mCurrentPosition] = -1;
    }

    public void reset() {
        this.mCurrentPosition = 0;
        Arrays.fill(this.mNumbers, -1);
    }

    public String getNumberInString() {
        StringBuilder strBuilder = new StringBuilder("");
        for (int num : mNumbers) {
            if (num != -1) {
                strBuilder.append(num);
            } else {
                strBuilder.append("_");
            }
        }
        return strBuilder.toString();
    }

    public GuessResult isEqual(BullCowNumber number2) {
        if (this.getDigit() != number2.getDigit()) {
            return new GuessResult(number2, 0, 0);
        }

        int bull = 0, cow = 0;
        int[] num2 = number2.getNumbers();
        boolean[] checker = new boolean[num2.length];
        for (int i = 0; i < num2.length; i++) {
            if (!checker[i] && num2[i] == mNumbers[i]) {
                bull++;
                checker[i] = true;
            } else {
                for (int j = 0; j < mNumbers.length; j++) {
                    if (!checker[j] && num2[i] == mNumbers[j]) {
                        cow++;
                        checker[j] = true;
                        break;
                    }
                }
            }
        }
        return new GuessResult(number2, bull, cow);
    }

    public BullCowNumber clone() {
        BullCowNumber bullCowNumber = new BullCowNumber(getDigit());
        System.arraycopy(mNumbers, 0, bullCowNumber.mNumbers,
                0, mNumbers.length);
        return bullCowNumber;
    }
}
