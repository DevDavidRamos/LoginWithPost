package com.example.login.firebase.domain.usecase

import com.example.login.firebase.domain.model.User
import com.example.login.firebase.domain.repository.AuthRepository
import com.example.login.firebase.domain.repository.UserRepository
import com.example.login.firebase.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseSignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository

) {

    suspend operator fun invoke(email:String, password: String): Flow<Resource<Boolean>> = flow{

        emit(Resource.Loading)
        val userUID : String = authRepository.signUp(email, password)
        if (userUID.isNotEmpty())
        {
            userRepository.createUser(User(
                email = email,
                uid = userUID
            ))

            emit(Resource.Succes(true))
        }else{
            emit(Resource.Error("SignUp Error"))
        }

    }


}