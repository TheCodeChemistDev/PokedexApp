package com.thecodechemist.pokedexapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thecodechemist.pokedexapp.R
import com.thecodechemist.pokedexapp.db.Pokemon

class PokemonAdapter : ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(PokemonComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val currentPokemon = getItem(position)
        holder.bind(currentPokemon.name)
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvPokemonName: TextView = itemView.findViewById(R.id.name)

        fun bind(pokemonName: String) {
            tvPokemonName.text = pokemonName
        }

        companion object {
            fun create(parent: ViewGroup): PokemonViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item, parent, false)
                return PokemonViewHolder(view)
            }
        }
    }

    class PokemonComparator : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }

    }

}