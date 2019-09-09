package com.littlegreens.wanandroidmvp.api.observer

import com.lish.base.klaus.rx.RxManager
import com.littlegreens.baselibary.base.BaseApplication
import com.littlegreens.baselibary.basebean.BaseResponse
import com.littlegreens.wanandroidmvp.R

/**
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019-09-07 17:54
 */
abstract class ObserverImpl<T : BaseResponse<*>>(private val rxManager: RxManager? = null) : AbstractObserver<T>() {

    /**
     * 统一处理Token过期
     */
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
