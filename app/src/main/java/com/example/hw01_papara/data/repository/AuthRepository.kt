package com.example.hw01_papara.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(private val auth : FirebaseAuth) {

    suspend fun loginUser(email: String, password: String): FirebaseUser? {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            result.user
        } catch (exception: Exception) {
            null
        }
    }

    suspend fun registerUser(email: String, password: String): FirebaseUser? {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            result.user
        } catch (exception: Exception) {
            null
        }
    }
    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
    fun logout() {
        auth.signOut()
    }

}