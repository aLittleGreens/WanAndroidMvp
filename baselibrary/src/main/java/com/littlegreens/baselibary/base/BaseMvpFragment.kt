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
import com.littlegreens.baselibary.base.support.MySupportFragment
import com.littlegreens.baselibary.commonutil.TUtil
import com.littlegreens.baselibary.commonwidget.LoadingDialog

/**
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/9/5 17:39
 */
abstract class BaseMvpFragment<P : BasePresenter<*, *>, M : BaseModel> : MySupportFragment(), IView {

    protected var rootView: View? = null
    lateinit var mRxManager: RxManager
    lateinit var mPresenter: P
    lateinit var mModel: M

    //获取布局文件
    protected abstract val layoutResource: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null)
            rootView = inflater.inflate(layoutResource, container, false)

        mRxManager = RxManager()
        mPresenter = TUtil.getT(this, 0)!!
        mModel = TUtil.getT(this, 1)!!
        mPresenter.bindVM(this, mModel)

        return rootView

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView(rootView)//Kotlin 在onCreateView用导入布局获取id，空指针，在onActivityCreated。调用可解决
        initEvent()
    }

    //初始化view
    protected abstract fun initView(view: View?)

    protected abstract fun initEvent()
    /**
     * 开启加载进度条
     */
    fun startProgressDialog() {
        LoadingDialog.showDialogForLoading(activity)
    }

    /**
     * 开启加载进度条
     *
     * @param msg
     */
    fun startProgressDialog(msg: String) {
        LoadingDialog.showDialogForLoading(activity, msg, true)
    }

    /**
     * 停止加载进度条
     */
    fun stopProgressDialog() {
        LoadingDialog.cancelDialogForLoading()
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
