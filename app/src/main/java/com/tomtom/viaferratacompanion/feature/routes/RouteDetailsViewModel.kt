package com.tomtom.viaferratacompanion.feature.routes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RouteDetailsViewModel(private val routeId: Long) : ViewModel() {

    private val repository = RoutesRepository()

    private val _route = MutableStateFlow<RouteDetailsState>(RouteDetailsState.Loading)
    val route: StateFlow<RouteDetailsState> = _route.asStateFlow()

    init {
        _route.value = RouteDetailsState.Loading
        viewModelScope.launch {
            _route.value = repository.getRoute(routeId)?.let(RouteDetailsState::Success)
                ?: RouteDetailsState.Error("Via ferrata not found")
        }
    }

    companion object {
        val ROUTE_ID_KEY = CreationExtras.Key<Long>()

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val routeId = this[ROUTE_ID_KEY] as Long
                RouteDetailsViewModel(routeId)
            }
        }
    }
}
