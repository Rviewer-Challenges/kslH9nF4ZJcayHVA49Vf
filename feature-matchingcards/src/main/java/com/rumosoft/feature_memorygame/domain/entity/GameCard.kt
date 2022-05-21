package com.rumosoft.feature_memorygame.domain.entity

import com.rumosoft.feature_memorygame.presentation.component.CardFace

data class GameCard(
    val id: Int,
    val image: String,
    val face: CardFace = CardFace.Back,
    val matched: Boolean = false
)