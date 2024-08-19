package com.e.wanandroid.viewmodel;

import android.view.View;

import com.e.wanandroid.base.BaseViewModel;
import com.e.wanandroid.utils.LogUtils;
import com.hjq.toast.ToastUtils;

public class PublicViewModel extends BaseViewModel {






  //ViewModel:
  public void click(View view){
    LogUtils.i("dddd","hhhhhh");
    ToastUtils.show("hhhhhh");
  }


}
