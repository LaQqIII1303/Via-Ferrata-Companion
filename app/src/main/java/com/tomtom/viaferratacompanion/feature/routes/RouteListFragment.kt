package com.tomtom.viaferratacompanion.feature.routes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.tomtom.viaferratacompanion.R
import com.tomtom.viaferratacompanion.databinding.FragmentRouteListBinding
import kotlinx.coroutines.launch

class RouteListFragment : Fragment(R.layout.fragment_route_list) {

    private lateinit var binding: FragmentRouteListBinding
    private val viewModel: RouteListViewModel by viewModels()
    private val adapter = RouteAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRouteListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.routes.adapter = adapter
        binding.routes.layoutManager = LinearLayoutManager(context)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.routes.collect { routes ->
                    adapter.updateRoutes(routes)
                }
            }
        }
    }
}