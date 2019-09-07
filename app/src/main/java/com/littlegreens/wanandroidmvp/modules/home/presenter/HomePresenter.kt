package com.littlegreens.wanandroidmvp.modules.home.presenter

import com.littlegreens.baselibary.commonutil.LogUtil
import com.littlegreens.wanandroidmvp.api.observer.ApiException
import com.littlegreens.wanandroidmvp.api.observer.ObserverImpl
import com.littlegreens.wanandroidmvp.bean.WXarticle
import com.littlegreens.wanandroidmvp.bean.WxArticle
import com.littlegreens.wanandroidmvp.modules.home.contract.HomeContract

/**
 * Created by caiyuk on 2019/1/30.
 */

class HomePresenter : HomeContract.Presenter() {

    override fun getArticleRequest() {
//        mRxManager.add(mModel.article.subscribe({ wXarticle -> mView?.returnArticle(wXarticle) }, { throwable ->
//            mView?.showErrorTip("throwable:" + throwable.localizedMessage)
//            mView?.stopLoading()
//        }, { mView?.stopLoading() }, { mView?.showLoading("success") }))
        mView?.showLoading("loading")
        mModel.article.
            doFinally { mView?.stopLoading()}.
            subscribe(object :ObserverImpl<WxArticle>(mRxManager){
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
