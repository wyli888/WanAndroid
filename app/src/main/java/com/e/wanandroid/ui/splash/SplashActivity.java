package com.e.wanandroid.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.e.wanandroid.MainActivity;
import com.e.wanandroid.R;
import com.e.wanandroid.base.BaseActivity;
import com.e.wanandroid.databinding.ActivitySplashBinding;
import com.e.wanandroid.utils.LogUtils;
import com.e.wanandroid.utils.SharePreferenceUtil;
import com.e.wanandroid.viewmodel.SplashViewModel;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> {
  private final String TAG = "SplashActivity==";
  private OnBoardAdapter onboardAdapter;
  private static final int SPLASH_TIME_OUT = 1000;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    LogUtils.i(TAG, "onCreate");
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.activity_splash;
  }

  @Override
  protected void initAndBindViewModel() {
    viewModel = new ViewModelProvider(this).get(SplashViewModel.class);
    binding.setViewModel(viewModel);
  }

  @Override
  protected void init() {
    if (SharePreferenceUtil.getBoolean("is_already_show_view_pager", false)) {
      binding.viewPager.setVisibility(View.GONE);
      toFinish();
    } else {
      binding.viewPager.setVisibility(View.VISIBLE);
      binding.animationView.setVisibility(View.GONE);
      setItemData();
      binding.viewPager.setAdapter(onboardAdapter);
      setIndicator();
      setCurrentIndicator(0);
      binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
          super.onPageSelected(position);
          setCurrentIndicator(position);
          if (position == 2) {
            binding.materialButton.setVisibility(View.VISIBLE);
            binding.linearIndicator.setVisibility(View.GONE);
            binding.materialButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                SharePreferenceUtil.setParam("is_already_show_view_pager", true);
                startNow();
              }
            });
          } else {
            binding.materialButton.setVisibility(View.GONE);
            binding.linearIndicator.setVisibility(View.VISIBLE);
          }
        }
      });
    }
  }


  private void toFinish() {
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent home = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(home);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
      }
    }, SPLASH_TIME_OUT);
  }

  private void startNow() {
    Intent home = new Intent(getApplicationContext(), MainActivity.class);
    startActivity(home);
    finish();
    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
  }

  // 设置初次安装时的数据源
  private void setItemData() {
    List<OnboardItem> onBoardItems = new ArrayList<>();
    OnboardItem startOne = new OnboardItem();
    startOne.setTitle("Hello Welcome");
    startOne.setImage(R.drawable.start_one);

    OnboardItem startTwo = new OnboardItem();
    startTwo.setTitle("How Are You!");
    startTwo.setImage(R.drawable.start_two);

    OnboardItem startThree = new OnboardItem();
    startThree.setTitle("Ok Let's Start!");
    startThree.setImage(R.drawable.start_three);

    onBoardItems.add(startOne);
    onBoardItems.add(startTwo);
    onBoardItems.add(startThree);

    onboardAdapter = new OnBoardAdapter(onBoardItems);
  }

  private void setIndicator() {
    ImageView[] indicators = new ImageView[onboardAdapter.getItemCount()];
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    layoutParams.setMargins(0, 0, 8, 0);
    for (int i = 0; i < indicators.length; i++) {
      indicators[i] = new ImageView(getApplicationContext());
      indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive));
      indicators[i].setLayoutParams(layoutParams);
      binding.linearIndicator.addView(indicators[i]);
    }
  }

  private void setCurrentIndicator(int index) {
    int childCount = binding.linearIndicator.getChildCount();
    for (int i = 0; i < childCount; i++) {
      ImageView imageView = (ImageView) binding.linearIndicator.getChildAt(i);
      if (i == index) {
        imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active));
      } else {
        imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive));
      }
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    LogUtils.i(TAG, "destroy");
  }
}