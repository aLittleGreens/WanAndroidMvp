package com.littlegreens.baselibary.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.littlegreens.baselibary.R
import com.littlegreens.baselibary.commonwidget.LoadingDialog

/**
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/9/5 17:39
 */
abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doBeforeSetContentView()
        setContentView(initLayoutId())
        mContext = this
        initView()
        initEvent()
    }


    private fun doBeforeSetContentView() {
        // 无标题
        //        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 默认着色状态栏
        // SetStatusBarColor()

    }

    /*********************子类实现 */
    //获取布局文件
    protected abstract fun initLayoutId(): Int

    //初始化view
    protected abstract fun initView()

    protected abstract fun initEvent()

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.windowToken)
            }
        }

        return super.dispatchTouchEvent(ev)
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private fun isShouldHideKeyboard(v: View?, event: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            val l = intArrayOf(0, 0)
            v.getLocationInWindow(l)
            val left = l[0]
            val top = l[1]
            val bottom = top + v.height
            val right = left + v.width
            val dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.x14)
            return if (event.x > left && event.x < (right + 300)
                    && event.y > (top - dimensionPixelSize) && event.y < (bottom + dimensionPixelSize)) {
                // 点击EditText的事件，忽略它。
                false
            } else {
                true
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    private fun hideKeyboard(token: IBinder?) {
        if (token != null) {
            val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected fun SetStatusBarColor() {
//        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.main_color))
    }

    /**
     * 开启加载进度条
     */
    protected fun startProgressDialog() {
        LoadingDialog.showDialogForLoading(mContext as Activity)
    }

    /**
     * 开启加载进度条
     *
     * @param msg
     */
    protected fun startProgressDialog(msg: String) {
        LoadingDialog.showDialogForLoading(mContext as Activity, msg, true)
    }

    /**
     * 停止加载进度条
     */
    protected fun stopProgressDialog() {
        LoadingDialog.cancelDialogForLoading()
    }

}