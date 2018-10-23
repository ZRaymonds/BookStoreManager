package com.app.bookstoremanager.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.app.bookstoremanager.R;
import com.app.bookstoremanager.base.BaseFragment;
import com.app.bookstoremanager.bean.RecommendInfo;
import com.app.bookstoremanager.utils.ToastUtil;
import com.app.bookstoremanager.view.adapter.MyLoader;
import com.app.bookstoremanager.view.adapter.RecommendAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BookCityFragment extends BaseFragment implements OnBannerListener {

    @BindView(R.id.banner)
    Banner banner;

    private ArrayList<RecommendInfo> birdList;
    private RecommendAdapter myAdapter;

    @BindView(R.id.recycleview)
    RecyclerView mRecycleView;

    private ArrayList<String> list_path;
    private ArrayList<String> list_title;

    private final String names[] = {
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat",
            "Lollipop",
            "Marshmallow"
    };
    private final String imageUrls[] = {
            "http://hawksaloft.org/wp-content/uploads/2012/08/614612316_20090805-_mg_3411-rufous-hummingbird-5x7.jpg",
            "http://www.gregscott.com/gjs_2007_spring/hummingbird/20070311_1948_100_0560.rufous_humminbird.jpg",
            "http://mosthdwallpapers.com/wp-content/uploads/2016/06/Flying-Hummingbird-Pictures.jpg",
            "https://wallpapercave.com/wp/alkKAoC.jpg",
            "http://mosthdwallpapers.com/wp-content/uploads/2016/06/Gorgeous-Hummingbird-Wallpapers-For-Desktop.jpg",
            "http://naturecanada.ca/wp-content/uploads/2014/07/Ruby-throat-Hummingbird-shutterstock_1953533.jpg",
            "http://images5.fanpop.com/image/photos/26100000/Hummingbird-hummingbirds-26167630-1024-740.jpg",
            "https://farm5.staticflickr.com/4065/4698051727_5024cd4e6c_b.jpg",
            "http://mosthdwallpapers.com/wp-content/uploads/2016/06/Beautiful-Hummingbird-HD-Photography.jpg",
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
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        list_title.add("好好学习");
        list_title.add("天天向上");
        list_title.add("热爱劳动");
        list_title.add("不搞对象");
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();

        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new GridLayoutManager(mActivity, 3));
        birdList = init();
        myAdapter = new RecommendAdapter(mActivity,birdList);
        mRecycleView.setAdapter(myAdapter);

    }

    private ArrayList<RecommendInfo> init() {
        ArrayList<RecommendInfo> birds = new ArrayList<>();

        for (int i = 0; i < names.length; ++i) {
            RecommendInfo bird = new RecommendInfo();
            bird.setImageUrl(imageUrls[i]);
            bird.setName(names[i]);
            birds.add(bird);
        }
        return birds;

    }

    //轮播图的监听方法
    @Override
    public void OnBannerClick(int position) {
        ToastUtil.show(mActivity,"你点了第" + position + "张轮播图");
        Log.i("tag", "你点了第" + position + "张轮播图");
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_book_city;
    }

}
