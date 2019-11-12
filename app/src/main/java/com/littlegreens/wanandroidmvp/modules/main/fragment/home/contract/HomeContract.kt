package com.littlegreens.wanandroidmvp.modules.main.fragment.home.contract

import com.littlegreens.baselibary.base.mvp.BaseModel
import com.littlegreens.baselibary.base.mvp.BasePresenter
import com.littlegreens.baselibary.base.mvp.BaseView
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.model.bean.*
import io.reactivex.Observable

/**
 *
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/11/11 15:24
 */
interface HomeContract {

    interface View : BaseView {
        abstract fun getBannerSuccess(code: Int, data: List<BannerBean>)
        abstract fun getBannerFail(code: Int, msg: String)
        abstract fun getArticleListSuccess(code: Int, data: HomeArticalBean)
        abstract fun getArticleListFailed(code: Int, msg: String)
        abstract fun getTopArticleListSuccess(code: Int, data: List<ArticleBean>)
        abstract fun getTopArticleListFailed(code: Int, msg: String)
    }

    interface Model : BaseModel {
        abstract fun getBanner():Observable<BannerBeanList>
        abstract fun getArticleList(page:Int):Observable<HomeArticalBeanList>
        abstract fun getTopArticleList():Observable<ArticleBeanTopList>
    }

    abstract class Presenter : BasePresenter<View, Model>() {
        abstract fun getBanner()
        abstract fun getArticleList(page:Int)
        abstract fun getTopArticleList()
    }

}