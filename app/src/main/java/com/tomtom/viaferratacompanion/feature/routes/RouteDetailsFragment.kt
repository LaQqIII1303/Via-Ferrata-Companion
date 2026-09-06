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
import androidx.lifecycle.viewmodel.MutableCreationExtras
import com.tomtom.viaferratacompanion.R
import com.tomtom.viaferratacompanion.databinding.FragmentRouteDetailsBinding
import kotlinx.coroutines.launch

class RouteDetailsFragment : Fragment(R.layout.fragment_route_details) {

    private val routeId: Long by lazy {
        requireArguments().getLong(ARG_ROUTE_ID)
    }

    private lateinit var binding: FragmentRouteDetailsBinding

    private val viewModel: RouteDetailsViewModel by viewModels(
        factoryProducer = { RouteDetailsViewModel.Factory },
        extrasProducer = {
            MutableCreationExtras(defaultViewModelCreationExtras).apply {
                set(RouteDetailsViewModel.ROUTE_ID_KEY, routeId)
            }
        })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRouteDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.route.collect { routeState ->
                    when (routeState) {
                        is RouteDetailsState.Success -> {
                            showViaFerrataDetails(routeState.viaFerrata)
                        }

                        RouteDetailsState.Loading -> {
                            showLoading()
                        }

                        is RouteDetailsState.Error -> {
                            showError(routeState.message)
                        }
                    }
                }
            }
        }
    }

    private fun showError(message: String) {
        binding.errorText.text = message
        binding.loadingProgressBar.visibility = View.GONE
        binding.viaFerrataDetails.visibility = View.GONE
        binding.errorText.visibility = View.VISIBLE
        binding.backButton.visibility = View.VISIBLE
    }

    private fun showLoading() {
        binding.loadingProgressBar.visibility = View.VISIBLE
        binding.viaFerrataDetails.visibility = View.GONE
        binding.errorText.visibility = View.GONE
        binding.backButton.visibility = View.GONE
    }

    private fun showViaFerrataDetails(viaFerrata: ViaFerrata) {
        binding.routeName.text = viaFerrata.name
        binding.routeCountry.text = getString(R.string.route_country, viaFerrata.country)
        binding.routeDifficulty.text = getString(
            R.string.route_difficulty, viaFerrata.difficulty
        )
        binding.routeDuration.text = getString(
            R.string.route_duration, viaFerrata.durationMinutes
        )
        binding.routeElevation.text = getString(
            R.string.route_elevation, viaFerrata.elevationGain
        )
        binding.viaFerrataDetails.visibility = View.VISIBLE
        binding.loadingProgressBar.visibility = View.GONE
        binding.errorText.visibility = View.GONE
        binding.backButton.visibility = View.VISIBLE
    }

    companion object {
        private const val ARG_ROUTE_ID = "route_id"

        fun newInstance(routeId: Long) = RouteDetailsFragment().apply {
            arguments = Bundle().apply {
                putLong(ARG_ROUTE_ID, routeId)
            }
        }
    }
}