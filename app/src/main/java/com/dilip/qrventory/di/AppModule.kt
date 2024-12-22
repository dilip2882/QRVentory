package com.dilip.qrventory.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.dilip.data.local.database.DeviceDao
import com.dilip.data.local.database.DeviceDatabase
import com.dilip.data.local.database.DeviceQrDao
import com.dilip.data.local.database.DeviceQrsDatabase
import com.dilip.data.remote.FirebaseDeviceQrDataSource
import com.dilip.data.repository.PreferencesDatastore
import com.dilip.data.repository.device.DeviceAssigneeRepositoryImpl
import com.dilip.data.repository.device.DeviceLocationRepositoryImpl
import com.dilip.data.repository.device.DeviceQrRepositoryImpl
import com.dilip.data.repository.device.DeviceTypeRepositoryImpl
import com.dilip.domain.repository.PreferencesRepository
import com.dilip.domain.repository.device.DeviceAssigneeRepository
import com.dilip.domain.repository.device.DeviceLocationRepository
import com.dilip.domain.repository.device.DeviceQrRepository
import com.dilip.domain.repository.device.DeviceTypeRepository
import com.dilip.domain.use_case.AddDeviceQr
import com.dilip.domain.use_case.DeleteDeviceQr
import com.dilip.domain.use_case.DeviceAssigneeUseCases
import com.dilip.domain.use_case.DeviceLocationUseCases
import com.dilip.domain.use_case.DeviceQrsUseCases
import com.dilip.domain.use_case.DeviceTypeUseCases
import com.dilip.domain.use_case.GetAllDeviceQrs
import com.dilip.domain.use_case.UpdateDeviceQr
import com.dilip.domain.use_case.device_assignee.AddDeviceAssigneeUseCase
import com.dilip.domain.use_case.device_assignee.DeleteDeviceAssigneeUseCase
import com.dilip.domain.use_case.device_assignee.GetDeviceAssigneesUseCase
import com.dilip.domain.use_case.device_location.AddDeviceLocationUseCase
import com.dilip.domain.use_case.device_location.DeleteDeviceLocationUseCase
import com.dilip.domain.use_case.device_location.GetDeviceLocationsUseCase
import com.dilip.domain.use_case.device_type.AddDeviceTypeUseCase
import com.dilip.domain.use_case.device_type.DeleteDeviceTypeUseCase
import com.dilip.domain.use_case.device_type.GetDeviceTypesUseCase
import com.google.firebase.firestore.FirebaseFirestore
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
        application: Application,
    ): PreferencesRepository = PreferencesDatastore(context = application)

    @Provides
    @Singleton
    fun providesDeviceDatabase(application: Application): DeviceDatabase {
        return Room.databaseBuilder(
            application,
            DeviceDatabase::class.java,
            DeviceDatabase.DATABASE_NAME,
        ).build()
    }

    @Provides
    @Singleton
    fun providesDeviceDao(database: DeviceDatabase): DeviceDao {
        return database.deviceDao
    }

    @Provides
    @Singleton
    fun providesDeviceAssigneeRepository(deviceDao: DeviceDao): DeviceAssigneeRepository {
        return DeviceAssigneeRepositoryImpl(deviceDao)
    }

    @Provides
    @Singleton
    fun providesDeviceAssigneeUseCases(repository: DeviceAssigneeRepository): DeviceAssigneeUseCases {
        return DeviceAssigneeUseCases(
            getDeviceAssigneesUseCase = GetDeviceAssigneesUseCase(repository),
            addDeviceAssigneeUseCase = AddDeviceAssigneeUseCase(repository),
            deleteDeviceAssigneeUseCase = DeleteDeviceAssigneeUseCase(repository),
        )
    }

    @Provides
    @Singleton
    fun providesDeviceLocationRepository(deviceDao: DeviceDao): DeviceLocationRepository {
        return DeviceLocationRepositoryImpl(deviceDao)
    }

    @Provides
    @Singleton
    fun providesDeviceLocationUseCases(repository: DeviceLocationRepository): DeviceLocationUseCases {
        return DeviceLocationUseCases(
            getDeviceLocationsUseCase = GetDeviceLocationsUseCase(repository),
            addDeviceLocationUseCase = AddDeviceLocationUseCase(repository),
            deleteDeviceLocationUseCase = DeleteDeviceLocationUseCase(repository),
        )
    }

    @Provides
    @Singleton
    fun providesDeviceTypeRepository(deviceDao: DeviceDao): DeviceTypeRepository {
        return DeviceTypeRepositoryImpl(deviceDao)
    }

    @Provides
    @Singleton
    fun providesDeviceTypeUseCases(repository: DeviceTypeRepository): DeviceTypeUseCases {
        return DeviceTypeUseCases(
            getDeviceTypesUseCase = GetDeviceTypesUseCase(repository),
            addDeviceTypeUseCase = AddDeviceTypeUseCase(repository),
            deleteDeviceTypeUseCase = DeleteDeviceTypeUseCase(repository),
        )
    }

    @Provides
    @Singleton
    fun provideDeviceQrsDatabase(app: Application): DeviceQrsDatabase {
        return Room.databaseBuilder(
            app,
            DeviceQrsDatabase::class.java,
            "device_qrs_db",
        ).build()
    }

    @Provides
    @Singleton
    fun provideDeviceQrsDao(db: DeviceQrsDatabase): DeviceQrDao {
        return db.deviceQrsDao()
    }

    @Provides
    @Singleton
    fun provideFirebaseDeviceQrDataSource(firestore: FirebaseFirestore): FirebaseDeviceQrDataSource {
        return FirebaseDeviceQrDataSource(firestore)
    }

    @Provides
    @Singleton
    fun provideDeviceQrsRepository(dao: DeviceQrDao, firebaseDeviceQrDataSource: FirebaseDeviceQrDataSource): DeviceQrRepository {
        return DeviceQrRepositoryImpl(localDataSource = dao, remoteDataSource = firebaseDeviceQrDataSource)
    }

    @Provides
    @Singleton
    fun provideDeviceQrsUseCases(repository: DeviceQrRepository): DeviceQrsUseCases {
        return DeviceQrsUseCases(
            addDeviceQr = AddDeviceQr(repository),
            updateDeviceQr = UpdateDeviceQr(repository),
            deleteDeviceQr = DeleteDeviceQr(repository),
            getAllDeviceQrs = GetAllDeviceQrs(repository),
        )
    }
}
