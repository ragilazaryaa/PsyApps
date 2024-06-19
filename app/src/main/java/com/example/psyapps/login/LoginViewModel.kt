package com.example.psyapps.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.psyapps.data.LoginResponse
import com.example.psyapps.data.LoginResult
import com.example.psyapps.data.UserModel
import com.example.psyapps.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val _loginresponse = MutableLiveData<LoginResponse>()
    val loginResponse : LiveData<LoginResponse> = _loginresponse

    fun postLogin(bodyLogin: UserModel){
        ApiConfig.getApiService().login(bodyLogin).enqueue(object :
            Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful){
                    Log.d("TAG", response.body().toString())
                    _loginresponse.postValue(response.body())
                }else{
                    Log.d("TAG", "Login Response Failed")
                    _loginresponse.postValue(LoginResponse(LoginResult("null", "null", "null"),
                    true, "null"))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("TAG", t.message.toString())
                _loginresponse.postValue(LoginResponse(LoginResult("null", "null", "null"),
                true , "null"))
            }
        })
    }
}