package com.tomtom.viaferratacompanion.feature.routes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tomtom.viaferratacompanion.R
import com.tomtom.viaferratacompanion.databinding.ItemViaFerrataBinding

class RouteAdapter(
    private val routes: List<ViaFerrata>
) : RecyclerView.Adapter<RouteAdapter.RouteViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RouteViewHolder {
        return RouteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_via_ferrata, parent)
        )
    }

    override fun onBindViewHolder(
        holder: RouteViewHolder, position: Int
    ) {
        if (routes.size < position) return
        val route = routes[position]
        holder.binding.ferrataName.text = route.name
        holder.binding.countryName.text = route.country
        holder.binding.difficulty.text = route.difficulty
        holder.binding.duration.text = route.durationMinutes.toString()
        holder.binding.elevation.text = route.elevationGain.toString()
    }

    override fun getItemCount() = routes.size

    class RouteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemViaFerrataBinding.inflate(LayoutInflater.from(view.context))
    }
}