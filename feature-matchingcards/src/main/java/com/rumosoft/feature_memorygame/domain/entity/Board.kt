package com.rumosoft.feature_memorygame.domain.entity

data class Board(val cards: List<GameCard>, val columns: Int)

fun Board.flipCard(card: GameCard): Board {
    return copy(cards = cards.map {
        if (it == card) {
            card.copy(face = card.face.next)
        } else {
            it
        }
    })
}
