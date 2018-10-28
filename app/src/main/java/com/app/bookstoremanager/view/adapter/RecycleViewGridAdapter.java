package com.app.bookstoremanager.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.bookstoremanager.R;
import com.app.bookstoremanager.bean.RecommendInfo;
import com.app.bookstoremanager.utils.ToastUtil;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewGridAdapter extends RecyclerView.Adapter<RecycleViewGridAdapter.MyViewHolder>{

    private static final int TYPE_HEADER = 0, TYPE_ITEM = 1, TYPE_FOOT = 2;
    private View headView;
    private View footView;
    private int headViewSize = 0;
    private int footViewSize = 0;
    private ChangeGridLayoutManagerSpance changeGridLayoutManager;
    private boolean isAddFoot = false;
    private boolean isAddHead = false;

    private ArrayList<String> list_path;
    private ArrayList<String> list_title;
    private Context mContext;
    public ArrayList<RecommendInfo> mBirdsList;

    public interface ChangeGridLayoutManagerSpance {
         void change(int size, boolean isAddHead, boolean isAddFoot);
    }

    //提供接口给 让LayoutManager根据添加尾部 头部与否来做判断 显示头部与底部的SpanSize要在添加头部和尾部之后
    public void setChangeGridLayoutManager(ChangeGridLayoutManagerSpance changeGridLayoutManager) {
        this.changeGridLayoutManager = changeGridLayoutManager;
        changeGridLayoutManager.change(getItemCount() - 1, isAddHead, isAddFoot);
    }

    public RecycleViewGridAdapter(Context mContext, ArrayList<RecommendInfo> mBirdsList, ArrayList<String> list_path, ArrayList<String> list_title) {
        this.mContext = mContext;
        this.mBirdsList = mBirdsList;
        this.list_path = list_path;
        this.list_title = list_title;
    }

    public void addHeadView(View view) {
        headView = view;
        headViewSize = 1;
        isAddHead = true;
    }

    public void addFootView(View view) {
        footView = view;
        footViewSize = 1;
        isAddFoot = true;
    }

    @Override
    public int getItemViewType(int position) {
        int type = TYPE_ITEM;

        if (headViewSize == 1 && position == 0) {
            type = TYPE_HEADER;
        } else if (footViewSize == 1 && position == getItemCount() - 1) {
            //最后一个位置
            type = TYPE_FOOT;
        }
        return type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case TYPE_HEADER:
                view = headView;
                break;

            case TYPE_ITEM:
                view = View.inflate(parent.getContext(), R.layout.recommend_item, null);
                break;

            case TYPE_FOOT:
                view = footView;
                break;
        }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_HEADER) {
            holder.mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            //设置图片加载器，图片加载器在下方
            holder.mBanner.setImageLoader(new MyLoader());
            //设置图片网址或地址的集合
            holder.mBanner.setImages(list_path);
            //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
            holder.mBanner.setBannerAnimation(Transformer.Default);
            //设置轮播图的标题集合
            holder.mBanner.setBannerTitles(list_title);
            //设置轮播间隔时间
            holder.mBanner.setDelayTime(3000);
            //设置是否为自动轮播，默认是“是”。
            holder.mBanner.isAutoPlay(true);
            //设置指示器的位置，小点点，左中右。
            holder.mBanner.setIndicatorGravity(BannerConfig.CENTER)
                    //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面
                    //必须最后调用的方法，启动轮播图。
                    .start();
        }
        if (viewType == TYPE_ITEM){
            holder.textView.setText(mBirdsList.get(position).getName());
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.show(mContext, mBirdsList.get(position).getName());
                }
            });
            Glide.with(mContext).load(mBirdsList.get(position).getImageUrl()).into(holder.imageView);
        }
        if (viewType == TYPE_FOOT){
            return;
        }
    }

    @Override
    public int getItemCount() {
        return  mBirdsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public Banner mBanner;
        public ImageView imageView;
        public TextView textView;


        public MyViewHolder(View itemView) {
            super(itemView);
            mBanner = (Banner) itemView.findViewById(R.id.banner);
            imageView = (ImageView) itemView.findViewById(R.id.bird_image);
            textView = (TextView) itemView.findViewById(R.id.bird_name);
        }
    }

}
