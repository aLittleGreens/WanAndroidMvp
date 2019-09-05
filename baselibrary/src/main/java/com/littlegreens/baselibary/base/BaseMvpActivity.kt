package com.littlegreens.baselibary.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.lish.base.klaus.rx.RxManager
import com.littlegreens.baselibary.R
import com.littlegreens.baselibary.base.mvp.BaseModel
import com.littlegreens.baselibary.base.mvp.BasePresenter
import com.littlegreens.baselibary.base.mvp.IView
import com.littlegreens.baselibary.commonutil.TUtil

/**
 * Created by littleGreens on 2019/5/8.
 */
abstract class BaseMvpActivity<P : BasePresenter<*, *>, M : BaseModel> : BaseActivity(), IView {

    lateinit var mRxManager: RxManager
    lateinit var mPresenter: P
    lateinit var mModel: M

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRxManager = RxManager()
        mPresenter = TUtil.getT(this, 0)!!
        mModel = TUtil.getT(this, 1)!!
        mPresenter.bindVM(this, mModel)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDestroy()
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

    override fun getContext(): Context? {
        return mContext
    }
}
