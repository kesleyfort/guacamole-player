package com.kalves.musicplayer.Utils


import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import org.jetbrains.anko.defaultSharedPreferences

class Prefs(context: Context) {

    val prefs: SharedPreferences = context.defaultSharedPreferences
    var DEVICE_TOKEN = "keepLogged"
    var SAVECREDENTIALS = "saveCredentials"
    var SAVEDEMAIL = "loginEmail"
    var SAVEDPASSWORD = "loginPassword"
    var cUserName = "currentUserName"
    var cUserEmail = "currentUserEmail"
    var cUserPassword = "currentUserPassword"
    var cUserNickname = "currentUserNickname"
    var keepLogged = prefs.getString(DEVICE_TOKEN, "")
        set(value) = prefs.edit().putString(DEVICE_TOKEN, value).apply()

    var isCredentialsSaved = prefs.getString(SAVECREDENTIALS, "")
        set(value) = prefs.edit().putString(SAVECREDENTIALS, value).apply()

    var savedEmail = prefs.getString(SAVEDEMAIL, "")
        set(value) = prefs.edit().putString(SAVEDEMAIL, value).apply()

    var savedPassword = prefs.getString(SAVEDPASSWORD, "")
        set(value) = prefs.edit().putString(SAVEDPASSWORD, value).apply()

    var currentUserName = prefs.getString(cUserName, "")
        set(value) = prefs.edit().putString(cUserName, value).apply()

    var currentUserEmail = prefs.getString(cUserEmail, "")
        set(value) = prefs.edit().putString(cUserEmail, value).apply()

    var currentUserPassword = prefs.getString(cUserPassword, "")
        set(value) = prefs.edit().putString(cUserPassword, value).apply()
    var currentUserNickname = prefs.getString(cUserNickname, "")
        set(value) = prefs.edit().putString(cUserNickname, value).apply()

    fun setPreference(key: String, value: String) {
        prefs.edit().putString(key, value).commit()
        Log.e("Retrieved preference from function setPreference", prefs.getString(cUserNickname, ""))
    }
}