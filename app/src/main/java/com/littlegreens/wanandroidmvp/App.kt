package com.littlegreens.wanandroidmvp

import com.billy.android.loading.Gloading
import com.littlegreens.baselibary.base.BaseApplication
import com.littlegreens.wanandroidmvp.adapter.loading.GlobalAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader

/**
 * Created by caiyuk on 2019/1/30.
 */

class App : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        initRefreshLayout()
        Gloading.debug(BuildConfig.DEBUG)
        Gloading.initDefault(GlobalAdapter())
    }

    private fun initRefreshLayout() {

        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            ClassicsHeader(
                context
            )
        }

        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            ClassicsFooter(
                context
            )
        }
    }




}
