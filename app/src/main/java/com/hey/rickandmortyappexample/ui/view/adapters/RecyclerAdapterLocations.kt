package com.hey.rickandmortyappexample.ui.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hey.rickandmortyappexample.R
import com.hey.rickandmortyappexample.databinding.RowLocationBinding
import com.hey.rickandmortyappexample.domain.model.location.Location

class RecyclerAdapterLocations(
    var listObjects : MutableList<Location>,
    val itemClick : (Location) ->Unit)
    : ListAdapter<Location, RecyclerAdapterLocations.ViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Location>() {
            override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val location = getItem(position)

        holder.setup(location)

        holder.itemView.setOnClickListener {
            itemClick(location)
        }
    }

    class ViewHolder(private val binding : RowLocationBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setup(location: Location){
            binding.tvName.text = location.name
            binding.tvType.text = location.type
            binding.tvDimension.text = location.dimension
        }
    }

}