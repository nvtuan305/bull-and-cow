package com.goodlife.bullandcow.view.base;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {

    private ArrayList<String> mDialogTags = new ArrayList<>();

    @Override
    protected void onDestroy() {
        dismissAllDialog();
        super.onDestroy();
    }

    /**
     * Show an DialogFragment
     *
     * @param dialog
     * @param tag
     */
    public void showDialog(DialogFragment dialog, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prevFrag = getSupportFragmentManager().findFragmentByTag(tag);
        if (prevFrag != null) ft.remove(prevFrag);
        ft.add(dialog, tag);
        ft.commitAllowingStateLoss();
    }

    /**
     * Remove all DialogFragment is showing
     */
    protected void dismissAllDialog() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prevFrag = null;

        for (String tag : mDialogTags) {
            prevFrag = getSupportFragmentManager().findFragmentByTag(tag);
            if (prevFrag != null) {
                ft.remove(prevFrag);
                prevFrag = null;
            }
        }
        mDialogTags.clear();
    }
}
