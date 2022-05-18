package com.rumosoft.feature_memorygame.domain.entity

enum class Level(val value: Int) {
    Undefined(0),
    Easy(1),
    Medium(2),
    Difficult(3);

    companion object {
        fun getByValue(value: Int?) = values().firstOrNull { it.value == value }
    }
}
