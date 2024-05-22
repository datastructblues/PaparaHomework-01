package com.example.hw01_papara.di

import com.example.hw01_papara.data.repository.MealRepository
import com.example.hw01_papara.data.repository.MealRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindRepository(repository: MealRepositoryImpl): MealRepository
}