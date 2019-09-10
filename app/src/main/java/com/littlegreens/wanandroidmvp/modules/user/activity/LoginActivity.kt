package com.littlegreens.wanandroidmvp.modules.user.activity

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

//        loginFragment = findFragment(LoginFragment::class.java)
        loginFragment = supportFragmentManager.findFragmentByTag("LoginFragment") as LoginFragment?
        if (loginFragment == null) {
            loginFragment = LoginFragment()
            registerFragment = RegisterFragment()

            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.fl_container, loginFragment!!, "LoginFragment")
            fragmentTransaction.add(R.id.fl_container, registerFragment!!, "RegisterFragment")
            fragmentTransaction.show(loginFragment!!)
            fragmentTransaction .hide(registerFragment!!)
            fragmentTransaction.commit()
//            loadMultipleRootFragment(R.id.fl_container, 0, loginFragment, registerFragment)
        }

    }

    public fun switch(showPosition: Int) {
        LogUtil.d("switch:"+showPosition)
        if (showPosition == 0) {
//            showHideFragment(loginFragment)
            supportFragmentManager.beginTransaction().show(loginFragment!!).hide(registerFragment!!).commit()
        } else {
//            showHideFragment(registerFragment)
            supportFragmentManager.beginTransaction().show(registerFragment!!).hide(loginFragment!!).commit()
        }
    }

    override fun initEvent() {

    }


}