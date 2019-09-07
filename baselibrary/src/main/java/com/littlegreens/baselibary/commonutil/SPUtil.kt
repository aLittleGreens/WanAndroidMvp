package com.littlegreens.baselibary.commonutil

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

@SuppressLint("StaticFieldLeak")
object SPUtil {
    private val fileName = "littlegreensConfigs"
    private val mode = Context.MODE_PRIVATE
    private lateinit var mContext: Context
    private lateinit var sp: SharedPreferences

    fun init(context: Context) {
        mContext = context.applicationContext
        sp = mContext.getSharedPreferences(fileName, mode)
    }

    /**存储 */
    fun putBoolean(key: String, value: Boolean) {
        val editor = sp.edit()
        editor.putBoolean(key, value)
        editor.commit()
    }

    fun putInt(key: String, value: Int) {
        val editor = sp.edit()
        editor.putInt(key, value)
        editor.commit()
    }

    fun putString(key: String, value: String) {
        val editor = sp.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun putLong(key: String, value: Long) {
        val editor = sp.edit()
        editor.putLong(key, value)
        editor.commit()
    }

    /**获取 */
    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return sp.getBoolean(key, defValue)
    }

    fun getInt(key: String, defValue: Int): Int {
        return sp.getInt(key, defValue)
    }

    fun getLong(key: String, defValue: Long): Long {
        return sp.getLong(key, defValue)
    }

    fun getString(key: String, defValue: String): String {
        return sp.getString(key, defValue)
    }

    /**独立部分*/
    fun putSpBoolean(key: String, value: Boolean) {
        val editor = sp.edit()
        editor.putBoolean(key, value)
        editor.commit()
    }

    fun getSpBoolean(key: String, defValue: Boolean): Boolean {
        return sp.getBoolean(key, defValue)
    }

    fun putSpString(key: String, value: String) {
        val editor = sp.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun getSpString(key: String, defValue: String): String {
        return sp.getString(key, defValue)
    }

    /**独立部分*/

    fun clear() {
        val editor = sp.edit()
        editor.clear()
        editor.commit()
    }
}
