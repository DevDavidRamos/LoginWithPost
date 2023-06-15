package com.example.login.firebase.domain.repository

import com.example.login.firebase.domain.model.Post
import com.example.login.firebase.util.Resource

interface PostRepository {


    suspend fun savePost(post: Post): Resource<Unit>

    suspend fun getAllPost(): Resource<List<Post>>

    suspend fun deletePost(post: Post): Resource<Unit>
}