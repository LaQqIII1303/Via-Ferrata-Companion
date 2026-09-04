package com.tomtom.viaferratacompanion.feature.routes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tomtom.viaferratacompanion.R
import com.tomtom.viaferratacompanion.databinding.ItemViaFerrataBinding

class RouteAdapter(
    diffUtil: DiffUtil.ItemCallback<ViaFerrata> = RouteDiffCallback(),
    private val onRouteClick: (ViaFerrata) -> Unit
) : ListAdapter<ViaFerrata, RouteAdapter.RouteViewHolder>(diffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RouteViewHolder {
        return RouteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_via_ferrata, parent, false
            )
        )
    }

    override fun onBindViewHolder(
        holder: RouteViewHolder, position: Int
    ) {
        val route = currentList[position]
        val context = holder.itemView.context
        holder.binding.ferrataName.text = route.name
        holder.binding.countryName.text = route.country
        holder.binding.difficulty.text = context.getString(
            R.string.route_difficulty, route.difficulty
        )
        holder.binding.duration.text = context.getString(
            R.string.route_duration, route.durationMinutes
        )
        holder.binding.elevation.text = context.getString(
            R.string.route_elevation, route.elevationGain
        )
        holder.itemView.setOnClickListener { onRouteClick(route) }
    }

    class RouteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemViaFerrataBinding.bind(view)
    }
}