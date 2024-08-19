
package com.e.wanandroid.ui.login;

import androidx.lifecycle.ViewModelProvider;

import com.e.wanandroid.R;
import com.e.wanandroid.base.BaseActivity;
import com.e.wanandroid.databinding.ActivityLoginBinding;
import com.e.wanandroid.viewmodel.LoginViewModel;


/**
 * Created by amitshekhar on 08/07/17.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    private ActivityLoginBinding mActivityLoginBinding;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initAndBindViewModel() {
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void init() {

    }
}
