package com.dilip.qrventory.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.dilip.data.database.QrCodeDatabase
import com.dilip.data.repository.PreferencesDatastore
import com.dilip.domain.repository.PreferencesRepository
import com.dilip.domain.repository.QRCodeRepository
import com.dilip.domain.use_case.AddQrCodeUseCase
import com.dilip.domain.use_case.AppUseCases
import com.dilip.domain.use_case.DeleteQrCodeUseCase
import com.dilip.domain.use_case.GetQrCodeUseCase
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppContext(@ApplicationContext context: Context) = context

    @Provides
    @Singleton
    fun provideAppCoroutineScope(): AppCoroutineScope {
        return object : AppCoroutineScope {
            override val coroutineContext =
                SupervisorJob() + Dispatchers.Main.immediate + CoroutineName("App")
        }
    }

    @Provides
    @Singleton
    fun providesOptions(): GmsBarcodeScannerOptions {
        return GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
            .build()
    }

    @Provides
    @Singleton
    fun provideScanner(context: Context, options: GmsBarcodeScannerOptions): GmsBarcodeScanner {
        return GmsBarcodeScanning.getClient(context, options)
    }

    @Provides
    @Singleton
    fun providesPreferencesRepository(
        application: Application
    ): PreferencesRepository = PreferencesDatastore(context = application)

    @Provides
    @Singleton
    fun providesQrCodeDatabase(application: Application): QrCodeDatabase {
        return Room.databaseBuilder(
            application,
            QrCodeDatabase::class.java,
            QrCodeDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesQrCodeUseCases(repository: QRCodeRepository): AppUseCases {
        return AppUseCases(
            getQrCodeUseCase = GetQrCodeUseCase(repository),
            deleteQrCodeUseCase = DeleteQrCodeUseCase(repository),
            addNoteUseCases = AddQrCodeUseCase(repository)
        )
    }

}