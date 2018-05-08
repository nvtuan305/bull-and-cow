package com.goodlife.bullandcow.view.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.goodlife.bullandcow.model.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PreferenceHelper {
    private final String SHARE_PRE_NAME = "bull-and-cow";
    private final String KEY_RANK = "rank";
    private SharedPreferences mPref;

    public PreferenceHelper(@NonNull Context context) {
        mPref = context.getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE);
        // Make sure app auto create share preference file at the first time
        if (!mPref.contains("dummy")) {
            SharedPreferences.Editor editor = mPref.edit();
            editor.putString("dummy", "dummy");
            editor.apply();
        }
    }

    public void saveGameResult(long time, String number) {
        // <rank>time-number-time-number-time-number</rank>
        String rank = mPref.getString(KEY_RANK, "");
        rank = rank + "-" + time + "-" + number;
        if (rank.startsWith("-")) {
            rank = rank.substring(1);
        }

        // Save rank
        SharedPreferences.Editor editor = mPref.edit();
        editor.putString(KEY_RANK, rank);
        editor.apply();
    }

    public ArrayList<Rank> getRank() {
        ArrayList<Rank> ranks = new ArrayList<>();
        String rank = mPref.getString(KEY_RANK, "");
        String[] rankStr = rank.split("-");

        for (int i = 0; i < rankStr.length; i = i + 2) {
            if (i + 1 < rankStr.length) {
                Rank r = new Rank(Long.parseLong(rankStr[i], 10), rankStr[i + 1]);
                ranks.add(r);
            }
        }

        // Sort rank
        Collections.sort(ranks, new Comparator<Rank>() {
            @Override
            public int compare(Rank rank1, Rank rank2) {
                if (rank1.getTime() > rank2.getTime()) {
                    return 1;
                } else if (rank1.getTime() < rank2.getTime()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return ranks;
    }
}
