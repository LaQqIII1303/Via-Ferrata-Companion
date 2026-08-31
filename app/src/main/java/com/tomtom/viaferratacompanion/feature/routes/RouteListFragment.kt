package com.tomtom.viaferratacompanion.feature.routes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tomtom.viaferratacompanion.R
import com.tomtom.viaferratacompanion.databinding.FragmentRouteListBinding

class RouteListFragment : Fragment(R.layout.fragment_route_list) {

    private lateinit var binding: FragmentRouteListBinding
    private val viewModel: RouteListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRouteListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.routes.adapter = RouteAdapter(viewModel.routes)
        binding.routes.layoutManager = LinearLayoutManager(context)
    }
}