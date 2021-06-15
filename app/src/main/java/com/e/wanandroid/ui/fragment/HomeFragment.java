package com.e.wanandroid.ui.fragment;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.e.wanandroid.R;
import com.e.wanandroid.adapter.ImageAdapter;
import com.e.wanandroid.base.BaseFragment;
import com.e.wanandroid.databinding.FragmentHomeBinding;
import com.e.wanandroid.response.HomeBannerResponse;
import com.e.wanandroid.viewmodel.HomeViewModel;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.transformer.RotateYTransformer;


public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

  @Override
  protected int getLayoutResId() {
    return R.layout.fragment_home;
  }

  @Override
  protected boolean isSupportLoad() {
    return true;
  }

  @Override
  protected void initAndBindViewModel() {
    mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    mDataBinding.setViewModel(mViewModel);
  }

  @Override
  protected void init() {
    initBannerData();
  }

  private void initBannerData() {
    
    // 获取首页banner数据
    mViewModel.getHomeBannerData();
    mViewModel.bannerResponse.observe(this, new Observer<HomeBannerResponse>() {

      @Override
      public void onChanged(HomeBannerResponse homeBannerResponse) {
        mDataBinding.banner.setAdapter(new ImageAdapter(homeBannerResponse.getHomeBanners()), true)
          .setPageTransformer(new RotateYTransformer())
          .setIndicator(new RectangleIndicator(getContext()));
      }
    });
  }

}