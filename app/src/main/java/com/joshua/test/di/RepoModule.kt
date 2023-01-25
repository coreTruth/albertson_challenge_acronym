package com.joshua.test.di

import com.joshua.test.data.AcronymRepositoryImpl
import com.joshua.test.domain.acronym.AcronymRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun provideAcronymRepository(acronymRepositoryImpl: AcronymRepositoryImpl): AcronymRepository
}