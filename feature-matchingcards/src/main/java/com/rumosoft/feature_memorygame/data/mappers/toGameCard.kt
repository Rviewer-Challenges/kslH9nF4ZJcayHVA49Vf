package com.rumosoft.feature_memorygame.data.mappers

import com.rumosoft.feature_memorygame.domain.entity.GameCard
import com.rumosoft.library_simpsons_api.data.model.CardDto

fun CardDto.toGameCard(id: Int): GameCard =
    GameCard(
        id = id,
        name = name,
        characterId = characterId,
        image = imageUrl
    )