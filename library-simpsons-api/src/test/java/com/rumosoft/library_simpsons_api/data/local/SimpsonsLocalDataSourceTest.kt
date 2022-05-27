package com.rumosoft.library_simpsons_api.data.local

import com.rumosoft.library_simpsons_api.data.model.CardDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class SimpsonsLocalDataSourceTest {
    @Test
    fun `fetchCards should return the number of cards passed by parameter`() = test {
        val numCards = `given a random number of cards`()

        val cards = `when fetchCards is invoked`(numCards)

        `then the returned number of cards is equal to the passed one`(numCards, cards)
    }

    private fun `given a random number of cards`() = Random.nextInt(0, 30)

    private fun TestScope.`when fetchCards is invoked`(numCards: Int): List<CardDto> {
        return simpsonsDataSource.fetchCards(numCards)
    }

    private fun `then the returned number of cards is equal to the passed one`(
        numCards: Int,
        cards: List<CardDto>
    ) {
        assertEquals(numCards, cards.size)
    }


    private fun test(block: TestScope.() -> Unit) {
        TestScope().block()
    }

    private class TestScope(
        val simpsonsDataSource: SimpsonsLocalDataSource = SimpsonsLocalDataSource()
    )
}