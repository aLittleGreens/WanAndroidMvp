package com.littlegreens.wanandroidmvp.modules.home.presenter

import com.littlegreens.wanandroidmvp.modules.home.contract.HomeContract

/**
 * Created by caiyuk on 2019/1/30.
 */

class HomePresenter : HomeContract.Presenter() {

    override fun getArticleRequest() {
        mRxManager.add(mModel.article.subscribe({ wXarticle -> mView?.returnArticle(wXarticle) }, { throwable ->
            mView?.showErrorTip("throwable:" + throwable.localizedMessage)
            mView?.stopLoading()
        }, { mView?.stopLoading() }, { mView?.showLoading("success") }))
    }
}
