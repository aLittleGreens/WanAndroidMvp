package com.littlegreens.wanandroidmvp.modules.user.activity

import android.view.WindowManager
import com.littlegreens.baselibary.base.BaseActivity
import com.littlegreens.baselibary.commonutil.LogUtil
import com.littlegreens.wanandroidmvp.R
import com.littlegreens.wanandroidmvp.modules.user.fragment.LoginFragment
import com.littlegreens.wanandroidmvp.modules.user.fragment.RegisterFragment

/**
 *
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/9/9 16:50
 */
class LoginActivity : BaseActivity() {

    var loginFragment: LoginFragment? = null
    var registerFragment: RegisterFragment? = null
    override fun initLayoutId(): Int {
        return R.layout.login_activity_layout
    }

    override fun initView() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
        loginFragment = findFragment(LoginFragment::class.java)
        if (loginFragment == null) {
            loginFragment = LoginFragment()
            registerFragment = RegisterFragment()
            loadMultipleRootFragment(R.id.fl_container, 0, loginFragment, registerFragment)
        }

    }

    public fun switch(showPosition: Int) {
        LogUtil.d("switch:" + showPosition)
        if (showPosition == 0) {
            showHideFragment(loginFragment)
        } else {
            showHideFragment(registerFragment)
        }
    }

    override fun initEvent() {

    }


}