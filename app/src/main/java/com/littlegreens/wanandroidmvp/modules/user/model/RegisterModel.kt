package com.littlegreens.wanandroidmvp.modules.user.model

import com.littlegreens.baselibary.rx.RxJavaHelper
import com.littlegreens.wanandroidmvp.api.Api
import com.littlegreens.wanandroidmvp.bean.RegisterBean
import com.littlegreens.wanandroidmvp.modules.user.contract.RegisterContract
import io.reactivex.Observable

/**
 *
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/9/10 13:53
 */
class RegisterModel : RegisterContract.Model {
    override fun register(username: String, password: String, repassword: String): Observable<RegisterBean> {
        return Api.getInstance().register(username, password, repassword).compose(RxJavaHelper.composeIoToMain())
    }

}