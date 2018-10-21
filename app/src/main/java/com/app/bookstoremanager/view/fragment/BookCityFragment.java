package com.app.bookstoremanager.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.bookstoremanager.R;
import com.app.bookstoremanager.base.BaseFragment;

public class BookCityFragment extends BaseFragment {

    @Override
    protected View initView(Bundle savedInstanceState) {
        View view = View.inflate(mActivity,R.layout.fragment_book_city,null);
        return view;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_book_city;
    }

}
