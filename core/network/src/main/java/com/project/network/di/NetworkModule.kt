package com.project.network.di

import com.project.network.TVMazeDataSource
import com.project.network.retrofit.TVMaze
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {
    @Binds
    fun bindsTVMaze(
        tvMaze: TVMaze
    ): TVMazeDataSource
}