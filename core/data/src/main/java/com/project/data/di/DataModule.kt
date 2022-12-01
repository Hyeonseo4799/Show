package com.project.data.di

import com.project.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsResultRepository(
        tvMazeRepository: ResultRepositoryImpl
    ): ResultRepository

    @Binds
    fun bindsDetailRepository(
        detailRepository: DetailRepositoryImpl
    ): DetailRepository
}