package com.e.wanandroid.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatDelegate;
import com.e.wanandroid.R;
import com.e.wanandroid.manager.MyActivityManager;
import com.e.wanandroid.utils.SharePreferenceUtil;
import com.hjq.toast.ToastUtils;
import com.scwang.smart.refresh.footer.ClassicsFooter;

import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;


// 当您的应用及其引用的库包含的方法数超过 65536 时，您会遇到一个构建错误，指明您的应用已达到 Android 构建架构规定的引用限制
// 如果您的 minSdkVersion 设为 21 或更高版本，系统会默认启用 MultiDex，并且您不需要 MultiDex 库。
// 如果小于 21 可以添加依赖 implementation "androidx.multidex:multidex:2.0.1"
// 并这样继承就行了 BaseApplication extends MultiDexApplication


public class BaseApplication extends Application {

  // 并不会造成内存泄漏，因为这是一个Application Context，如果你执有的是一个静态的Activity Context，那就会内存泄漏的。
  // Android Studio并没有智能地判断出来这是Application Context，所以这是一个误报，并没有任何影响，如果你看着觉得不舒服，
  // 可以在字段上加个这个注解 @SuppressLint("StaticFieldLeak")

  @SuppressLint("StaticFieldLeak")
  private static Context context;

  public static Context getContext() {
    return context;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    context = this;
    init();
    initActivityManager();
    setCurrentTheme();
  }

  private void init() {
    // 在 Application 中初始化 toast
    ToastUtils.init(this);
  }

  private void setCurrentTheme(){
    //根据app上次退出的状态来判断是否需要设置夜间模式,提前在SharedPreference中存了一个是否是夜间模式的boolean值
    boolean isNightMode = SharePreferenceUtil.getBoolean("is_set_night_theme", false);
    if (isNightMode) {
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }else {
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
  }

  /**
   * 管理Activity
   */
  private void initActivityManager() {
    registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
      @Override
      public void onActivityCreated(@NonNull Activity activity, Bundle savedInstanceState) {
      }

      @Override
      public void onActivityStarted(@NonNull Activity activity) {
      }

      @Override
      public void onActivityResumed(@NonNull Activity activity) {
        MyActivityManager.getInstance().setCurrentActivity(activity);
      }

      @Override
      public void onActivityPaused(@NonNull Activity activity) {
      }

      @Override
      public void onActivityStopped(@NonNull Activity activity) {
      }

      @Override
      public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
      }

      @Override
      public void onActivityDestroyed(@NonNull Activity activity) {
      }
    });
  }

  /*
   * 设置全局的上拉加载和下拉刷新的样式, static 代码段可以防止内存泄露
   */
  static {
    //设置全局的Header构建器
    SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
      layout.setPrimaryColorsId(R.color.colorStatusBarBg, R.color.colorWhite);//全局设置主题颜色
      return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
    });
    //设置全局的Footer构建器
    SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
      //指定为经典Footer，默认是 BallPulseFooter
      return new ClassicsFooter(context).setDrawableSize(20);
    });
  }


  static {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
  }


}
