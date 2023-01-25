package com.joshua.test.di

import com.joshua.test.data.AcronymApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.joshua.test.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor

const val baseUrl = BuildConfig.Base_Url
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkhttpClient(
    ): OkHttpClient {

        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(interceptor)
        }

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideAcronymApi(client: OkHttpClient): AcronymApi {
        val retrofit =
            Retrofit.Builder().client(client).baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(AcronymApi::class.java)

    }
}
