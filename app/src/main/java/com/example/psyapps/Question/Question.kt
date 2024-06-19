package com.example.psyapps.Question

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    val username: String= "",
    val question: String = "",
    val userImg: Int = 0
) : Parcelable
