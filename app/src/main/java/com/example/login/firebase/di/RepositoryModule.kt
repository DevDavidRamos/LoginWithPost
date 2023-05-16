package com.example.login.firebase.di

import com.example.login.firebase.data.remote.FirebaseAuthRepositoryImpl
import com.example.login.firebase.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(authRepository: FirebaseAuthRepositoryImpl): AuthRepository

}