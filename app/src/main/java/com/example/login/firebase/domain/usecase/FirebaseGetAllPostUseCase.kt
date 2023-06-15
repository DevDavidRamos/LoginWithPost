package com.example.login.firebase.domain.usecase

import com.example.login.firebase.domain.model.Post
import com.example.login.firebase.domain.repository.PostRepository
import com.example.login.firebase.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseGetAllPostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {

    suspend operator fun invoke(): Flow<Resource<List<Post>>> = flow {
        emit(Resource.Loading)

        val networkRequest = postRepository.getAllPost()

        when(networkRequest) {
            is Resource.Succes -> emit(Resource.Succes(networkRequest.data))
            is Resource.Error -> emit(Resource.Error(networkRequest.message))
            else -> Resource.Finished
        }
    }
}