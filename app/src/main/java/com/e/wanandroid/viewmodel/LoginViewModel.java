package com.e.wanandroid.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.e.wanandroid.base.BaseViewModel;
import com.hjq.toast.ToastUtils;


public class LoginViewModel extends BaseViewModel {

  public MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);




  public void onServerLoginClick(){

    ToastUtils.show("hahahah");

  }


}
