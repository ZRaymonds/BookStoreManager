package com.app.bookstoremanager.view;

import com.app.bookstoremanager.base.BaseModel;
import com.app.bookstoremanager.base.BasePresenter;
import com.app.bookstoremanager.base.BaseView;
import com.app.bookstoremanager.bean.BookBean;
import com.app.bookstoremanager.bean.ScanBook;

import java.io.File;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by tctctc on 2017/3/24.
 */

public interface ScanContact {
    interface View extends BaseView {
        void toShelf();

        void whenStartScan();

        void whenStopScan();

        void refresh(int totalNum);

        void whenScan(File file);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void onAddBooks(List<ScanBook> files);

        public abstract void onStartScanBooks(File file, String rex);

        public abstract void onStopScanBooks();
    }

    interface Model extends BaseModel {
        Observable<Boolean> addBookList(List<BookBean> been);

        Flowable<File> scanFile(File file, String rex);

        int getTotalNum();

    }
}
