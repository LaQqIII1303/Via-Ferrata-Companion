package com.tomtom.viaferratacompanion.feature.routes

sealed class RouteListState {
    data object Loading : RouteListState()
    data class Success(val routes: List<ViaFerrata>) : RouteListState()
    data class Error(val message: String) : RouteListState()
}