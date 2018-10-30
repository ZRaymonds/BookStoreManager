package com.app.bookstoremanager.common.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.app.bookstoremanager.R;
import com.app.bookstoremanager.view.SelectView;


/**
 * Created by tctctc on 2017/4/1.
 * Function:
 */

@SuppressLint("AppCompatCustomView")
public class SelectTextView extends TextView implements SelectView {

    public SelectTextView(Context context) {
        this(context, null);
    }

    public SelectTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void select() {
        setBackgroundResource(R.drawable.dialog_button_select_shape);
    }

    @Override
    public void unSelect() {
        setBackgroundResource(R.drawable.dialog_button_un_select_shape);
    }
}
