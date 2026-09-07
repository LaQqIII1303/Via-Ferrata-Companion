package com.tomtom.viaferratacompanion.feature.routes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.tomtom.viaferratacompanion.ViaFerrataApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RouteListViewModel(private val repository: RoutesRepository) : ViewModel() {
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