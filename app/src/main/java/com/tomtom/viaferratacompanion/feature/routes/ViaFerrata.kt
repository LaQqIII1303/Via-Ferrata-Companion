package com.tomtom.viaferratacompanion.feature.routes

data class ViaFerrata(
    val id: Long,
    val name: String,
    val country: String,
    val difficulty: String,
    val durationMinutes: Int,
    val elevationGain: Int
)
