package com.example.login.firebase.domain.repository

import com.example.login.firebase.domain.model.User

interface UserRepository {

    suspend fun createUser(user: User):Boolean

    suspend fun getUser(uid: String,nombre:String, apellido: String): User
}