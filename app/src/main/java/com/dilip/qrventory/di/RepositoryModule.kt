package com.dilip.qrventory.di

import com.dilip.data.repository.MainRepoImpl
import com.dilip.domain.repository.MainRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMainRepo(
        mainRepoImpl: MainRepoImpl
    ): MainRepo
}