package com.example.psyapps.preferences

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences (context: Context) {
    private val sharedpreference : SharedPreferences = context.getSharedPreferences("MY_PREF", Context.MODE_PRIVATE)
    private val editor : SharedPreferences.Editor = sharedpreference.edit()






    fun saveToken( username : String, email : String,token : String){
        editor.putString("username", username)
        editor.putString("email", email)
        editor.putString("token", token)
        editor.apply()
    }

    fun getToken() : String?{
        return sharedpreference.getString("token", null)
    }

    fun getUsername() : String?{
        return sharedpreference.getString("username", null)
    }

    fun clearData(){
        editor.remove("token")
        editor.remove("username")
        editor.remove("email")
        editor.apply()
    }
}
