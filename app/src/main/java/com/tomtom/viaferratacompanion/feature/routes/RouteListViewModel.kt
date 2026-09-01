package com.tomtom.viaferratacompanion.feature.routes

import androidx.lifecycle.ViewModel

class RouteListViewModel : ViewModel() {

    val routes = listOf(
        ViaFerrata(
            id = 1,
            name = "Donnerkogel",
            country = "Austria",
            difficulty = "D",
            durationMinutes = 150,
            elevationGain = 350
        ), ViaFerrata(
            id = 2,
            name = "Tatabanya",
            country = "Hungary",
            difficulty = "C/D",
            durationMinutes = 35,
            elevationGain = 50
        ), ViaFerrata(
            id = 3,
            name = "Cuha-Volgy",
            country = "Hungary",
            difficulty = "C/D",
            durationMinutes = 60,
            elevationGain = 100
        ), ViaFerrata(
            id = 4,
            name = "Csesznek",
            country = "Hungary",
            difficulty = "B",
            durationMinutes = 60,
            elevationGain = 100
        ), ViaFerrata(
            id = 5,
            name = "Tarkanyferrata",
            country = "Hungary",
            difficulty = "E",
            durationMinutes = 120,
            elevationGain = 150
        )
    )
}