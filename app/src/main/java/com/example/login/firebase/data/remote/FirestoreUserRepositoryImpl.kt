package com.example.login.firebase.data.remote

import com.example.login.firebase.data.util.FirebaseConstants.POST_COLLECTION
import com.example.login.firebase.data.util.FirebaseConstants.USER_COLLECTION
import com.example.login.firebase.domain.model.Post
import com.example.login.firebase.domain.model.User
import com.example.login.firebase.domain.repository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject

class FirestoreUserRepositoryImpl @Inject constructor(

): UserRepository {
    override suspend fun createUser(user: User): Boolean {
        return try {
            var isSuccesful = false
            FirebaseFirestore.getInstance().collection(USER_COLLECTION)
                .document(user.uid)
                .set(user, SetOptions.merge())
                .addOnCompleteListener { isSuccesful = it.isSuccessful }
                .await()
            isSuccesful
        } catch (e: Exception) {
            false

        }
    }

    override suspend fun getUser(uid: String, nombre: String, apellido: String): User {

        return try {
            var loggedUser = User()
            FirebaseFirestore.getInstance().collection(USER_COLLECTION)
                .document(uid)
                .get()
                .addOnSuccessListener {
                    loggedUser = it.toObject(User::class.java)!!
                }
                .await()
            loggedUser
        } catch (e: Exception) {
            User()
        }
    }


}