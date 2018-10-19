package com.app.bookstoremanager.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.app.bookstoremanager.R;
import com.app.bookstoremanager.view.fragment.BookCityFragment;
import com.app.bookstoremanager.view.fragment.BookIdeaFragment;
import com.app.bookstoremanager.view.fragment.BookMyFragment;
import com.app.bookstoremanager.view.fragment.BookSelfFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.mainContent)
    Fragment mainContent;

    BottomNavigationView navigation;

    private FragmentManager fragmentManager;

    BookCityFragment bookCityFragment = new BookCityFragment();
    BookSelfFragment bookSelfFragment = new BookSelfFragment();
    BookIdeaFragment bookIdeaFragment = new BookIdeaFragment();
    BookMyFragment bookMyFragment = new BookMyFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fragmentManager = getSupportFragmentManager();

        addFragment(bookSelfFragment);
        hideFragment(bookSelfFragment);
        addFragment(bookIdeaFragment);
        hideFragment(bookIdeaFragment);
        addFragment(bookMyFragment);
        hideFragment(bookMyFragment);
        addFragment(bookCityFragment);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.book_city:
                    showFragment(bookCityFragment);
                    hideFragment(bookSelfFragment);
                    hideFragment(bookIdeaFragment);
                    hideFragment(bookMyFragment);
                    return true;
                case R.id.book_self:
                    showFragment(bookSelfFragment);
                    hideFragment(bookCityFragment);
                    hideFragment(bookIdeaFragment);
                    hideFragment(bookMyFragment);
                    return true;
                case R.id.book_idea:
                    showFragment(bookIdeaFragment);
                    hideFragment(bookSelfFragment);
                    hideFragment(bookCityFragment);
                    hideFragment(bookMyFragment);
                    return true;
                case R.id.book_my:
                    showFragment(bookMyFragment);
                    hideFragment(bookSelfFragment);
                    hideFragment(bookIdeaFragment);
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

}
