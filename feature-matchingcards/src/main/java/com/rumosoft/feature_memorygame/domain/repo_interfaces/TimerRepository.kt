package com.rumosoft.feature_memorygame.domain.repo_interfaces

import kotlinx.coroutines.flow.Flow

interface TimerRepository {
    fun countDownFrom(secs: Int): Flow<Long>
}