package com.lish.base.klaus.base.mvp

import com.lish.base.klaus.rx.RxManager

/**
 * Created by littleGreens on 2019/5/7.
 */
abstract class BasePresenter<V1 : IView, M1 : BaseModel> {

    lateinit var mView: V1
    lateinit var mModel: M1

    protected var mRxManager = RxManager()

    fun <V : IView, M : BaseModel> bindVM(v: V, m: M) {
        this.mView = (v as? V1)!!
        this.mModel = (m as? M1)!!
        onAttach()
    }

    open fun onAttach() {

    }

    fun onDestroy() {
        mRxManager.clear()
    }
}