package com.littlegreens.wanandroidmvp.modules.main.fragment.home.model

import com.littlegreens.baselibary.rx.RxJavaHelper
import com.littlegreens.wanandroidmvp.api.Api
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.contract.HomeContract
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.model.bean.ArticleBeanTopList
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.model.bean.BannerBeanList
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.model.bean.HomeArticalBeanList
import io.reactivex.Observable

/**
 *
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/11/11 15:35
 */
class HomeModel : HomeContract.Model{
    override fun getBanner(): Observable<BannerBeanList> {
       return Api.getInstance().getBanner().compose(RxJavaHelper.composeIoToMain())
    }

    override fun getArticleList(page: Int): Observable<HomeArticalBeanList> {
        return Api.getInstance().getArticleList(page).compose(RxJavaHelper.composeIoToMain())
    }

    override fun getTopArticleList(): Observable<ArticleBeanTopList> {
        return Api.getInstance().getTopArticleList().compose(RxJavaHelper.composeIoToMain())
    }


}