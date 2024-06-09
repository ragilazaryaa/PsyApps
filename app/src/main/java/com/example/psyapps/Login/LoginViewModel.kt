package com.example.psyapps.Login

import androidx.lifecycle.ViewModel
import com.example.psyapps.pref.UserModel
import com.example.psyapps.repository.UserRepository

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }
}