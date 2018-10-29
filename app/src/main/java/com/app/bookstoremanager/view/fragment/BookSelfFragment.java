package com.app.bookstoremanager.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.bookstoremanager.R;
import com.app.bookstoremanager.base.BaseFragment;
import com.app.bookstoremanager.bean.BookBean;
import com.app.bookstoremanager.common.view.GridSpacingItemDecoration;
import com.app.bookstoremanager.model.impl.BookDao;
import com.app.bookstoremanager.presenter.ShelfPresenter;
import com.app.bookstoremanager.view.ShelfContact;
import com.app.bookstoremanager.view.activity.ImportBookActivity;
import com.app.bookstoremanager.view.activity.ReadBookActivity;
import com.app.bookstoremanager.view.adapter.ShelfBookAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class BookSelfFragment extends BaseFragment implements ShelfBookAdapter.OnClickCallBack, ShelfContact.View {


    @BindView(R.id.bookRecycle)
    RecyclerView mBookRecycle;

    @BindView(R.id.add_book)
    FloatingActionButton mAddBookBt;

    @BindView(R.id.book_operate)
    LinearLayout mBookOperate;

    @BindView(R.id.book_delete)
    TextView mBookDelete;

    @BindView(R.id.book_all_select)
    TextView mBookAllSelect;

    private ShelfBookAdapter mAdapter;
    private List<BookBean> mBooks;

    private List<BookBean> mSelectBooks;

    private ShelfPresenter mPresenter;

    private boolean isSelectAll;

    @Override
    protected View initView(Bundle savedInstanceState) {
        View view = View.inflate(mActivity, R.layout.fragment_book_self, null);
        return view;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_book_self;
    }

    @Override
    protected void initData() {
        mBooks = new ArrayList<>();
        mAdapter = new ShelfBookAdapter(mBooks, mActivity, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        mBookRecycle.setLayoutManager(layoutManager);
        mBookRecycle.setAdapter(mAdapter);
        mBookRecycle.setLongClickable(true);
        mBookRecycle.addItemDecoration(new GridSpacingItemDecoration(3, 50, true));
        mSelectBooks = new ArrayList<>();
        mPresenter = new ShelfPresenter();
        mPresenter.setVM(new BookDao(), this);
        mPresenter.onLoadBookList();
    }

    @Override
    public void OnLongClick() {
        mBookOperate.setVisibility(View.VISIBLE);
        mAddBookBt.setVisibility(View.INVISIBLE);
    }

    @Override
    public void OnBookClick(BookBean bookBean) {
        Intent intent = new Intent(getActivity(), ReadBookActivity.class);
        intent.putExtra("book", bookBean);
        startActivity(intent);
    }

    @Override
    public void selectBook(BookBean bookBean) {
        mSelectBooks.add(bookBean);
        mBookDelete.setText("删除书籍(" + mSelectBooks.size() + ")");
        if (mSelectBooks.size() == mBooks.size()) {
            isSelectAll = true;
            mBookAllSelect.setText(getResources().getString(R.string.all_un_select));
        }
    }

    @Override
    public void unSelectBook(BookBean bookBean) {
        mSelectBooks.remove(bookBean);
        mBookDelete.setText("删除书籍(" + mSelectBooks.size() + ")");
        if (mSelectBooks.size() < mBooks.size()) {
            isSelectAll = false;
            mBookAllSelect.setText(getResources().getString(R.string.all_select));
        }
    }

    @OnClick({R.id.book_delete, R.id.book_all_select, R.id.add_book})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.book_delete:
                mPresenter.onDeleteBooks(mSelectBooks);
                break;
            case R.id.book_all_select:
                allSelectBook(mAdapter.getBookMap());
                break;
            case R.id.add_book:
                Intent intent = new Intent(getActivity(), ImportBookActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }

    private void allSelectBook(HashMap<BookBean, Integer> bookMap) {
        //本来是全不选状态,此时按钮应该显示的是全选，所以点击之后变成全不选
        if (!isSelectAll) {
            for (Map.Entry<BookBean, Integer> entry : bookMap.entrySet()) {
                if (entry.getValue() == 2) {
                    entry.setValue(1);
                    selectBook(entry.getKey());
                }
            }
        }
        //本来是全选状态,此时按钮应该显示的是全不选，所以点击之后变成全选
        else {
            for (Map.Entry<BookBean, Integer> entry : bookMap.entrySet()) {
                if (entry.getValue() == 1) {
                    entry.setValue(2);
                    unSelectBook(entry.getKey());
                }
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshBookList(List<BookBean> books) {
        mBooks.clear();
        mBooks.addAll(books);
        exitLongClick();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDestroy();
        mPresenter = null;
    }

    public void exitLongClick() {
        mAdapter.statusInit();
        mBookOperate.setVisibility(View.INVISIBLE);
        mBookAllSelect.setText("全选");
        mAddBookBt.setVisibility(View.VISIBLE);
        mAdapter.notifyDataSetChanged();
    }

    public boolean onBackPressed() {
        if (mAdapter.isLongClick()) {
            mAdapter.statusInit();
            exitLongClick();
            return true;
        } else return false;
    }

}
