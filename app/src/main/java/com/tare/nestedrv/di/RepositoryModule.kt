package com.tare.nestedrv.di

import com.tare.nestedrv.network.Services
import com.tare.nestedrv.ui.HomeRepository
import com.tare.nestedrv.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideConstants():Constants{
        return Constants
    }

    @Singleton
    @Provides
    fun provideHomeRepository(webServices: Services,constants:Constants): HomeRepository{
        return HomeRepository(webServices, constants)
    }
}