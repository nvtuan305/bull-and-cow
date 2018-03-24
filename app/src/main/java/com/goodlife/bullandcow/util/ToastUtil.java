package com.goodlife.bullandcow.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    public static void showShort(Context context, String message) {
        if (context == null) return;
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, String message) {
        if (context == null) return;
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
