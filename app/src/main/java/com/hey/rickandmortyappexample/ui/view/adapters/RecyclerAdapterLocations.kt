package com.hey.rickandmortyappexample.ui.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hey.rickandmortyappexample.R
import com.hey.rickandmortyappexample.databinding.RowLocationBinding
import com.hey.rickandmortyappexample.domain.model.location.Location

class RecyclerAdapterLocations(var listObjects : MutableList<Location>, val listener : (Location) ->Unit)
    : RecyclerView.Adapter<RecyclerAdapterLocations.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_location, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val location = listObjects[position]

        holder.setup(location)

        holder.itemView.setOnClickListener {
            listener(location)
        }
    }

    override fun getItemCount() = listObjects.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RowLocationBinding.bind(view)

        fun setup(location: Location){
            binding.tvName.text = location.name
            binding.tvType.text = location.type
            binding.tvDimension.text = location.dimension
        }
    }

}