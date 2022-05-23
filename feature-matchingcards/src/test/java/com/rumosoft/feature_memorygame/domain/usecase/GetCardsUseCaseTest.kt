package com.rumosoft.feature_memorygame.domain.usecase

import com.rumosoft.feature_memorygame.domain.repo_interfaces.MatchingCardsRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class GetCardsUseCaseTest {
    @Test
    fun `GetCardsUseCase calls repo`() = test {
        `given fetchCards on repo runs`()

        `when getCardsUseCase is invoked`()

        `then repository is invoked`()
    }

    private fun TestScope.`given fetchCards on repo runs`() {
        every { repository.fetchCards(numCards) } returns emptyList()
    }

    private fun TestScope.`when getCardsUseCase is invoked`() {
        getCardsUseCase(numCards)
    }

    private fun TestScope.`then repository is invoked`() {
        verify { repository.fetchCards(numCards) }
    }

    private fun test(block: TestScope.() -> Unit) {
        TestScope().block()
    }

    private class TestScope(
        val numCards: Int = Random.nextInt(1, 99),
        val repository: MatchingCardsRepository = mockk(),
        val getCardsUseCase: GetCardsUseCase = GetCardsUseCase(
            repository = repository
        ),
    )
}