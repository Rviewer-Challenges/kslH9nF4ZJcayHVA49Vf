package com.rumosoft.feature_memorygame.domain.usecase

import com.rumosoft.feature_memorygame.domain.repo_interfaces.TimerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountDownUseCase @Inject constructor(
    private val repository: TimerRepository
) {
    operator fun invoke(secs: Int): Flow<Long> =
        repository.countDownFrom(secs)
}
