package com.app.bookstoremanager.view;

import com.app.bookstoremanager.base.BaseModel;
import com.app.bookstoremanager.base.BasePresenter;
import com.app.bookstoremanager.base.BaseView;
import com.app.bookstoremanager.bean.BookBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by tctctc on 2017/3/24.
 */

public interface ShelfContact {
    interface View extends BaseView {
        void refreshBookList(List<BookBean> books);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void onLoadBookList();

        public abstract void onDeleteBooks(List<BookBean> books);
    }

    interface Model extends BaseModel {
        Observable<List<BookBean>> loadBookList();

        Observable<Boolean> removeDeleteBooks(List<BookBean> books);

        Observable<Boolean> removeBooks(List<BookBean> books);
    }
}
