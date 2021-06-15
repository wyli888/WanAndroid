package com.e.wanandroid.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.wanandroid.R;
import com.e.wanandroid.base.BaseFragment;
import com.e.wanandroid.databinding.FragmentSquareBinding;
import com.e.wanandroid.viewmodel.SquareViewModel;


public class SquareFragment extends BaseFragment<FragmentSquareBinding, SquareViewModel> {


  @Override
  protected int getLayoutResId() {
    return R.layout.fragment_square;
  }

  @Override
  protected void initAndBindViewModel() {
   mViewModel = new ViewModelProvider(this).get(SquareViewModel.class);
  }

  @Override
  protected void init() {

  }
}