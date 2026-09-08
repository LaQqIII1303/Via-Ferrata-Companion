package com.tomtom.viaferratacompanion.feature.routes

class RoutesRepository {

    suspend fun getRoutes(): List<ViaFerrata> = listOf(
        ViaFerrata(
            id = 1,
            name = "Donnerkogel",
            country = "Austria",
            difficulty = "D",
            description = "the most famous via ferrata in Austria",
            durationMinutes = 150,
            elevationGain = 350
        ), ViaFerrata(
            id = 2,
            name = "Tatabanya",
            country = "Hungary",
            difficulty = "C/D",
            description = "my first via ferrata",
            durationMinutes = 35,
            elevationGain = 50
        ), ViaFerrata(
            id = 3,
            name = "Cuha-Volgy",
            country = "Hungary",
            difficulty = "C/D",
            description = "Hungary famous ferrata",
            durationMinutes = 60,
            elevationGain = 100
        ), ViaFerrata(
            id = 4,
            name = "Csesznek",
            country = "Hungary",
            difficulty = "B",
            description = "ferrata in Hungary that I wanted to visit",
            durationMinutes = 60,
            elevationGain = 100
        ), ViaFerrata(
            id = 5,
            name = "Tarkanyferrata",
            country = "Hungary",
            difficulty = "E",
            description = "new one ferrata",
            durationMinutes = 120,
            elevationGain = 150
        )
    )

    suspend fun getRoute(id: Long): ViaFerrata? = getRoutes().firstOrNull { it.id == id }
}