package com.rumosoft.feature_memorygame.domain.entity

data class Board(val cards: List<GameCard>, val columns: Int)

val Board.numPairs: Int
    get() = cards.size / 2

fun Board.flipCard(card: GameCard): Board =
    copy(cards = cards.map {
        if (it.id == card.id) {
            it.copy(face = it.face.next)
        } else {
            it
        }
    })

fun Board.matched(characterId: Int): Board {
    return copy(cards = cards.map {
        if (it.characterId == characterId) {
            it.copy(matched = true)
        } else {
            it
        }
    })
}

fun Board.resetCards(first: GameCard, second: GameCard): Board =
    copy(cards = cards.map {
        if (it.id == first.id || it.id == second.id) {
            it.copy(face = it.face.next)
        } else {
            it
        }
    })
