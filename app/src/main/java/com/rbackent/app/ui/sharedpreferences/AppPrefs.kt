package com.rbackent.app.ui.sharedpreferences
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap

class AppPrefs(private val ctx: Context) {
    private fun getPrefs(): SharedPreferences {
        return ctx.getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE)
    }

    fun setString(key: String, value:String) {
        val edit = getPrefs().edit()
        edit.putString(key, value)
        edit.apply()
    }
    fun getString(key: String) : String?{
        return getPrefs().getString(key,"")
    }
    fun setBoolean(key: String, value: Boolean){
        val edit = getPrefs().edit()
        edit.putBoolean(key, value)
        edit.apply()
    }

    fun getBoolean(key: String) :Boolean{
        return getPrefs().getBoolean(key,false)
    }

    fun setNameString(key: String, value:String) {
        val edit = getPrefs().edit()
        edit.putString(key, value)
        edit.apply()
    }
    fun getNameStringKey(key: String) :String?{
        return getPrefs().getString(key,"")
    }

    fun setToken(key : String,value: String){
        var edit = getPrefs().edit()
        edit.putString(key, value)
        edit.apply()
    }
    fun getToken(key: String) :String?{
        return getPrefs().getString(key,"")
    }
}