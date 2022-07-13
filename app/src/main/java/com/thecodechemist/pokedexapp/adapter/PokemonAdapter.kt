package com.thecodechemist.pokedexapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thecodechemist.pokedexapp.databinding.ListItemBinding
import com.thecodechemist.pokedexapp.db.Pokemon
import com.bumptech.glide.Glide
import com.thecodechemist.pokedexapp.ui.main.MainFragmentDirections

class PokemonAdapter : ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(PokemonComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val currentPokemon = getItem(position)
        holder.bind(currentPokemon.name, currentPokemon.spriteUrl)

    }

    class PokemonViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemonName: String, pokemonSpriteUrl: String) {
            binding.name.text = pokemonName
            val ivSprite: ImageView = binding.sprite
            Glide.with(binding.root).load(pokemonSpriteUrl).into(ivSprite)
            binding.getDetails.setOnClickListener {
                Navigation.findNavController(it).navigate(MainFragmentDirections.actionMainFragmentToPokemonDetailFragment())
            }
        }

        companion object {
            fun create(parent: ViewGroup): PokemonViewHolder {
                val binding: ListItemBinding = ListItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false)
                return PokemonViewHolder(binding)
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