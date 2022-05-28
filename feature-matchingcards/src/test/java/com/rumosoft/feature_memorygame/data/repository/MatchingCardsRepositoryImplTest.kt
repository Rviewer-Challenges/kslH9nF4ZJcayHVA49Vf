package com.rumosoft.feature_memorygame.data.repository

import com.rumosoft.feature_memorygame.domain.repo_interfaces.MatchingCardsRepository
import com.rumosoft.library_simpsons_api.data.local.SimpsonsLocalDataSource
import com.rumosoft.library_simpsons_api.data.model.CardDto
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class MatchingCardsRepositoryImplTest {
    @Test
    fun `repo fetchCards invokes data source fetchCards`() = test {
        `given fetchCards on data source returns data`()

        `when fetchCards is invoked on repo`()

        `then data source gets invoked`()
    }

    private fun TestScope.`given fetchCards on data source returns data`() {
        every { simpsonsDataSource.fetchCards(numCards / 2) } returns
                listOf(
                    CardDto(
                        characterId = 1,
                        name = "name",
                        imageUrl = "imageUrl",
                    )
                )
    }

    private fun TestScope.`when fetchCards is invoked on repo`() {
        repository.fetchCards(numCards)
    }

    private fun TestScope.`then data source gets invoked`() {
        verify { simpsonsDataSource.fetchCards(numCards / 2) }
    }

    private fun test(block: TestScope.() -> Unit) {
        TestScope().block()
    }

    private class TestScope(
        val numCards: Int = 2,
        val simpsonsDataSource: SimpsonsLocalDataSource = mockk(),
        val repository: MatchingCardsRepository = MatchingCardsRepositoryImpl(
            simpsonsLocalDataSource = simpsonsDataSource
        )
    )
}
