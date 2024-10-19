package com.dilip.qrventory.di

import com.dilip.data.repository.QrScannerImpl
import com.dilip.domain.repository.QRScannerRepository
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
    abstract fun qrScannerRepo(
        qrScannerImpl: QrScannerImpl,
    ): QRScannerRepository
}
