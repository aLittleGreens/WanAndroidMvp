package com.littlegreens.wanandroidmvp.modules.user.model

import com.littlegreens.baselibary.rx.RxJavaHelper
import com.littlegreens.wanandroidmvp.api.Api
import com.littlegreens.wanandroidmvp.bean.LoginBean
import com.littlegreens.wanandroidmvp.modules.user.contract.LoginContract
import io.reactivex.Observable

/**
 *
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/9/10 13:52
 */
class LoginModel : LoginContract.Model {
    override fun login(username: String, password: String): Observable<LoginBean> {
        return Api.getInstance().login(username, password).compose(RxJavaHelper.composeIoToMain())
    }


}