package com.lish.base.klaus.base.mvp

import com.lish.base.klaus.base.mvp.IView

/**
 * Created by littleGreens on 2019/5/7.
 */
interface BaseView : IView {
    fun showLoading(msg:String)
    fun stopLoading()
    fun showErrorTip(msg: String)
}