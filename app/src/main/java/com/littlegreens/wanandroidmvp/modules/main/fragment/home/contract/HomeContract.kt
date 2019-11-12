package com.littlegreens.wanandroidmvp.modules.main.fragment.home.contract

import com.littlegreens.baselibary.base.mvp.BaseModel
import com.littlegreens.baselibary.base.mvp.BasePresenter
import com.littlegreens.baselibary.base.mvp.BaseView

/**
 *
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/11/11 15:24
 */
interface HomeContract {

    interface View : BaseView {

    }

    interface Model : BaseModel {

    }

    abstract class Presenter : BasePresenter<View, Model>() {

    }

}