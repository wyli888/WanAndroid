package com.e.wanandroid.utils;

import android.util.Log;

import com.e.wanandroid.BuildConfig;

/**
 * <pre>
 *     desc  : 日志相关工具类
 * </pre>
 */
public final class LogUtils {

  public static final int VERBOSE = 5;
  public static final int DEBUG = 4;
  public static final int INFO = 3;
  public static final int WARN = 2;
  public static final int ERROR = 1;

  /**
   * 通过改变该值来进行不同级别的Log打印
   */
  public static int LOG_LEVEL = BuildConfig.DEBUG ? 6 : 0 ;

  public static void v(String tag, String msg) {
    if (LOG_LEVEL > VERBOSE) {
      Log.v(tag, msg);
    }
  }

  public static void d(String tag, String msg) {
    if (LOG_LEVEL > DEBUG) {
      Log.d(tag, msg);
    }
  }

  public static void i(String tag, String msg) {
    if (LOG_LEVEL > INFO) {
      Log.i(tag, msg);
    }
  }

  public static void w(String tag, String msg) {
    if (LOG_LEVEL > WARN) {
      Log.w(tag, msg);
    }
  }

  public static void e(String tag, String msg) {
    if (LOG_LEVEL > ERROR) {
      Log.e(tag, msg);
    }
  }

}
