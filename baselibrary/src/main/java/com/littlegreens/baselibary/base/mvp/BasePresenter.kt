package com.littlegreens.baselibary.base.mvp

import android.content.Context
import com.lish.base.klaus.rx.RxManager

/**
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/9/5 17:39
 */
abstract class BasePresenter<V : IView, M : BaseModel> {

    public var mView: V? = null
    protected lateinit var mModel: M

    protected var mRxManager = RxManager()

    protected var mContext: Context? = null

    fun <V1 : IView, M1 : BaseModel> bindVM(v: V1, m: M1) {
        this.mModel = (m as? M)!!
        onAttach(v)
    }

    fun <V1 : IView> onAttach(v: V1) {
        this.mView = v as V
        this.mContext = mView?.getContext()
    }

    fun onDetach() {
        this.mView = null
        this.mContext = null
    }

    fun onDestroy() {
        mRxManager.clear()
        onDetach()
    }
}