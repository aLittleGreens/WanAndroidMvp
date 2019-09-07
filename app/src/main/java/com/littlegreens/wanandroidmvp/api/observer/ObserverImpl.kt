package com.littlegreens.wanandroidmvp.api.observer

import com.lish.base.klaus.rx.RxManager
import com.littlegreens.baselibary.base.BaseApplication
import com.littlegreens.baselibary.basebean.BaseResponse
import com.littlegreens.wanandroidmvp.R

abstract class ObserverImpl<T : BaseResponse<*>>(private val rxManager: RxManager? = null) : AbstractObserver<T>() {

    override fun onNext(t: T) {
        if (t.code == 70001) {
            onError(
                ApiException(
                    ApiException.ERROR_TOKEN_EXPIRED,
                    t.msg, BaseApplication.appContext!!.getString(R.string.text_token_expired)
                )
            )
            return
        }
        onSuccess(t)
    }
}
