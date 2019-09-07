package com.littlegreens.wanandroidmvp.modules.home.activity

import android.util.Log
import com.littlegreens.baselibary.base.BaseMvpActivity
import com.littlegreens.baselibary.commonutil.ToastUitl
import com.littlegreens.baselibary.commonwidget.LoadingDialog
import com.littlegreens.wanandroidmvp.R
import com.littlegreens.wanandroidmvp.bean.WXarticle
import com.littlegreens.wanandroidmvp.bean.WxArticle
import com.littlegreens.wanandroidmvp.modules.home.contract.HomeContract
import com.littlegreens.wanandroidmvp.modules.home.model.HomeModel
import com.littlegreens.wanandroidmvp.modules.home.presenter.HomePresenter
import kotlinx.android.synthetic.main.activity_main.*


class HomeActivity : BaseMvpActivity<HomePresenter, HomeModel>(), HomeContract.View {

    override fun initLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

    }

    override fun initEvent() {
        bt_request!!.setOnClickListener { mPresenter.getArticleRequest() }
    }

    override fun returnArticle(wXarticle: WxArticle) {
        ToastUitl.showShort(wXarticle.toString())
        tv.text = wXarticle.toString()
    }

    override fun stopLoading() {
        Log.e(TAG, "stopLoading")
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String) {
        Log.e(TAG, "msg:$msg")
    }

    override fun showLoading(msg: String) {
        Log.e(TAG, "showLoading")
        LoadingDialog.showDialogForLoading(this,msg,true)
    }

    companion object {
        private val TAG = "HomeActivity"
    }
}
