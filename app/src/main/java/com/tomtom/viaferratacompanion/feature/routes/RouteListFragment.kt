package com.tomtom.viaferratacompanion.feature.routes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tomtom.viaferratacompanion.R
import com.tomtom.viaferratacompanion.databinding.FragmentRouteListBinding

class RouteListFragment : Fragment(R.layout.fragment_route_list) {

    private lateinit var binding: FragmentRouteListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return FragmentRouteListBinding.inflate(inflater).root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.routes.adapter = RouteAdapter(routes)
        binding.routes.layoutManager = LinearLayoutManager(context)
    }

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