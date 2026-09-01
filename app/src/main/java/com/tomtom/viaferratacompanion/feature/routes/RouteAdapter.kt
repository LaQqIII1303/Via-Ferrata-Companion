package com.tomtom.viaferratacompanion.feature.routes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tomtom.viaferratacompanion.R
import com.tomtom.viaferratacompanion.databinding.ItemViaFerrataBinding

class RouteAdapter : RecyclerView.Adapter<RouteAdapter.RouteViewHolder>() {

    private var _routes: List<ViaFerrata> = emptyList()

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
        val route = _routes[position]
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
    }

    override fun getItemCount() = _routes.size

    fun updateRoutes(routes: List<ViaFerrata>) {
        _routes = routes
        notifyDataSetChanged()
    }

    class RouteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemViaFerrataBinding.bind(view)
    }
}