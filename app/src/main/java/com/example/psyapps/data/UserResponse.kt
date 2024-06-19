package com.example.psyapps.data

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("UserResponse")
	val userResponse: List<UserResponseItem>
)

data class UserResponseItem(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("gender")
	val gender: Any,

	@field:SerializedName("patient_id")
	val patientId: String,

	@field:SerializedName("username")
	val name: String,

	@field:SerializedName("email")
	val email: String
)
