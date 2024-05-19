package com.example.hw01_papara.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw01_papara.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {
    private val _loginResult = MutableStateFlow<FirebaseUser?>(null)
    val loginResult: MutableStateFlow<FirebaseUser?> = _loginResult

    private val _registerResult = MutableStateFlow<FirebaseUser?>(null)
    val registerResult: MutableStateFlow<FirebaseUser?> = _registerResult

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            val result = authRepository.loginUser(email,password)
            _loginResult.value = result
        }
    }
    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            val result = authRepository.registerUser(email,password)
            _registerResult.value = result
        }
    }
}