package com.littlegreens.wanandroidmvp.modules.home.presenter

import com.littlegreens.baselibary.commonutil.LogUtil
import com.littlegreens.wanandroidmvp.api.observer.ApiException
import com.littlegreens.wanandroidmvp.api.observer.ObserverImpl
import com.littlegreens.wanandroidmvp.bean.WxArticle
import com.littlegreens.wanandroidmvp.modules.home.contract.HomeContract

/**
 * Created by caiyuk on 2019/1/30.
 */

class HomePresenter : HomeContract.Presenter() {

    override fun getArticleRequest() {
        mModel.article.subscribe(object : ObserverImpl<WxArticle>(mRxManager) {
            override fun finish() {
                LogUtil.d("finish:")
                mView?.stopLoading()
            }

            override fun start() {
                mView?.showLoading("loading...")
            }

            override fun onSuccess(bean: WxArticle) {
                LogUtil.d("onSuccess: $bean")
                mView?.returnArticle(bean)
            }

            override fun onFail(error: ApiException) {
                LogUtil.d("onFail: $error")
                mView?.showErrorTip("throwable:" + error.localizedMessage)
            }
        })
    }
}
