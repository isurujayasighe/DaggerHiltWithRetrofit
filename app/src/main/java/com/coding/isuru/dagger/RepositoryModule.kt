package com.coding.isuru.dagger

import com.coding.isuru.repository.MainRepository
import com.coding.isuru.repository.remote.BackendAPIClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(backendAPIClient: BackendAPIClient):MainRepository{
        return MainRepository(backendAPIClient)
    }
}