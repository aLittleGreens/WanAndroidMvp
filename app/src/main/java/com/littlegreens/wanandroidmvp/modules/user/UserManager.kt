package com.littlegreens.wanandroidmvp.modules.user

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import com.google.gson.Gson
import com.littlegreens.baselibary.commonutil.JsonUtil
import com.littlegreens.baselibary.commonutil.SPUtil
import com.littlegreens.wanandroidmvp.bean.LoginBean
import com.littlegreens.wanandroidmvp.modules.user.activity.LoginActivity

/**
 *
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/11/13 10:51
 */
object UserManager {

    private var mLoginBean: LoginBean? = null

    private val KEY_LOGIN_BEAN = "KEY_LOGIN_BEAN"

    fun login(loginBean: LoginBean) {
        mLoginBean = loginBean
        val json = Gson().toJson(loginBean)
        SPUtil.putString(KEY_LOGIN_BEAN, json)
    }

    fun logout() {
        mLoginBean = null
        SPUtil.clear()
    }

    fun getLoginBean(): LoginBean? {
        if (mLoginBean == null) {
            val json = SPUtil.getString(KEY_LOGIN_BEAN, "")
            if (!TextUtils.isEmpty(json)) {
                try {
                    mLoginBean = JsonUtil.parseJStr2Object(LoginBean::class.java, json)
                } catch (ignore: Exception) {
                    ignore.printStackTrace()
                }

            }
        }
        return mLoginBean
    }

    fun update(loginBean: LoginBean) {
        mLoginBean = loginBean
        val json = Gson().toJson(loginBean)
        SPUtil.putString(KEY_LOGIN_BEAN, json)
    }

    fun isLogin(): Boolean {
        val loginBean = getLoginBean() ?: return false
        return loginBean.data!!.id > 0
    }

    fun doIfLogin(context: Context): Boolean {
        if (isLogin()) {
            return true
        } else {
            context.startActivity(Intent(context, LoginActivity::class.java))
            return false
        }
    }

}