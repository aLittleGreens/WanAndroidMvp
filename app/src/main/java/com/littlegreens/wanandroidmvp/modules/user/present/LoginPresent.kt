package com.littlegreens.wanandroidmvp.modules.user.present

import com.littlegreens.baselibary.commonutil.LogUtil
import com.littlegreens.wanandroidmvp.api.observer.ApiException
import com.littlegreens.wanandroidmvp.api.observer.ObserverImpl
import com.littlegreens.wanandroidmvp.bean.LoginBean
import com.littlegreens.wanandroidmvp.modules.user.contract.LoginContract

/**
 *
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/9/10 13:50
 */
class LoginPresent : LoginContract.Present() {

    override fun login(username: String, password: String) {
        mModel.login(username, password).subscribe(object : ObserverImpl<LoginBean>(mRxManager) {
            override fun onSuccess(bean: LoginBean) {
                LogUtil.d("bean $bean.data code:${bean.errorCode} msg:${bean.errorMsg}")

                if (bean.isSuccess()) {
                    mView?.loginSuccess()
                } else {
                    mView?.showErrorTip(bean.errorMsg)
                }

            }

            override fun onFail(error: ApiException) {
                mView?.showErrorTip(error.showMessage)
            }

            override fun finish() {
                mView?.stopLoading()
            }

            override fun start() {
                mView?.showLoading("正在登陆中...")
            }


        })
    }

}