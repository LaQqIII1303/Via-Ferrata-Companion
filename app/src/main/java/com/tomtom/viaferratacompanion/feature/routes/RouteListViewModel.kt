package com.tomtom.viaferratacompanion.feature.routes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RouteListViewModel : ViewModel() {

    private val repository = RoutesRepository()

    private val _routes = MutableStateFlow<RouteListState>(RouteListState.Loading)
    val routes: StateFlow<RouteListState> = _routes.asStateFlow()

    init {
        loadRoutes()
    }

    fun retry() {
        loadRoutes()
    }

    private fun loadRoutes() {
        _routes.value = RouteListState.Loading
        viewModelScope.launch {
            _routes.value = try {
                delay(1000)
                RouteListState.Success(repository.getRoutes())
            } catch (e: Exception) {
                RouteListState.Error(e.message ?: "$e")
            }
        }
    }
}