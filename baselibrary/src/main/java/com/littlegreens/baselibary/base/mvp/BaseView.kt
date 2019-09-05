package com.littlegreens.baselibary.base.mvp

/**
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/9/5 17:39
 */
interface BaseView : IView {
    fun showLoading(msg:String)
    fun stopLoading()
    fun showErrorTip(msg: String)
}