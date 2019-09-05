package com.littlegreens.wanandroidmvp

import com.lish.base.klaus.rx.RxBus
import com.littlegreens.baselibary.base.BaseActivity


class MainActivity : BaseActivity() {


    override fun initLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        RxBus.instance.post("klaus", 78)
    }

    override fun initEvent() {

    }

    companion object {

        private val TAG = "com.klaus.MainActivity"
    }

}
