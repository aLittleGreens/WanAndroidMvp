package com.littlegreens.wanandroidmvp.modules.main.fragment.home

import android.os.Handler
import android.view.View
import com.billy.android.loading.Gloading
import com.littlegreens.baselibary.base.BaseMvpFragment
import com.littlegreens.baselibary.commonutil.LogUtil
import com.littlegreens.wanandroidmvp.R
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.contract.HomeContract
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.model.HomeModel
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.presenter.HomePresenter
import kotlinx.android.synthetic.main.fragment_home_layout.*

/**
 * @author LittleGreens [Contact me.](mailto:alittlegreens@foxmail.com)
 * @version 1.0
 * @since 2019/11/7 18:00
 */
class HomeFragment : BaseMvpFragment<HomePresenter, HomeModel>(), HomeContract.View {

    companion object {
        fun create(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun showLoading(msg: String) {
    }

    override fun showErrorTip(msg: String) {
    }

    override fun stopLoading() {
    }

    override val layoutResource: Int = R.layout.fragment_home_layout

    override fun initView(view: View?) {
        LogUtil.d("recycleView :$recycleView")
        loadImage()
    }

    override fun initEvent() {

    }

    override fun initLoadingStatusViewHolder(): Gloading.Holder? {
        return Gloading.getDefault().wrap(recycleView).withRetry(Runnable {
            loadImage()
        })
    }

    private fun loadImage() {
        showLoading()
        LogUtil.d("showLoading")
        Handler().postDelayed({
            LogUtil.d("showLoadFailed")
            recycleView.setText("success")
            showLoadSuccess()
        }, 5000)
    }


}
