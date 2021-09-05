package com.coding.isuru.dagger

import com.coding.isuru.repository.remote.BackendAPI
import com.coding.isuru.repository.remote.BackendAPIClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


@InstallIn(SingletonComponent::class)
@Module
object RemoteModule {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/todos/1"

    @Singleton
    @Provides
    fun backendAPI(retrofit: Retrofit): BackendAPI{
        return retrofit.create(BackendAPI::class.java)
    }

    @Singleton
    @Provides
    fun backendAPIClient(backendAPI: BackendAPI):BackendAPIClient {
        return BackendAPIClient(backendAPI)
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor ():HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor):OkHttpClient{
        return  OkHttpClient.Builder()
            .connectTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .readTimeout(50, TimeUnit.SECONDS)
            .build();
    }

}