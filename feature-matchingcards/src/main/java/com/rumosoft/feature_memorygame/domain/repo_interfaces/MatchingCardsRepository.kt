package com.rumosoft.feature_memorygame.domain.repo_interfaces

import com.rumosoft.feature_memorygame.domain.entity.GameCard

interface MatchingCardsRepository {
    fun fetchCards(numCards: Int): List<GameCard>
}