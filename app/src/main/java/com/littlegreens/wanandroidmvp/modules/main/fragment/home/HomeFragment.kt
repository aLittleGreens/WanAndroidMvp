package com.littlegreens.wanandroidmvp.modules.main.fragment.home

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.billy.android.loading.Gloading
import com.chad.library.adapter.base.BaseViewHolder
import com.littlegreens.baselibary.base.BaseMvpFragment
import com.littlegreens.baselibary.commonutil.LogUtil
import com.littlegreens.baselibary.commonutil.ToastUitl
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.adapter.HomeAdapter
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.contract.HomeContract
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.model.HomeModel
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.model.bean.ArticleBean
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.model.bean.BannerBean
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.model.bean.HomeArticalBean
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.presenter.HomePresenter
import com.littlegreens.wanandroidmvp.modules.user.UserManager
import com.littlegreens.wanandroidmvp.util.RvAnimUtils
import kotlinx.android.synthetic.main.fragment_home_layout.*


/**
 * @author LittleGreens [Contact me.](mailto:alittlegreens@foxmail.com)
 * @version 1.0
 * @since 2019/11/7 18:00
 */
class HomeFragment : BaseMvpFragment<HomePresenter, HomeModel>(), HomeContract.View {

    private val PAGE_START = 0
    private var currPage = PAGE_START
    var homeAdapter: HomeAdapter? = null

    override fun getBannerSuccess(code: Int, data: List<BannerBean>) {
        LogUtil.d("code：$code data:$data")
    }

    override fun getBannerFail(code: Int, msg: String) {
        LogUtil.d("1code：$code msg:$msg")
    }

    override fun getArticleListSuccess(code: Int, data: HomeArticalBean) {
        LogUtil.d("code：$code data:$data")

        if (currPage == PAGE_START) {
            homeAdapter?.setNewData(data.datas)
        } else {
            homeAdapter?.addData(data.datas)
            homeAdapter?.loadMoreComplete()
        }
        if (data.over) {
            homeAdapter?.loadMoreEnd()
        } else {
            if (!homeAdapter!!.isLoadMoreEnable) {
                homeAdapter?.setEnableLoadMore(true)
            }
        }

        success()
    }

    override fun getArticleListFailed(code: Int, msg: String) {
        LogUtil.d("2code：$code msg:$msg")
        ToastUitl.showShort(msg)
        fail()
    }

    override fun getTopArticleListSuccess(code: Int, data: List<ArticleBean>) {
        LogUtil.d("code：$code data:$data")
    }

    override fun getTopArticleListFailed(code: Int, msg: String) {
        LogUtil.d("3code：$code msg:$msg")
    }

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

    override val layoutResource: Int = com.littlegreens.wanandroidmvp.R.layout.fragment_home_layout

    override fun initView(view: View?) {
        createHeaderBanner()
        homeAdapter = HomeAdapter()
        homeAdapter?.setEnableLoadMore(false)
        recyclerView.layoutManager = LinearLayoutManager(context)
        RvAnimUtils.setAnim(homeAdapter, RvAnimUtils.RvAnim.SLIDEIN_LEFT)

        recyclerView.adapter = homeAdapter
    }

    private fun createHeaderBanner() {


    }

    override fun initEvent() {

        actionBarCommon.setOnRightIconClickListener {
            ToastUitl.showShort("搜索")
        }

        refreshLayout.setEnableLoadMore(false)//是否启用上拉加载功能
        refreshLayout.setOnRefreshListener { refreshlayout ->
            firstloadData()
        }
//        refreshLayout.setOnLoadMoreListener { refreshlayout ->
//            currPage++
//            mPresenter.getArticleList(currPage)
//        }

        homeAdapter?.setOnLoadMoreListener({
            currPage++
            mPresenter.getArticleList(currPage)
        }, recyclerView)

        homeAdapter?.setOnItemClickListener { adapter, view, position ->

            ToastUitl.showShort("$position")
        }

//        homeAdapter?.setOnCollectViewClickListener { helper, v, position ->
//
//
//        }
        homeAdapter?.setOnCollectViewClickListener(object : HomeAdapter.OnCollectViewClickListener {
            override fun onClick(helper: BaseViewHolder, v: View, position: Int) {
                if (UserManager.doIfLogin(context!!)) {
                    val item = homeAdapter?.getItem(position)
                    if (item != null) {
                        if(item.collect){
                            ToastUitl.showShort("collect")
//                            presenter.collect(item, v)
                        }else{
                            ToastUitl.showShort("uncollect")
//                            presenter.uncollect(item, v)
                        }
                    }
                }

            }


        })
    }

    override fun loadData() {
        showLoading()
        firstloadData()


    }

    override fun initLoadingStatusViewHolder(): Gloading.Holder? {
        return Gloading.getDefault().wrap(recyclerView).withRetry(Runnable {
            showLoading()
            firstloadData()
        })
    }

    private fun firstloadData() {
        mPresenter.getBanner()
        mPresenter.getTopArticleList()
        currPage = PAGE_START
        mPresenter.getArticleList(currPage)
    }

    fun success() {
        showLoadSuccess()
        refreshLayout.finishRefresh(true)
    }

    fun fail() {
        showLoadFailed()
        refreshLayout.finishRefresh(false)
        homeAdapter?.loadMoreFail()
    }
}
