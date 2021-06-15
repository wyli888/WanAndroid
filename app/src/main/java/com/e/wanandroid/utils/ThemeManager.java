package com.e.wanandroid.utils;

import android.content.Context;
import android.content.res.Resources;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ThemeManager {
  // 預設是日間模式
  private static ThemeMode mThemeMode = ThemeMode.DAY;
  // 主題模式監聽器
  private static List<OnThemeChangeListener> mThemeChangeListenerList = new LinkedList<>();
  // 夜間資源的快取，key : 資源型別, 值<key:資源名稱, value:int值>
  private static HashMap<String, HashMap<String, Integer>> sCachedNightResrouces = new HashMap<>();
  // 夜間模式資源的字尾，比如日件模式資源名為：R.color.activity_bg, 那麼夜間模式就為 ：R.color.activity_bg_night
  private static final String RESOURCE_SUFFIX = "_night";
  /**
   * 主題模式，分為日間模式和夜間模式
   */
  public enum ThemeMode {
    DAY, NIGHT
  }
  /**
   * 設定主題模式
   *
   * @param themeMode
   */
  public static void setThemeMode(ThemeMode themeMode) {
    if (mThemeMode != themeMode) {
      mThemeMode = themeMode;
      if (mThemeChangeListenerList.size() > 0) {
        for (OnThemeChangeListener listener : mThemeChangeListenerList) {
          listener.onThemeChanged();
        }
      }
    }
  }
  /**
   * 根據傳入的日間模式的resId得到相應主題的resId，注意：必須是日間模式的resId
   *
   * @param dayResId 日間模式的resId
   * @return 相應主題的resId，若為日間模式，則得到dayResId；反之夜間模式得到nightResId
   */
  public static int getCurrentThemeRes(Context context, int dayResId) {
    if (getThemeMode() == ThemeMode.DAY) {
      return dayResId;
    }
// 資源名
    String entryName = context.getResources().getResourceEntryName(dayResId);
// 資源型別
    String typeName = context.getResources().getResourceTypeName(dayResId);
    HashMap<String, Integer> cachedRes = sCachedNightResrouces.get(typeName);
// 先從快取中去取，如果有直接返回該id
    if (cachedRes == null) {
      cachedRes = new HashMap<>();
    }
    Integer resId = cachedRes.get(entryName + RESOURCE_SUFFIX);
    if (resId != null && resId != 0) {
      return resId;
    } else {
//如果快取中沒有再根據資源id去動態獲取
      try {
// 通過資源名，資源型別，包名得到資源int值
        int nightResId = context.getResources().getIdentifier(entryName  + RESOURCE_SUFFIX, typeName, context.getPackageName());
// 放入快取中
        cachedRes.put(entryName  + RESOURCE_SUFFIX, nightResId);
        sCachedNightResrouces.put(typeName, cachedRes);
        return nightResId;
      } catch (Resources.NotFoundException e) {
        e.printStackTrace();
      }
    }
    return 0;
  }
  /**
   * 註冊ThemeChangeListener
   *
   * @param listener
   */
  public static void registerThemeChangeListener(OnThemeChangeListener listener) {
    if (!mThemeChangeListenerList.contains(listener)) {
      mThemeChangeListenerList.add(listener);
    }
  }
  /**
   * 反註冊ThemeChangeListener
   *
   * @param listener
   */
  public static void unregisterThemeChangeListener(OnThemeChangeListener listener) {
    if (mThemeChangeListenerList.contains(listener)) {
      mThemeChangeListenerList.remove(listener);
    }
  }
  /**
   * 得到主題模式
   *
   * @return
   */
  public static ThemeMode getThemeMode() {
    return mThemeMode;
  }
  /**
   * 主題模式切換監聽器
   */
  public interface OnThemeChangeListener {
    /**
     * 主題切換時回撥
     */
    void onThemeChanged();
  }
}

