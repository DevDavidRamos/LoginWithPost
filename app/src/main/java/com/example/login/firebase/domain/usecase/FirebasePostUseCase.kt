package com.example.login.firebase.domain.usecase

import com.example.login.firebase.domain.model.Post
import com.example.login.firebase.domain.model.User
import com.example.login.firebase.domain.repository.AuthRepository
import com.example.login.firebase.domain.repository.PostRepository
import com.example.login.firebase.domain.repository.UserRepository
import com.example.login.firebase.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class FirebasePostUseCase @Inject constructor(
    private val postRepository: PostRepository ){


    suspend operator fun invoke(post: Post): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading)

        val networkRequest = postRepository.savePost(post)

        when(networkRequest) {
            is Resource.Succes -> emit(Resource.Succes(Unit))
            is Resource.Error -> emit(Resource.Error(networkRequest.message))
            else -> Resource.Finished
        }
    }
}