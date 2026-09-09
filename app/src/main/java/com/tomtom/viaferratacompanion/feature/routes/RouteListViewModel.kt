package com.tomtom.viaferratacompanion.feature.routes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.tomtom.viaferratacompanion.ViaFerrataApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RouteListViewModel(private val repository: RoutesRepository) : ViewModel() {
    private val _routes = MutableStateFlow<RouteListState>(RouteListState.Loading)
    val routes: StateFlow<RouteListState> = _routes.asStateFlow()

    private val _filter = MutableStateFlow(RouteFilter())
    val filter: StateFlow<RouteFilter> = _filter.asStateFlow()

    val filteredRoutes = combine(_routes, _filter) { routeState, filter ->
        when (routeState) {
            is RouteListState.Success -> {
                val finalRoutes = routeState.routes.filter { route ->
                    (filter.difficulty.isNullOrEmpty() || route.difficulty == filter.difficulty)
                            && (filter.country.isNullOrEmpty() || route.country == filter.country)
                            && (filter.maxDurationMinutes?.let { route.durationMinutes <= it }
                        ?: true)
                }
                RouteListState.Success(finalRoutes)
            }

            else -> routeState
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = RouteListState.Loading
    )

    init {
        loadRoutes()
    }

    fun retry() {
        loadRoutes()
    }

    fun setFilter(filter: RouteFilter) {
        _filter.value = filter
    }

    private fun loadRoutes() {
        _routes.value = RouteListState.Loading
        viewModelScope.launch {
            _routes.value = try {
                RouteListState.Success(repository.getRoutes())
            } catch (e: Exception) {
                RouteListState.Error(e.message ?: "$e")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                Log.i("!@#", "RouteListViewModel factory")
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ViaFerrataApplication)
                RouteListViewModel(application.appContainer.repository)
            }
        }
    }
}