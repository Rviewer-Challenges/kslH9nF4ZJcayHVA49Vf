package com.rumosoft.feature_memorygame.data.repository

import com.rumosoft.feature_memorygame.domain.repo_interfaces.TimerRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
internal class TimerRepositoryImplTest {
    @Test
    fun `countdown starts at the passed value`() = test {
        runTest {
            val flow = timerRepo.countDownFrom(1)

            assertEquals(1, flow.first().toInt())
        }
    }

    @Test
    fun `countdown decreases`() = test {
        runTest {
            val secs = 1
            val flow = timerRepo.countDownFrom(secs)

            advanceUntilIdle()

            assertEquals(0, flow.last().toInt())
        }
    }

    private fun test(block: TestScope.() -> Unit) {
        TestScope().block()
    }

    private class TestScope(
        val timerRepo: TimerRepository = TimerRepositoryImpl()
    )
}