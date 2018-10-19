package com.app.bookstoremanager.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    public ToastUtil() {
    }

    /**
     * 根据文本打印吐丝
     *
     * @param context 应用程序上下文
     * @param text    要显示的文本
     */
    public static void show(Context context, CharSequence text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

}
