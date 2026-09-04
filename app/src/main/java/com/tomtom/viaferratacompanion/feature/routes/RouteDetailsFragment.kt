package com.tomtom.viaferratacompanion.feature.routes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tomtom.viaferratacompanion.R
import com.tomtom.viaferratacompanion.databinding.FragmentRouteDetailsBinding

class RouteDetailsFragment : Fragment(R.layout.fragment_route_details) {

    private lateinit var binding: FragmentRouteDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRouteDetailsBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        fun newInstance(routeId: Long) = RouteDetailsFragment().apply {
            arguments = Bundle().apply {
                putLong("route_id", routeId)
            }
        }
    }
}