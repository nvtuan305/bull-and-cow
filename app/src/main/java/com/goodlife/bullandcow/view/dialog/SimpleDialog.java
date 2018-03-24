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
import android.widget.TextView;

import com.goodlife.bullandcow.R;

public class SimpleDialog extends DialogFragment {
    private static final String ARG_MESSAGE = "dialog_message";
    private static final String ARG_OK_TEXT = "dialog_button_ok_text";
    private static final String ARG_CANCEL_TEXT = "dialog_button_cancel_text";

    private TextView mTvMessage;
    private Button mBtnOk, mBtnCancel;
    private View.OnClickListener mBtnOnClickListener, mBtnCancelOnClickListener;

    public static SimpleDialog newInstance(String message, String okText, String cancelText) {
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
        args.putString(ARG_OK_TEXT, okText);
        args.putString(ARG_CANCEL_TEXT, cancelText);
        SimpleDialog simpleDialog = new SimpleDialog();
        simpleDialog.setArguments(args);
        return simpleDialog;
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
        return inflater.inflate(R.layout.dialog_simple, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTvMessage = view.findViewById(R.id.tvMessage);
        mBtnOk = view.findViewById(R.id.btnOk);
        mBtnCancel = view.findViewById(R.id.btnCancel);
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
            String message = args.getString(ARG_MESSAGE, getString(R.string.default_dialog_message));
            mTvMessage.setText(message);

            String okMessage = args.getString(ARG_OK_TEXT, getString(R.string.action_yes));
            mBtnOk.setText(okMessage);

            String cancelMessage = args.getString(ARG_CANCEL_TEXT, getString(R.string.action_cancel));
            mBtnCancel.setText(cancelMessage);
        }

        mBtnOk.setOnClickListener(mBtnOnClickListener);
        mBtnCancel.setOnClickListener(mBtnCancelOnClickListener);
    }
}
