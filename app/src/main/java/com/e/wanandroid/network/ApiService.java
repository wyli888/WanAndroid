package com.e.wanandroid.network;


import com.e.wanandroid.response.HomeBannerResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

  @GET("banner/json")
  Call<HomeBannerResponse> getHomeBanner();

}
