package com.project.data.di

import com.project.data.repository.TVMazeRepository
import com.project.data.repository.TVMazeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsTVMazeRepository(
        tvMazeRepository: TVMazeRepositoryImpl
    ): TVMazeRepository
}