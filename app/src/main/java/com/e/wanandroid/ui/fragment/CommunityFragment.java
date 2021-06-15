package com.e.wanandroid.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.wanandroid.R;
import com.e.wanandroid.base.BaseFragment;
import com.e.wanandroid.databinding.FragmentCommunityBinding;
import com.e.wanandroid.viewmodel.CommunityViewModel;


public class CommunityFragment extends BaseFragment<FragmentCommunityBinding, CommunityViewModel> {

  @Override
  protected int getLayoutResId() {
    return R.layout.fragment_community;
  }

  @Override
  protected void initAndBindViewModel() {
   mViewModel = new ViewModelProvider(this).get(CommunityViewModel.class);
  }

  @Override
  protected void init() {

  }
}