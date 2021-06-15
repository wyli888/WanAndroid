package com.e.wanandroid.adapter;


import android.app.Application;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.wanandroid.R;
import com.e.wanandroid.base.BaseApplication;
import com.e.wanandroid.model.HomeBanner;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class ImageAdapter extends BannerAdapter<HomeBanner, ImageAdapter.BannerViewHolder> {


  public ImageAdapter(List<HomeBanner> mDatas) {
    //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
    super(mDatas);
  }

  //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
  @Override
  public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
    ImageView imageView = new ImageView(parent.getContext());
    //注意，必须设置为match_parent，这个是viewpager2强制要求的
    imageView.setLayoutParams(new ViewGroup.LayoutParams(
      ViewGroup.LayoutParams.MATCH_PARENT,
      ViewGroup.LayoutParams.MATCH_PARENT));
    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    return new BannerViewHolder(imageView);
  }

  @Override
  public void onBindView(BannerViewHolder holder, HomeBanner data, int position, int size) {
    //holder.imageView.setImageResource(data.getImagePath());
    Glide.with(BaseApplication.getContext())
      .load(data.getImagePath())
      .placeholder(R.drawable.loading_image_view)
      .into(holder.imageView);
  }

  static class BannerViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;

    public BannerViewHolder(@NonNull ImageView view) {
      super(view);
      this.imageView = view;
    }
  }
}
