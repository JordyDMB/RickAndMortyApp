package com.hey.rickandmortyappexample.ui.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hey.rickandmortyappexample.R
import com.hey.rickandmortyappexample.databinding.RowCharacterBinding
import com.hey.rickandmortyappexample.domain.model.character.Character
import com.hey.rickandmortyappexample.ui.view.adapters.RecyclerAdapterCharacters.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecyclerAdapterCharacters(var listObjects : MutableList<Character>, val listener : (Character) ->Unit)
    : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_character, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = listObjects[position]

        holder.setupCharacter(character)

        holder.itemView.setOnClickListener {
            listener(character)
        }
    }

    override fun onViewRecycled(holder: ViewHolder) {
        holder.clearImage()
        super.onViewRecycled(holder)
    }

    override fun getItemCount() = listObjects.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = RowCharacterBinding.bind(view)

        fun setupCharacter(character: Character){
            binding.tvName.text = character.name
            binding.tvSpecie.text = character.species
            binding.tvStatus.text = character.status
            setImageCharacter(character)
        }

        private fun setImageCharacter(character: Character){
            if (character.bitmap==null){
                CoroutineScope(Dispatchers.IO).launch {
                    val bitmap = character.getImageByUrl(itemView.context)
                    withContext(Dispatchers.Main){
                        binding.imgCharacter.setImageBitmap(bitmap)
                    }
                }
            }else{
                binding.imgCharacter.setImageBitmap(character.bitmap)
            }
        }

        fun clearImage(){
            binding.imgCharacter.setImageBitmap(null)
        }

    }
}