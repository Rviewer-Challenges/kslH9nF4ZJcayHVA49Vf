package com.rumosoft.feature_memorygame.domain.entity

import javax.inject.Inject

interface BoardFactory {
    fun getBoard(cards: List<GameCard>, numColumns: Int): Board
}

class BoardFactoryImpl @Inject constructor() : BoardFactory {
    override fun getBoard(cards: List<GameCard>, numColumns: Int): Board {
        return Board(
            columns = numColumns,
            cards = cards
        )
    }
}
