package com.example.login.firebase.domain.usecase

import com.example.login.firebase.domain.repository.AuthRepository
import com.example.login.firebase.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        val loggedSuccessfully = authRepository.login(email,password)
        if (loggedSuccessfully) {
            emit(Resource.Succes(true))
            emit(Resource.Finished)
        } else {
            emit(Resource.Error("Login error"))
            emit(Resource.Finished)
        }
    }
}