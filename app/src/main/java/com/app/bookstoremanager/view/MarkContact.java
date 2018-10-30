package com.app.bookstoremanager.view;


import com.app.bookstoremanager.base.BaseModel;
import com.app.bookstoremanager.base.BasePresenter;
import com.app.bookstoremanager.base.BaseView;
import com.app.bookstoremanager.bean.MarkBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by tctctc on 2017/3/24.
 */

public interface MarkContact {
    interface View extends BaseView {
        void showMarkList(List<MarkBean> list);

        void onItemClick(MarkBean bean);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void loadMarkList();

        public abstract void itemClick(MarkBean bean);
    }

    interface Model extends BaseModel {
        Observable<List<MarkBean>> getMarkList();
    }
}
