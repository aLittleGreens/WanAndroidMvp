package com.littlegreens.wanandroidmvp.modules.user.fragment

import android.view.View
import com.littlegreens.baselibary.base.BaseMvpFragment
import com.littlegreens.baselibary.commonutil.ToastUitl
import com.littlegreens.wanandroidmvp.R
import com.littlegreens.wanandroidmvp.modules.user.activity.LoginActivity
import com.littlegreens.wanandroidmvp.modules.user.contract.RegisterContract
import com.littlegreens.wanandroidmvp.modules.user.model.RegisterModel
import com.littlegreens.wanandroidmvp.modules.user.present.RegisterPresent
import kotlinx.android.synthetic.main.register_layout.*

/**
 *
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/9/10 14:04
 */
class RegisterFragment : BaseMvpFragment<RegisterPresent, RegisterModel>(), RegisterContract.View {
    override fun loadData() {
    }

    override val layoutResource: Int = R.layout.register_layout

    override fun initView(view: View?) {
    }

    override fun initEvent() {
        go_login.setOnClickListener {
            (activity as LoginActivity).switch(0)
        }

        sv_register.setOnClickListener {
            mPresenter.register(piv_register_account.text, piv_register_password.text, re_register_password.text)
        }
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

    override fun registerSuccess() {
        ToastUitl.showShort("注冊成功")
    }


}