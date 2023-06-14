package com.example.login.firebase.data.remote

import com.example.login.firebase.domain.repository.AuthRepository
import com.example.login.firebase.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class FirebaseAuthRepositoryImpl @Inject constructor(
  private val firebaseAuth: FirebaseAuth
):AuthRepository {
    override suspend fun login(email: String, password: String): String {
        return try {
            var useUID = ""
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    useUID =  it.user?.uid ?: ""
                }
                .await()
            useUID
        }catch (e: Exception){
            ""

        }
    }

    override suspend fun signUp(nombre: String, apellido: String,email: String, password: String): String {
        return try {
            var useUID = ""
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                  useUID =  it.user?.uid ?: ""
                }
                .await()
            useUID



        }catch (e: Exception){
            ""
        }
    }
}