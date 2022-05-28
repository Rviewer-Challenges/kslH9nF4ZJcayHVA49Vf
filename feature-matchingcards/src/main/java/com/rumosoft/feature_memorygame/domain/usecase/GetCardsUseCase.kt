package com.rumosoft.feature_memorygame.domain.usecase

import com.rumosoft.feature_memorygame.domain.entity.GameCard
import com.rumosoft.feature_memorygame.domain.repo_interfaces.MatchingCardsRepository
import javax.inject.Inject

class GetCardsUseCase @Inject constructor(
    private val repository: MatchingCardsRepository
) {
    operator fun invoke(numCards: Int): List<GameCard> =
        repository.fetchCards(numCards)
}
