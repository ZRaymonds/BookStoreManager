package com.app.bookstoremanager.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.app.bookstoremanager.R;
import com.app.bookstoremanager.base.BaseFragment;
import com.app.bookstoremanager.bean.RecommendInfo;
import com.app.bookstoremanager.common.view.GridSpacingItemDecoration;
import com.app.bookstoremanager.view.adapter.RecycleViewGridAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BookCityFragment extends BaseFragment {

    @BindView(R.id.recycleview)
    RecyclerView mRecycleView;

    private GridLayoutManager gridLayoutManager;
    private ArrayList<RecommendInfo> birdList;

    private ArrayList<String> list_path;
    private ArrayList<String> list_title;

    private final String names[] = {
            "飘",
            "飘",
            "深入理解计算机系统英文原版",
            "夜莺与玫瑰",
            "小王子",
            "雅舍谈吃",
            "被人遗忘的人",
            "论摄影",
            "荒野之歌",
            "乌合之众",
    };
    private final String imageUrls[] = {
            "http://192.168.1.138:8080/image/upload/20181023165753075_973580.jpg",
            "http://192.168.1.138:8080/image/upload/20181023165753075_973580.jpg",
            "http://192.168.1.138:8080/image/upload/20181023165333411_738415.jpg",
            "http://192.168.1.138:8080/image/upload/20181023163344967_987827.jpg",
            "http://192.168.1.138:8080/image/upload/20181023163332845_389741.jpg",
            "http://192.168.1.138:8080/image/upload/20181023101919764_944058.jpg",
            "http://192.168.1.138:8080/image/upload/20181023101608846_378596.jpg",
            "http://192.168.1.138:8080/image/upload/20181023101308922_800869.jpg",
            "http://192.168.1.138:8080/image/upload/20181023101035051_648230.jpg",
            "http://192.168.1.138:8080/image/upload/20181023095631587_032144.jpg",
    };


    @Override
    protected View initView(Bundle savedInstanceState) {
        View view = View.inflate(mActivity, R.layout.fragment_book_city, null);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        list_path = new ArrayList<>();
        //放标题的集合
        list_title = new ArrayList<>();
        list_path.add("http://111.230.204.150/img/examples/lun01.jpg");
        list_path.add("http://111.230.204.150/img/examples/lun02.jpg");
        list_path.add("http://111.230.204.150/img/examples/lun03.jpg");
        list_path.add("http://111.230.204.150/img/examples/lun02.jpg");
        list_title.add("好好学习");
        list_title.add("天天向上");
        list_title.add("热爱劳动");
        list_title.add("不搞对象");
    }

    @Override
    protected void initEvent() {
        gridLayoutManager = new GridLayoutManager(mActivity, 3);
        birdList = init();
        RecycleViewGridAdapter recycleViewAdapter = new RecycleViewGridAdapter(mActivity, birdList, list_path, list_title);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.recommend_layout, null);
        recycleViewAdapter.addHeadView(view);

        //如果添加了头部或者尾部 就需要做相关的SpanSize的修改
        recycleViewAdapter.setChangeGridLayoutManager(new RecycleViewGridAdapter.ChangeGridLayoutManagerSpance() {
            @Override
            public void change(final int size, final boolean isAddHead, final boolean isAddFoot) {
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        int spanSzie = 1;
                        if (isAddHead) {
                            if (position == 0) {
                                spanSzie = gridLayoutManager.getSpanCount();
                            }
                        }

                        if (isAddFoot) {
                            if (position == size) {
                                spanSzie = gridLayoutManager.getSpanCount();
                            }
                        }
                        return spanSzie;
                    }
                });
            }
        });
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(gridLayoutManager);
//        mRecycleView.addItemDecoration(new GridSpacingItemDecoration(3,1,true));
        mRecycleView.setAdapter(recycleViewAdapter);
    }

    private ArrayList<RecommendInfo> init() {
        ArrayList<RecommendInfo> birds = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            RecommendInfo bird = new RecommendInfo();
            bird.setImageUrl(imageUrls[i]);
            bird.setName(names[i]);
            birds.add(bird);
        }
        return birds;
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_book_city;
    }

}
