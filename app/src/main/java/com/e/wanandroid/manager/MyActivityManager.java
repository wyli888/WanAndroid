package com.e.wanandroid.manager;

import android.app.Activity;

import java.lang.ref.WeakReference;

/**
 * activity 管理类
 */
public class MyActivityManager {

  private static final MyActivityManager sInstance = new MyActivityManager();
  /**
   * 弱引用当前Activity
   */
  private WeakReference<Activity> sCurrentActivityWeakRef;


  private MyActivityManager() {

  }

  public static MyActivityManager getInstance() {
    return sInstance;
  }

  /**
   * 获取当前的Activity
   *
   * @return
   */
  public Activity getCurrentActivity() {
    Activity currentActivity = null;
    if (sCurrentActivityWeakRef != null) {
      currentActivity = sCurrentActivityWeakRef.get();
    }
    return currentActivity;
  }

  /**
   * 保存Activity
   *
   * @param activity
   */
  public void setCurrentActivity(Activity activity) {
    sCurrentActivityWeakRef = new WeakReference<Activity>(activity);
  }
}
