package com.example.psyapps.data

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("loginResult")
	val loginResult: LoginResult? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class LoginResult(

	@field:SerializedName("patient_id")
	val patientId: String? = null,

	@field:SerializedName("name")
	val username: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)
