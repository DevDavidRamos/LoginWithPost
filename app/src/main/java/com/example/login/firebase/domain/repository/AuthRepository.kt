package com.example.login.firebase.domain.repository

import com.example.login.firebase.util.Resource
import java.util.*
import kotlin.collections.ArrayList

interface AuthRepository {
    suspend fun login(email: String, password: String): String
    suspend fun signUp(nombre: String, apellido: String,email:String, password: String): String



}