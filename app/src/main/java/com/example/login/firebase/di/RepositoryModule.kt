package com.example.login.firebase.di

import com.example.login.firebase.data.remote.FirebaseAuthRepositoryImpl
import com.example.login.firebase.data.remote.FirestoreUserRepositoryImpl
import com.example.login.firebase.domain.repository.AuthRepository
import com.example.login.firebase.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(authRepository: FirebaseAuthRepositoryImpl): AuthRepository


   @Binds
   abstract fun bindUserRepository(userRepository: FirestoreUserRepositoryImpl): UserRepository

}