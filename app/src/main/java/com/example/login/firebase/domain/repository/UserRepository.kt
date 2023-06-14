package com.example.login.firebase.domain.repository

import com.example.login.firebase.domain.model.Post
import com.example.login.firebase.domain.model.User
import java.util.*
import kotlin.collections.ArrayList

interface UserRepository {

    suspend fun createUser(user: User):Boolean

    suspend fun getUser(uid: String,nombre:String, apellido: String): User


}