package com.example.login.firebase.domain.repository

import com.example.login.firebase.util.Resource

interface AuthRepository {
    suspend fun login(email: String, password: String): String
    suspend fun signUp(email:String, password: String): String


}