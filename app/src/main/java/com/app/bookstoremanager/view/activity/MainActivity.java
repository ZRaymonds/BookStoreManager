package com.app.bookstoremanager.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.app.bookstoremanager.R;
import com.app.bookstoremanager.base.BaseActivity;
import com.app.bookstoremanager.view.fragment.BookCityFragment;
import com.app.bookstoremanager.view.fragment.BookIdeaFragment;
import com.app.bookstoremanager.view.fragment.BookMyFragment;
import com.app.bookstoremanager.view.fragment.BookSelfFragment;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.mainContent)
    FrameLayout mainContent;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private FragmentManager fragmentManager;

    BookCityFragment bookCityFragment = new BookCityFragment();
    BookSelfFragment bookSelfFragment = new BookSelfFragment();
    //    BookIdeaFragment bookIdeaFragment = new BookIdeaFragment();
    BookMyFragment bookMyFragment = new BookMyFragment();

    private long lastBackTime = 0;
    private long currentBackTime = 0;

    @Override
    protected void initData(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fragmentManager = getSupportFragmentManager();

        addFragment(bookSelfFragment);
        hideFragment(bookSelfFragment);
//        addFragment(bookIdeaFragment);
//        hideFragment(bookIdeaFragment);
        addFragment(bookMyFragment);
        hideFragment(bookMyFragment);
        addFragment(bookCityFragment);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.book_city:
                    showFragment(bookCityFragment);
                    hideFragment(bookSelfFragment);
//                    hideFragment(bookIdeaFragment);
                    hideFragment(bookMyFragment);
                    return true;
                case R.id.book_self:
                    showFragment(bookSelfFragment);
                    hideFragment(bookCityFragment);
//                    hideFragment(bookIdeaFragment);
                    hideFragment(bookMyFragment);
                    return true;
//                case R.id.book_idea:
//                    showFragment(bookIdeaFragment);
//                    hideFragment(bookSelfFragment);
//                    hideFragment(bookCityFragment);
//                    hideFragment(bookMyFragment);
//                    return true;
                case R.id.book_my:
                    showFragment(bookMyFragment);
                    hideFragment(bookSelfFragment);
//                    hideFragment(bookIdeaFragment);
                    hideFragment(bookCityFragment);
                    return true;
            }
            return false;
        }
    };

    private void addFragment(Fragment fragment) {
        fragmentManager.beginTransaction().add(R.id.mainContent, fragment).commit();
    }

    private void showFragment(Fragment fragment) {
        fragmentManager.beginTransaction().show(fragment).commit();
    }

    private void hideFragment(Fragment fragment) {
        fragmentManager.beginTransaction().hide(fragment).commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            currentBackTime = System.currentTimeMillis();
            if (currentBackTime - lastBackTime > 2 * 1000) {
                Toast.makeText(this, "再按一次返回键退出", Toast.LENGTH_SHORT).show();
                lastBackTime = currentBackTime;
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
