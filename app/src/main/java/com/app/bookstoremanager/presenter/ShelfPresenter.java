package com.app.bookstoremanager.presenter;

import com.app.bookstoremanager.bean.BookBean;
import com.app.bookstoremanager.view.ShelfContact;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by tctctc on 2017/3/25.
 * Function:
 */

public class ShelfPresenter extends ShelfContact.Presenter {

    @Override
    public void onStart() {
    }

    @Override
    public void onLoadBookList() {
        mModel.loadBookList().subscribe(new Consumer<List<BookBean>>() {
            @Override
            public void accept(@NonNull List<BookBean> books) throws Exception {
                mView.refreshBookList(books);
            }
        });
    }

    @Override
    public void onDeleteBooks(List<BookBean> books) {
        mModel.removeBooks(books).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean aBoolean) throws Exception {
                onLoadBookList();
            }
        });
    }
}
