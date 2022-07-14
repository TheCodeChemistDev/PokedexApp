package com.thecodechemist.pokedexapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thecodechemist.pokedexapp.databinding.ListItemBinding
import com.thecodechemist.pokedexapp.db.Pokemon
import com.bumptech.glide.Glide
import com.thecodechemist.pokedexapp.ui.main.MainFragmentDirections
import com.thecodechemist.pokedexapp.ui.main.MainViewModel

class PokemonAdapter(private val viewModel: MainViewModel) : ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(PokemonComparator() ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder.create(parent, viewModel)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val currentPokemon = getItem(position)
        holder.bind(currentPokemon)

    }

    class PokemonViewHolder(private val binding: ListItemBinding, private val viewModel: MainViewModel) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: Pokemon) {
            binding.name.text = pokemon.name
            val ivSprite: ImageView = binding.sprite
            Glide.with(binding.root).load(pokemon.spriteUrl).into(ivSprite)
            binding.getDetails.setOnClickListener {
                viewModel.selectPokemon(pokemon)
                Navigation.findNavController(it).navigate(MainFragmentDirections.actionMainFragmentToPokemonDetailFragment(pokemon.id.toString()))
            }
        }

        companion object {
            fun create(parent: ViewGroup, mainViewModel: MainViewModel): PokemonViewHolder {
                val binding: ListItemBinding = ListItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false)
                return PokemonViewHolder(binding, mainViewModel)
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