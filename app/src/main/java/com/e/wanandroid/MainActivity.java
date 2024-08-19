package com.e.wanandroid;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.e.wanandroid.base.BaseActivity;
import com.e.wanandroid.databinding.ActivityMainBinding;
import com.e.wanandroid.manager.MyActivityManager;
import com.e.wanandroid.ui.login.LoginActivity;
import com.e.wanandroid.utils.SharePreferenceUtil;
import com.e.wanandroid.utils.ThemeManager;
import com.e.wanandroid.viewmodel.MainViewModel;
import com.hjq.toast.ToastUtils;
import com.suke.widget.SwitchButton;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.activity_main;
  }

  @Override
  protected void initAndBindViewModel() {
    viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    binding.setViewModel(viewModel);
  }

  @Override
  protected void init() {
    initFragment();
    initUserDada();
  }

  private void initFragment() {
    NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
    assert navHostFragment != null;
    NavController navController = navHostFragment.getNavController();
    NavigationUI.setupWithNavController(binding.navViewBottom, navController);
  }

  // 初始化侧边栏点击事件
  private void initUserDada() {
    SwitchButton switchButton = binding.navView.getHeaderView(0).findViewById(R.id.switch_button);
    int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
    switchButton.setChecked(mode == Configuration.UI_MODE_NIGHT_YES);
    switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(SwitchButton view, boolean isChecked) {
        if (isChecked) {
          AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
          SharePreferenceUtil.setParam("is_set_night_theme",true);
        } else {
          AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
          SharePreferenceUtil.setParam("is_set_night_theme",false);
        }
      }
    });
   binding.navView.getHeaderView(0).findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
       ToastUtils.show("hahah");
       Intent home = new Intent(getApplicationContext(), LoginActivity.class);
       startActivity(home);
     }
   });
   binding.navView.getMenu().findItem(R.id.nav_home).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
     @Override
     public boolean onMenuItemClick(MenuItem item) {

       ToastUtils.show("nihao");
       return false;
     }
   });
  }
  // 在AndroidManifest.xml 中加入 android:configChanges="uiMode" 当主题改变是不会重启Activity
  // AppCompatDelegate.setDefaultNightMode()这个方法会调用recreate()方法重启当前的activity 但是会有闪屏
  // 当主题改变时 下面这个方法会走 在这个方法里我们手动重启Activity并加入动画来解决闪屏的问题
  @Override
  public void onConfigurationChanged(@NonNull Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    restartActivity();
  }

  private void restartActivity() {
    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
    startActivity(intent);
    finish();
    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
  }

  @Override
  protected boolean isSupportLoad() {
    return true;
  }



}