package com.app.bookstoremanager.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.bookstoremanager.R;
import com.app.bookstoremanager.bean.RecommendInfo;
import com.app.bookstoremanager.utils.ToastUtil;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.MyViewHolder> {

    public Context mContext;
    public ArrayList<RecommendInfo> mBirdsList;

    public RecommendAdapter(Context mContext, ArrayList<RecommendInfo> mBirdsList) {
        this.mContext = mContext;
        this.mBirdsList = mBirdsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.textView.setText(mBirdsList.get(position).getName());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.show(mContext, mBirdsList.get(position).getName());
            }
        });
        Glide.with(mContext).load(mBirdsList.get(position).getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mBirdsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            //适配器构造时只会用到实体类的get方法，用以获取相应的属性
            imageView = (ImageView) itemView.findViewById(R.id.bird_image);
            textView = (TextView) itemView.findViewById(R.id.bird_name);
        }
    }

}
