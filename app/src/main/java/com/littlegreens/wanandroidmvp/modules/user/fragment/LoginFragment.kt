package com.littlegreens.wanandroidmvp.modules.user.fragment

import android.content.Intent
import android.view.View
import com.littlegreens.baselibary.base.BaseMvpFragment
import com.littlegreens.baselibary.commonutil.ToastUitl
import com.littlegreens.wanandroidmvp.R
import com.littlegreens.wanandroidmvp.modules.home.activity.HomeActivity
import com.littlegreens.wanandroidmvp.modules.user.activity.LoginActivity
import com.littlegreens.wanandroidmvp.modules.user.contract.LoginContract
import com.littlegreens.wanandroidmvp.modules.user.model.LoginModel
import com.littlegreens.wanandroidmvp.modules.user.present.LoginPresent
import kotlinx.android.synthetic.main.login_layout.*

/**
 * @author LittleGreens [Contact me.](mailto:alittlegreens@foxmail.com)
 * @version 1.0
 * @since 2019/9/10 10:54
 */
class LoginFragment : BaseMvpFragment<LoginPresent, LoginModel>(), LoginContract.View {
    override fun initEvent() {
        sv_login.setOnClickListener {
            mPresenter.login(piv_register_account.text, piv_register_password.text)
        }

        go_register.setOnClickListener {
            (activity as LoginActivity).switch(1)
        }
    }

    override val layoutResource: Int = R.layout.login_layout

    override fun initView(view: View?) {
    }

    override fun showLoading(msg: String) {
        startProgressDialog(msg)
    }

    override fun stopLoading() {
        stopProgressDialog()
    }

    override fun showErrorTip(msg: String) {
        ToastUitl.showShort(msg)
    }

    override fun loginSuccess() {
        ToastUitl.showShort("登录成功")
        startActivity(Intent(activity, HomeActivity::class.java))
        activity?.finish()
    }


}
