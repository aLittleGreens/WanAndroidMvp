package com.littlegreens.baselibary.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lish.base.klaus.rx.RxManager
import com.littlegreens.baselibary.base.mvp.BaseModel
import com.littlegreens.baselibary.base.mvp.BasePresenter
import com.littlegreens.baselibary.base.mvp.IView
import com.littlegreens.baselibary.commonutil.TUtil

/**
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/9/5 17:39
 */
abstract class BaseMvpFragment<P : BasePresenter<*, *>, M : BaseModel> : BaseFragment(), IView {

    lateinit var mRxManager: RxManager
    lateinit var mPresenter: P
    lateinit var mModel: M

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater,container,savedInstanceState)
        mRxManager = RxManager()
        mPresenter = TUtil.getT(this, 0)!!
        mModel = TUtil.getT(this, 1)!!
        mPresenter.bindVM(this, mModel)
        return rootView
    }


    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.onDestroy()
        mRxManager.clear()
    }

    override fun getContext(): Context? {
        return super.getContext()
    }
}
