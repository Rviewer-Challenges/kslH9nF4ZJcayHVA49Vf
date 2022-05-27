package com.rumosoft.feature_memorygame.data.repository

import com.rumosoft.feature_memorygame.domain.repo_interfaces.TimerRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.inject.Inject

private const val ONE_SEC_IN_MILLIS = 1_000L

class TimerRepositoryImpl @Inject constructor() : TimerRepository {
    override fun countDownFrom(secs: Int): Flow<Long> {
        val startingTime = Instant.now()
        var remainingTime = elapsedTime(secs, startingTime)
        return flow {
            while (remainingTime > 0) {
                delay(ONE_SEC_IN_MILLIS)
                remainingTime = elapsedTime(secs, startingTime)
                emit(remainingTime)
            }
        }
    }

    private fun elapsedTime(secs: Int, startingTime: Instant) =
        secs - ChronoUnit.SECONDS.between(startingTime, Instant.now())
}