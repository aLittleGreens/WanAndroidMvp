package com.littlegreens.wanandroidmvp

import com.billy.android.loading.Gloading
import com.littlegreens.baselibary.base.BaseApplication
import com.littlegreens.wanandroidmvp.adapter.loading.GlobalAdapter

/**
 * Created by caiyuk on 2019/1/30.
 */

class App : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        Gloading.debug(BuildConfig.DEBUG)
        Gloading.initDefault(GlobalAdapter())
    }




}
