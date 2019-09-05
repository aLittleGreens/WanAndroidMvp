package com.lish.base.klaus.base

import android.content.Intent
import android.os.Bundle
import com.lish.base.klaus.base.mvp.BaseModel
import com.lish.base.klaus.base.mvp.BasePresenter
import com.lish.base.klaus.base.mvp.IView
import com.littlegreens.baselibary.commonutil.TUtil
import com.lish.base.klaus.rx.RxManager
import com.littlegreens.baselibary.R

/**
 * Created by littleGreens on 2019/5/8.
 */
abstract class BaseMvpActivity<P : BasePresenter<*, *>, M : BaseModel> : BaseActivity(), IView {

    lateinit var mRxManager: RxManager
    lateinit var mPresenter: P
    lateinit var mModel: M

    override fun onCreate(savedInstanceState: Bundle?) {
        mRxManager = RxManager()
        mPresenter = TUtil.getT(this, 0)!!
        mModel = TUtil.getT(this, 1)!!
        mPresenter.bindVM(this, mModel)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
        mRxManager.clear()
    }


    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }


    override fun finish()
    {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }


}
