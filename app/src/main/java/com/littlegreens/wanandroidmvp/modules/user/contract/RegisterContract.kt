package com.littlegreens.wanandroidmvp.modules.user.contract

import com.littlegreens.baselibary.base.mvp.BaseModel
import com.littlegreens.baselibary.base.mvp.BasePresenter
import com.littlegreens.baselibary.base.mvp.BaseView
import com.littlegreens.wanandroidmvp.bean.RegisterBean
import io.reactivex.Observable

/**
 *
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/9/10 11:52
 */
interface RegisterContract {

    interface View : BaseView {
        fun registerSuccess()
    }

    interface Model : BaseModel {
        fun register(username: String, password: String, repassword: String): Observable<RegisterBean>
    }

    abstract class Present : BasePresenter<View, Model>() {
        abstract fun register(username: String, password: String, repassword: String)
    }

}