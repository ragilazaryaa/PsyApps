package com.example.psyapps.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.psyapps.data.RegisterResponse
import com.example.psyapps.data.UserAuth
import com.example.psyapps.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterViewModel : ViewModel() {
    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerresponse : LiveData<RegisterResponse> = _registerResponse

    fun postRegister(authBody: UserAuth) {
        ApiConfig.getApiService().register(authBody).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        Log.d("TAG" , "Berhasil")
                        _registerResponse.postValue(response.body())
                    } else {
                        Log.d("TAG", "Response body is null")
                        _registerResponse.postValue(RegisterResponse(true, "Empty response body"))
                    }
                } else {
                    Log.d("TAG", "Register Response Failed: ${response.code()}")
                    _registerResponse.postValue(RegisterResponse(true, "Register failed: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.d("TAG", "Register Error: ${t.message}")
                _registerResponse.postValue(RegisterResponse(true, "Register failed: ${t.message}"))
            }
        })
    }
}