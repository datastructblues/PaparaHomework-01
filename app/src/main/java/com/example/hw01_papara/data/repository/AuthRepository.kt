package com.example.hw01_papara.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(private val auth : FirebaseAuth) {

    suspend fun loginUser(email: String, password: String): FirebaseUser? {
        val result = auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            Log.d("AuthRepository", "Logged in")
            it.user
        }.addOnFailureListener {
            Log.d("AuthRepository", "Failed to login")
        }.await()
        return null
    }

    suspend fun registerUser(email: String, password: String): FirebaseUser? {
        val result = auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            Log.d("AuthRepository", "Registered")
            it.user
        }.addOnFailureListener {
            Log.d("AuthRepository", "Failed to register")
        }.await()
        return null

    }
    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
    suspend fun logout() {
        auth.signOut()
    }

}