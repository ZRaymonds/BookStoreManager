package com.app.bookstoremanager.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.app.bookstoremanager.R;
import com.app.bookstoremanager.base.BaseAdapter;
import com.app.bookstoremanager.base.BasePageFragment;
import com.app.bookstoremanager.bean.Directory;
import com.app.bookstoremanager.common.ContentManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

import static com.app.bookstoremanager.BookApplication.getContext;

/**
 * Created by tctctc on 2017/4/7.
 * Function:
 */

public class DirectoryListFragment extends BasePageFragment {

    private List<Directory> mDirectoryList;

    private ContentManager mManager;

    @BindView(R.id.directory_recycle)
    RecyclerView mRecyclerView;

    @BindView(R.id.empty)
    TextView mEmpty;

    private BaseAdapter<Directory> adapter;

    private int mCurrentPosition;
    private int mLastPosition;
    private LinearLayoutManager manager;

    @Override
    protected void initView() {
//        setLazyLoad(false);
        mManager = ContentManager.getInstance();
        mDirectoryList = new ArrayList<>();
        manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new BaseAdapter<>(getContext(), R.layout.directory_item, mDirectoryList, new BaseAdapter.OnItem() {
            @Override
            public void onClick(int position) {
                Directory directory = mDirectoryList.get(position);
                mRxManager.post("chapter", directory);
                mRxManager.post("close drawer", "");
            }

            @Override
            public void onLongClick(int position) {
            }

            @Override
            public void onBind(BaseAdapter.BaseViewHolder mHolder, int position) {
                Directory directory = mDirectoryList.get(position);
                if (position == mCurrentPosition) {
                    mHolder.setText(R.id.chapterName, directory.getName(), getResources().getColor(R.color.tab_text_color));
                } else {
                    mHolder.setText(R.id.chapterName, directory.getName(), getResources().getColor(R.color.black));
                }
            }
        });
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(manager);

        mRxManager.onEvent("Directory", new Consumer<Integer>() {


            @Override
            public void accept(@NonNull Integer result) throws Exception {
                if (result == 1) {
                    judgeLoadData(true);
                    mEmpty.setVisibility(View.INVISIBLE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                } else if (result == 2) {
                    //无法提取目录
                    mEmpty.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.INVISIBLE);
                }
            }
        });
        mRxManager.onEvent("updateCurrentChapter", new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer result) {
                mLastPosition = mCurrentPosition;
                mCurrentPosition = result;
                adapter.notifyItemChanged(mLastPosition);
                adapter.notifyItemChanged(mCurrentPosition);

                mRecyclerView.scrollToPosition(mCurrentPosition);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.directory_list_fragment;
    }

    @Override
    public boolean fetchData() {
        mDirectoryList.clear();
        mDirectoryList.addAll(mManager.getDirectory());
        adapter.notifyDataSetChanged();

        if (mDirectoryList.isEmpty()) {
            mEmpty.setVisibility(View.VISIBLE);
        } else {
            mEmpty.setVisibility(View.GONE);
        }
        return true;
    }
}
