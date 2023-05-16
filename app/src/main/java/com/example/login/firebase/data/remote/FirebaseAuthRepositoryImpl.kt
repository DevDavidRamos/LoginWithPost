package com.example.login.firebase.data.remote

import com.example.login.firebase.domain.repository.AuthRepository
import com.example.login.firebase.util.Resource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class FirebaseAuthRepositoryImpl @Inject constructor(
  private val firebaseAuth: FirebaseAuth
):AuthRepository {
    override suspend fun login(email: String, password: String): Boolean{
        return try {
            var isSuccesful :Boolean = false
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { isSuccesful = true }
                .addOnFailureListener{ isSuccesful = false }
                .await()
            isSuccesful
        }catch (e: Exception){
            false

        }
    }

    override suspend fun signUp(email: String, password: String): Boolean {
        return try {
            var isSuccesful :Boolean = false
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {isSuccesful = it.isSuccessful }
                    .await()
            return isSuccesful


        }catch (e: Exception){
            false
        }
    }

}