package com.tomtom.viaferratacompanion.feature.routes

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RouteDetailsViewModel(
    private val routeId: Long, savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val repository = RoutesRepository()

    private val _route: MutableStateFlow<RouteDetailsState> =
        savedStateHandle.getMutableStateFlow("route", RouteDetailsState.Loading)
    val route: StateFlow<RouteDetailsState> = _route.asStateFlow()

    init {
        _route.value = RouteDetailsState.Loading
        viewModelScope.launch {
            try {
                val viaFerrata = repository.getRoute(routeId)
                _route.value = RouteDetailsState.Success(viaFerrata)
            } catch (e: Exception) {
                _route.value = RouteDetailsState.Error("${e.message}")
            }
        }
    }

    companion object {
        val ROUTE_ID_KEY = CreationExtras.Key<Long>()

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val routeId = this[ROUTE_ID_KEY] as Long
                RouteDetailsViewModel(routeId, savedStateHandle)
            }
        }
    }
}