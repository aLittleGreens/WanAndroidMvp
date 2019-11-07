package com.littlegreens.wanandroidmvp.api.observer

import android.util.Log
import android.widget.Toast
import com.google.gson.JsonParseException
import com.lish.base.klaus.rx.RxManager
import com.littlegreens.baselibary.base.BaseApplication
import com.littlegreens.baselibary.commonutil.LogUtil
import com.littlegreens.baselibary.commonutil.NetWorkUtils
import com.littlegreens.baselibary.net.CookieManager
import com.littlegreens.wanandroidmvp.R
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019-09-07 17:54
 */
abstract class AbstractObserver<T : Any>(private val rxManager: RxManager? = null) : Observer<T> {

    abstract fun onSuccess(bean: T)

    abstract fun onFail(error: ApiException)

    abstract fun finish()

    abstract fun start()

    override fun onSubscribe(d: Disposable) {
        rxManager?.add(d)
        start()
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        Log.e("onError", e.message ?: "onError")
        finish()
        val apiException = ApiException.parseException(e)
        if (apiException.errorCode == ApiException.ERROR_TOKEN_EXPIRED) {
            quitAndStartLogin(apiException.showMessage)
            return
        }
        onFail(apiException)

    }

    override fun onComplete() {
        LogUtil.e("onComplete")
        finish()
    }

    private fun quitAndStartLogin(showMessage: String) {
        val context = BaseApplication.appContext
        Toast.makeText(context, showMessage, Toast.LENGTH_SHORT).show()
        CookieManager.getInstance(context).clearCookieInfo()
//         val intent = Intent(context, LoginActivity::class.java)
//         intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//         context.startActivity(intent)
    }

}

class ApiException(val errorCode: Int, override val message: String?, val showMessage: String) : Exception() {

    companion object {

        const val ERROR_UNKONWN = -1    //其他未知错误
        const val ERROR_UNKONWN_HOST = -2   //网络未连接
        const val ERROR_CONNECT_TIMEOUT = -3    //连接服务器超时
        const val ERROR_TOKEN_EXPIRED = -4  // token过期
        const val ERROR_JSONPARSEEXCEPTION = -5 //json解析异常


        fun parseException(error: Throwable): ApiException {
            if (error is ApiException) return error
            val context = BaseApplication.appContext!!
            val showMessage: String
            val code: Int
            if (error is UnknownHostException || error is HttpException) {
                code = ERROR_UNKONWN_HOST
                showMessage = if (!NetWorkUtils.isNetConnected(context)) {
                    context.getString(R.string.text_network_not_available)
                } else {
                    context.getString(R.string.text_address_not_reachable)
                }
            } else if (error is ConnectException || error is TimeoutException
                || error is SocketException || error is SocketTimeoutException
            ) {
                code = ERROR_CONNECT_TIMEOUT
                showMessage = if (!NetWorkUtils.isNetConnected(context)) {
                    context.getString(R.string.text_network_not_available)
                } else {
                    context.getString(R.string.text_network_timeout)
                }

            } else if (error is JsonParseException) {
                code = ERROR_JSONPARSEEXCEPTION
                showMessage = context.getString(R.string.json_parse_error)
            } else {
                code = ERROR_UNKONWN
                showMessage = error.message ?: context.getString(R.string.text_unknown_error)
            }
            return ApiException(code, error.message, showMessage)
        }
    }

}