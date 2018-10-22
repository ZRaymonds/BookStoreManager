package com.app.bookstoremanager.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.app.bookstoremanager.R;
import com.app.bookstoremanager.base.BaseFragment;

public class BookIdeaFragment extends BaseFragment {

    @Override
    protected View initView(Bundle savedInstanceState) {
        View view = View.inflate(mActivity,R.layout.fragment_book_idea,null);
//        imagePath.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=345497051,870782541&fm=11&gp=0.jpg");
//        imagePath.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2855051181,3062079698&fm=26&gp=0.jpg");
//        imagePath.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2365926110,751802226&fm=26&gp=0.jpg");
//        imagePath.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2552196253,1508996934&fm=26&gp=0.jpg");
        return view;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_book_idea;
    }

}
