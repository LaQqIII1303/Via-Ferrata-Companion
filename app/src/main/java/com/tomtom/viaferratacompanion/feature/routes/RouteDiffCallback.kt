package com.tomtom.viaferratacompanion.feature.routes

import androidx.recyclerview.widget.DiffUtil

class RouteDiffCallback : DiffUtil.ItemCallback<ViaFerrata>() {

    override fun areItemsTheSame(
        oldItem: ViaFerrata, newItem: ViaFerrata
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: ViaFerrata, newItem: ViaFerrata
    ): Boolean = oldItem == newItem
}