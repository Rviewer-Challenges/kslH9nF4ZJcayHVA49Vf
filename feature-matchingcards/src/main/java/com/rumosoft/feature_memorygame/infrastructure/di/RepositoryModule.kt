package com.rumosoft.feature_memorygame.infrastructure.di

import com.rumosoft.feature_memorygame.data.repository.MatchingCardsRepositoryImpl
import com.rumosoft.feature_memorygame.data.repository.TimerRepositoryImpl
import com.rumosoft.feature_memorygame.domain.repo_interfaces.MatchingCardsRepository
import com.rumosoft.feature_memorygame.domain.repo_interfaces.TimerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMatchingCardsRepository(
        cardsRepository: MatchingCardsRepositoryImpl
    ): MatchingCardsRepository

    @Binds
    @Singleton
    abstract fun bindTimerRepository(
        timerRepository: TimerRepositoryImpl
    ): TimerRepository
}
