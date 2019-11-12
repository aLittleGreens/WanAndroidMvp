package com.littlegreens.baselibary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.billy.android.loading.Gloading
import com.littlegreens.baselibary.base.support.MySupportFragment
import com.littlegreens.baselibary.commonwidget.LoadingDialog

/**
 *
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/11/11 15:12
 */
abstract class BaseFragment : MySupportFragment() {

    protected var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            rootView = inflater.inflate(layoutResource, container, false)
        }
        return rootView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(rootView)//Kotlin 在onCreateView用导入布局获取id，空指针，在onActivityCreated。调用可解决
        initEvent()
    }

    //获取布局文件
    protected abstract val layoutResource: Int

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

    /*************************** GLoading *****************************/

    protected var mHolder: Gloading.Holder? = null

    /**
     * make a Gloading.Holder wrap with current activity by default
     * override this method in subclass to do special initialization
     */
    private fun initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            //bind status view to activity root view by default
            mHolder = initLoadingStatusViewHolder()
        }
    }

    protected open fun initLoadingStatusViewHolder(): Gloading.Holder? {
        throw NullPointerException("you must overvide this method")
    }


    fun showLoading() {
        initLoadingStatusViewIfNeed()
        mHolder!!.showLoading()
    }

    fun showLoadSuccess() {
        initLoadingStatusViewIfNeed()
        mHolder!!.showLoadSuccess()
    }

    fun showLoadFailed() {
        initLoadingStatusViewIfNeed()
        mHolder!!.showLoadFailed()
    }

    fun showEmpty() {
        initLoadingStatusViewIfNeed()
        mHolder!!.showEmpty()
    }
}