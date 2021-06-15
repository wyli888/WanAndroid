package com.e.wanandroid.ui.fragment;

import androidx.lifecycle.ViewModelProvider;

import com.e.wanandroid.R;
import com.e.wanandroid.base.BaseFragment;
import com.e.wanandroid.databinding.FragmentPublicBinding;
import com.e.wanandroid.viewmodel.PublicViewModel;

public class PublicFragment extends BaseFragment<FragmentPublicBinding, PublicViewModel> {


  @Override
  protected int getLayoutResId() {
    return R.layout.fragment_public;
  }

  @Override
  protected void initAndBindViewModel() {
   mViewModel = new ViewModelProvider(this).get(PublicViewModel.class);
  }

  @Override
  protected void init() {

  }

  @Override
  protected boolean isSupportLoad() {
    return true;
  }
}