package com.littlegreens.wanandroidmvp.modules.user.present

import com.littlegreens.baselibary.commonutil.LogUtil
import com.littlegreens.wanandroidmvp.api.observer.ApiException
import com.littlegreens.wanandroidmvp.api.observer.ObserverImpl
import com.littlegreens.wanandroidmvp.bean.RegisterBean
import com.littlegreens.wanandroidmvp.modules.user.contract.RegisterContract

/**
 *
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/9/10 13:51
 */
class RegisterPresent : RegisterContract.Present() {
    override fun register(username: String, password: String, repassword: String) {
        mModel.register(username, password, repassword).subscribe(object : ObserverImpl<RegisterBean>(mRxManager) {

            override fun onSuccess(bean: RegisterBean) {
                LogUtil.d("bean $bean.data code:${bean.errorCode} msg:${bean.errorMsg}")
                if (bean.isSuccess()) {
                    mView?.registerSuccess()
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
                mView?.showLoading("loading")
            }

        })
    }

}