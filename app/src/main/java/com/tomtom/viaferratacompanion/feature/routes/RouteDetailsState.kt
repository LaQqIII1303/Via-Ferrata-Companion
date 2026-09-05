package com.tomtom.viaferratacompanion.feature.routes

sealed class RouteDetailsState {
    data object Loading : RouteDetailsState()
    data class Success(val viaFerrata: ViaFerrata) : RouteDetailsState()
    data class Error(val message: String) : RouteDetailsState()
}