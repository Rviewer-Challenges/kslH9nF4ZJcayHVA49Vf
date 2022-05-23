package com.rumosoft.feature_memorygame.data.repository

import com.rumosoft.feature_memorygame.data.mappers.toGameCard
import com.rumosoft.feature_memorygame.domain.entity.GameCard
import com.rumosoft.feature_memorygame.domain.repo_interfaces.MatchingCardsRepository
import com.rumosoft.library_simpsons_api.data.local.SimpsonsLocalDataSource
import com.rumosoft.library_simpsons_api.data.model.CardDto
import javax.inject.Inject

class MatchingCardsRepositoryImpl @Inject constructor(
    private val simpsonsLocalDataSource: SimpsonsLocalDataSource
) : MatchingCardsRepository {
    override fun fetchCards(numCards: Int): List<GameCard> {
        val fetchedCards: List<CardDto> = simpsonsLocalDataSource.fetchCards(numCards / 2)
        val cards: List<CardDto> = (fetchedCards + fetchedCards).shuffled()
        var id = 0
        return cards.map {
            id++
            it.toGameCard(id)
        }
    }
}