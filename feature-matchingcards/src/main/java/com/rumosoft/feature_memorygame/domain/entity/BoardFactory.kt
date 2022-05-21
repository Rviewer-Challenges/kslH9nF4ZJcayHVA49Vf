package com.rumosoft.feature_memorygame.domain.entity

import javax.inject.Inject

interface BoardFactory {
    fun getBoard(numCards: Int, numColumns: Int): Board
}

class BoardFactoryImpl @Inject constructor() : BoardFactory {
    override fun getBoard(numCards: Int, numColumns: Int): Board {
        return Board(
            columns = numColumns,
            cards = getGameCards(numCards)
        )
    }

    private fun getGameCards(numCards: Int): MutableList<GameCard> {
        val cards = mutableListOf<GameCard>()

        for (i in 1..numCards) {
            val generated = GameCard(id = i, image = "image: $i")
            if (generated !in cards) {
                cards.add(generated)
            }
        }
        return cards
    }
}
