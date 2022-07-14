package com.thecodechemist.pokedexapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.thecodechemist.pokedexapp.PokemonApplication
import com.thecodechemist.pokedexapp.databinding.FragmentPokemonDetailBinding


class PokemonDetailFragment : Fragment() {

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels {
        MainViewModelFactory((requireActivity().application as PokemonApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Display the up button
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Display the Pokemon details
        Glide.with(binding.root).load(viewModel.selectedPokemon.spriteUrl).into(binding.sprite)
        binding.pokemonId.text = "#${viewModel.selectedPokemon.id}"
        binding.name.text = "Name: ${viewModel.selectedPokemon.name}"
        binding.height.text = "Height: ${viewModel.selectedPokemon.height}cm"
        binding.weight.text = "Weight: ${viewModel.selectedPokemon.weight}kg"

    }


}