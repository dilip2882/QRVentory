package com.dilip.qrventory.di

import android.app.Application
import android.content.Context
import com.dilip.data.repository.PreferencesDatastore
import com.dilip.domain.repository.PreferencesRepository
import com.komu.sekia.di.AppCoroutineScope
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
    fun providesPreferencesRepository(
        application: Application
    ): PreferencesRepository = PreferencesDatastore(context = application)

}