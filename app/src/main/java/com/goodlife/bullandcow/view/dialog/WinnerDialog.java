package com.goodlife.bullandcow.view.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.goodlife.bullandcow.R;

public class WinnerDialog extends DialogFragment {
    private Button mBtnOk, mBtnCancel;
    private View.OnClickListener mBtnOnClickListener, mBtnCancelOnClickListener;

    public static WinnerDialog newInstance() {
        return new WinnerDialog();
    }

    public void setOnClickListener(View.OnClickListener btnOkListener,
                                   View.OnClickListener btnCancelListener) {
        mBtnOnClickListener = btnOkListener;
        mBtnCancelOnClickListener = btnCancelListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        if (dialog.getWindow() != null) {
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_winner, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBtnOk = view.findViewById(R.id.btnOk);
        mBtnCancel = view.findViewById(R.id.btnCancel);
    }

    @Override
    public void onStart() {
        super.onStart();
        mBtnOk.setOnClickListener(mBtnOnClickListener);
        mBtnCancel.setOnClickListener(mBtnCancelOnClickListener);
    }
}
