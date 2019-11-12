package com.littlegreens.wanandroidmvp.modules.main.contract

import com.littlegreens.baselibary.base.mvp.BaseModel
import com.littlegreens.baselibary.base.mvp.BasePresenter
import com.littlegreens.baselibary.base.mvp.BaseView

/**
 *
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/11/11 14:57
 */
interface MainContract {

    interface View : BaseView {
        fun checkUpdateSuccess()

    }

    interface Model : BaseModel {
        fun checkUpdate()
    }

    abstract class Presenter : BasePresenter<View, Model>() {
       abstract fun checkUpdate()
    }

}