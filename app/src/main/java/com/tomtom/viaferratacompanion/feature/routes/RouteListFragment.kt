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
    private lateinit var adapter: RouteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRouteListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RouteAdapter { route ->
            val fragment = RouteDetailsFragment.newInstance(route.id)
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null).commit()
        }

        binding.routes.adapter = adapter
        binding.routes.layoutManager = LinearLayoutManager(context)

        binding.retryButton.setOnClickListener {
            viewModel.retry()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.routes.collect { routeListState ->
                    when (routeListState) {
                        is RouteListState.Error -> {
                            showError(routeListState.message)
                        }

                        RouteListState.Loading -> {
                            showLoading()
                        }

                        is RouteListState.Success -> {
                            showRoutes(routeListState.routes)
                        }
                    }
                }
            }
        }
    }

    private fun showLoading() {
        binding.errorText.visibility = View.GONE
        binding.routes.visibility = View.GONE
        binding.retryButton.visibility = View.GONE
        binding.loadingProgressBar.visibility = View.VISIBLE
    }

    private fun showError(message: String) {
        binding.loadingProgressBar.visibility = View.GONE
        binding.routes.visibility = View.GONE
        binding.errorText.text = message
        binding.errorText.visibility = View.VISIBLE
        binding.retryButton.visibility = View.VISIBLE
    }

    private fun showRoutes(routes: List<ViaFerrata>) {
        binding.errorText.visibility = View.GONE
        binding.loadingProgressBar.visibility = View.GONE
        binding.retryButton.visibility = View.GONE
        binding.routes.visibility = View.VISIBLE
        adapter.submitList(routes)
    }
}