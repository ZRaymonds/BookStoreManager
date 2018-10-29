package com.app.bookstoremanager.view;

import com.app.bookstoremanager.base.BaseModel;
import com.app.bookstoremanager.base.BasePresenter;
import com.app.bookstoremanager.base.BaseView;
import com.app.bookstoremanager.bean.BookBean;
import com.app.bookstoremanager.bean.ScanBook;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by tctctc on 2017/3/24.
 */

public interface ImportContact {
    interface View extends BaseView {
        void showScanBookList(List<ScanBook> files, int fileNum, int importableNum);
        void toShelf();
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void onAddBooks(List<ScanBook> files);
        public abstract void onGetFileList(File file);
    }

    interface Model extends BaseModel {
        Observable<Boolean> addBookList(List<BookBean> been);
        List<File> getFileList(File rootFile);
    }
}
