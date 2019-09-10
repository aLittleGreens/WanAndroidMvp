package com.littlegreens.wanandroidmvp.modules.user.contract

import com.littlegreens.baselibary.base.mvp.BaseModel
import com.littlegreens.baselibary.base.mvp.BasePresenter
import com.littlegreens.baselibary.base.mvp.BaseView
import com.littlegreens.wanandroidmvp.bean.LoginBean
import io.reactivex.Observable

/**
 *
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/9/10 11:52
 */
interface LoginContract {

    interface View : BaseView {
        fun loginSuccess()
    }

    interface Model : BaseModel {
        fun login(username: String, password: String): Observable<LoginBean>
    }

    abstract class Present : BasePresenter<View, Model>() {
        abstract fun login(username: String, password: String)
    }

}