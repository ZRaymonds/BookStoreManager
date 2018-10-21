package com.app.bookstoremanager.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.app.bookstoremanager.R;
import com.app.bookstoremanager.base.BaseFragment;

public class BookIdeaFragment extends BaseFragment {

    @Override
    protected View initView(Bundle savedInstanceState) {
        View view = View.inflate(mActivity,R.layout.fragment_book_idea,null);
        return view;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_book_idea;
    }

}
