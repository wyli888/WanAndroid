package com.e.wanandroid.model;

import com.google.gson.annotations.SerializedName;

public class HomeBanner {

  /**
   * desc : 扔物线
   * id : 29
   * imagePath : https://wanandroid.com/blogimgs/8690f5f9-733a-476a-8ad2-2468d043c2d4.png
   * isVisible : 1
   * order : 0
   * title : Kotlin 的 Lambda，大部分人学得连皮毛都不算
   * type : 0
   * url : http://i0k.cn/5jhSp
   */
  @SerializedName("desc")
  private String desc;
  @SerializedName("id")
  private Integer id;
  @SerializedName("imagePath")
  private String imagePath;
  @SerializedName("isVisible")
  private Integer isVisible;
  @SerializedName("order")
  private Integer order;
  @SerializedName("title")
  private String title;
  @SerializedName("type")
  private Integer type;
  @SerializedName("url")
  private String url;


  public String getDesc() {
    return desc;
  }

  public Integer getId() {
    return id;
  }

  public String getImagePath() {
    return imagePath;
  }

  public Integer getIsVisible() {
    return isVisible;
  }

  public Integer getOrder() {
    return order;
  }

  public String getTitle() {
    return title;
  }

  public Integer getType() {
    return type;
  }

  public String getUrl() {
    return url;
  }
}
