package com.littlegreens.wanandroidmvp.modules.main.fragment.home.presenter

import com.littlegreens.wanandroidmvp.api.observer.ApiException
import com.littlegreens.wanandroidmvp.api.observer.ObserverImpl
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.contract.HomeContract
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.model.bean.ArticleBeanTopList
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.model.bean.BannerBeanList
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.model.bean.HomeArticalBeanList

/**
 *
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/11/11 15:35
 */
class HomePresenter : HomeContract.Presenter() {
    override fun getBanner() {
        mModel.getBanner().subscribe(object : ObserverImpl<BannerBeanList>(mRxManager) {
            override fun onSuccess(bean: BannerBeanList) {
                mView?.getBannerSuccess(bean.errorCode, bean.data!!)
            }

            override fun onFail(error: ApiException) {
                mView?.getBannerFail(error.errorCode,error.showMessage)
            }

            override fun finish() {
            }

            override fun start() {
            }

        })
    }

    override fun getArticleList(page: Int) {
        mModel.getArticleList(page).subscribe(object :ObserverImpl<HomeArticalBeanList>(mRxManager){
            override fun onSuccess(bean: HomeArticalBeanList) {
                mView?.getArticleListSuccess(bean.errorCode,bean.data!!)
            }

            override fun onFail(error: ApiException) {
                mView?.getArticleListFailed(error.errorCode,error.showMessage)
            }

            override fun finish() {
            }

            override fun start() {
            }

        })
    }

    override fun getTopArticleList() {
        mModel.getTopArticleList().subscribe(object : ObserverImpl<ArticleBeanTopList>(mRxManager){
            override fun onSuccess(bean: ArticleBeanTopList) {
                mView?.getTopArticleListSuccess(bean.errorCode,bean.data!!)
            }

            override fun onFail(error: ApiException) {
                mView?.getTopArticleListFailed(error.errorCode,error.showMessage)
            }

            override fun finish() {
            }

            override fun start() {
            }

        })
    }

}