package com.example.psyapps.injection

import android.content.Context
import com.example.psyapps.pref.UserPreference
import com.example.psyapps.pref.dataStore
import com.example.psyapps.repository.UserRepository

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }
}