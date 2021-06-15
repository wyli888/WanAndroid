package com.e.wanandroid.response;

import com.e.wanandroid.model.HomeBanner;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeBannerResponse {

  @SerializedName("errorCode")
  private int errorCode;
  @SerializedName("errorMsg")
  private String errorMsg;
  @SerializedName("data")
  private List<HomeBanner> homeBanners;

  public int getErrorCode() {
    return errorCode;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public List<HomeBanner> getHomeBanners() {
    return homeBanners;
  }
}
