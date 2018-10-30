package com.app.bookstoremanager.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.app.bookstoremanager.R;
import com.app.bookstoremanager.base.BaseFragment;
import com.app.bookstoremanager.view.adapter.DirectoryPageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by tctctc on 2017/4/7.
 * Function:
 */

public class DirectoryFragment extends BaseFragment {

    @BindView(R.id.directory_pager)
    ViewPager mViewPager;

    @BindView(R.id.directory_tab)
    TabLayout mTabLayout;

    private List<Fragment> mFragmentList = new ArrayList();

    @Override
    protected View initView(Bundle savedInstanceState) {
        View view = View.inflate(mActivity, R.layout.fragment_directory, null);
        return view;
    }

    @Override
    protected void initData() {
        mFragmentList.add(new DirectoryListFragment());
        mFragmentList.add(new DirectoryListFragment());
        mFragmentList.add(new DirectoryListFragment());
        String[] titles = getResources().getStringArray(R.array.read_drawer);
        DirectoryPageAdapter adapter = new DirectoryPageAdapter(getChildFragmentManager(), mFragmentList, titles);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == mFragmentList.size() - 1) {
                    mRxManager.post("onPageSelected", true);
                } else {
                    mRxManager.post("onPageSelected", false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_directory;
    }
}
