package com.thecodechemist.pokedexapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.thecodechemist.pokedexapp.PokemonApplication
import com.thecodechemist.pokedexapp.adapter.PokemonAdapter
import com.thecodechemist.pokedexapp.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels {
        MainViewModelFactory((requireActivity().application as PokemonApplication).repository)
    }
    private var _binding: MainFragmentBinding? = null
    private val binding get() =_binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Display the up button
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        val recyclerView = binding.recyclerView
        val adapter = PokemonAdapter(viewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
        viewModel.allPokemon.observe(viewLifecycleOwner, { pokemon ->
            pokemon?.let { adapter.submitList(it)}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}