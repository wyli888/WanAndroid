package com.e.wanandroid.viewmodel;


import android.annotation.SuppressLint;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.e.wanandroid.base.BaseViewModel;
import com.e.wanandroid.config.LoadState;
import com.e.wanandroid.network.ApiClient;
import com.e.wanandroid.network.ApiService;
import com.e.wanandroid.response.HomeBannerResponse;
import com.e.wanandroid.utils.LogUtils;
import com.e.wanandroid.utils.NetWorkUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeViewModel extends BaseViewModel {

  public MutableLiveData<HomeBannerResponse> bannerResponse = new MutableLiveData<>();

  public void getHomeBannerData() {
    if (NetWorkUtils.isConnected()){
      loadState.postValue(LoadState.LOADING);
      ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);
      apiService.getHomeBanner().enqueue(new Callback<HomeBannerResponse>() {
        @Override
        public void onResponse(@NonNull Call<HomeBannerResponse> call, @NonNull Response<HomeBannerResponse> response) {
          LogUtils.i("============", response.body().toString());
          if (response.body() != null){
            loadState.postValue(LoadState.SUCCESS);
            bannerResponse.postValue(response.body());
          }else {
            loadState.postValue(LoadState.NO_DATA);
          }
        }

        @Override
        public void onFailure(@NonNull Call<HomeBannerResponse> call, @NonNull Throwable t) {
          loadState.postValue(LoadState.ERROR);
        }
      });
    }else {
      loadState.postValue(LoadState.NO_NETWORK);
    }



  }

  @SuppressLint("NonConstantResourceId")
  public void handleOnclick(View view) {
    switch (view.getId()) {

    }
  }
}
