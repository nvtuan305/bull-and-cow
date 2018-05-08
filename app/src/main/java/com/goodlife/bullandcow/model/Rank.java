package com.goodlife.bullandcow.model;

public class Rank {
    private long mTime;
    private String mNumber;

    public Rank(long time, String number) {
        this.mTime = time;
        this.mNumber = number;
    }

    public long getTime() {
        return mTime;
    }

    public String getNumber() {
        return mNumber;
    }
}
