package com.e.wanandroid.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.wanandroid.R;
import com.e.wanandroid.base.BaseFragment;
import com.e.wanandroid.databinding.FragmentSortBinding;
import com.e.wanandroid.viewmodel.SortViewModel;


public class SortFragment extends BaseFragment<FragmentSortBinding, SortViewModel> {


  @Override
  protected int getLayoutResId() {
    return R.layout.fragment_sort;
  }

  @Override
  protected void initAndBindViewModel() {
    mViewModel = new ViewModelProvider(this).get(SortViewModel.class);
  }

  @Override
  protected void init() {

  }
}