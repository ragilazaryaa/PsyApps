package com.example.psyapps.retrofit

import com.example.psyapps.data.LoginResponse
import com.example.psyapps.data.RegisterResponse
import com.example.psyapps.data.UserAuth
import com.example.psyapps.data.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("register/patient")
    fun register(
       @Body authBody:UserAuth
    ) : Call<RegisterResponse>

    @POST("login/patient")
    fun login(
        @Body bodyLogin:UserModel
    ) : Call<LoginResponse>

}