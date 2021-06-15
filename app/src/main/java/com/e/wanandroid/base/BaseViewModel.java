package com.e.wanandroid.base;

import android.content.res.Resources;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e.wanandroid.R;
import com.e.wanandroid.config.LoadState;

public class BaseViewModel extends ViewModel implements DefaultLifecycleObserver {

  public Resources resources;

  /**
   * 加载状态
   */
  public MutableLiveData<LoadState> loadState = new MutableLiveData<>();

  public MutableLiveData<String> errorMsg = new MutableLiveData<>(getResources().getString(R.string.load_error));

  public Resources getResources() {
    if (resources == null) {
      resources = BaseApplication.getContext().getResources();
    }
    return resources;
  }


  /**
   * 是否为刷新数据
   */
  public boolean mRefresh;

  /**
   * 重新加载数据。没有网络，点击重试时回调
   */
  public void reloadData() {

  }



}
