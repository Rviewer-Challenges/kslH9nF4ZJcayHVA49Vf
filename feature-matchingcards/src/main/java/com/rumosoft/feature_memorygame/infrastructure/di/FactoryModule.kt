package com.rumosoft.feature_memorygame.infrastructure.di

import com.rumosoft.feature_memorygame.domain.entity.BoardFactory
import com.rumosoft.feature_memorygame.domain.entity.BoardFactoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FactoryModule {
    @Binds
    abstract fun bindBoardFactory(
        boardFactoryImpl: BoardFactoryImpl
    ): BoardFactory
}
