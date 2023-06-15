package com.example.login.firebase.data.remote

import com.example.login.firebase.data.util.FirebaseConstants.POST_COLLECTION
import com.example.login.firebase.domain.model.Post
import com.example.login.firebase.domain.repository.PostRepository
import com.example.login.firebase.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestorePostRepositoryImpl @Inject constructor(

): PostRepository {

    override suspend fun savePost(post: Post): Resource<Unit> {
        return try {
            var isSuccessful = false
            FirebaseFirestore.getInstance().collection(POST_COLLECTION)
                .document(post.id)
                .set(post, SetOptions.merge())
                .addOnCompleteListener { isSuccessful = it.isSuccessful }
                .await()

            if (isSuccessful) {
                Resource.Succes(Unit)
            } else {
                Resource.Error("Network error")
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getAllPost(): Resource<List<Post>> {
        return try {
            val postList = FirebaseFirestore.getInstance().collection(POST_COLLECTION)
                .get()
                .await()
                .toObjects(Post::class.java)

            Resource.Succes(postList)
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun deletePost(post: Post): Resource<Unit> {
        return try {
            var isSuccessful = false
            FirebaseFirestore.getInstance().collection(POST_COLLECTION)
                .document(post.id)
                .delete()
                .addOnCompleteListener { isSuccessful = it.isSuccessful }
                .await()

            if (isSuccessful) {
                Resource.Succes(Unit)
            } else {
                Resource.Error("Network error")
            }
        } catch (e :Exception) {
            Resource.Error(e.message.toString())
        }
    }

}