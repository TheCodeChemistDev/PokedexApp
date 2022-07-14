package com.thecodechemist.pokedexapp.ui.main

import androidx.lifecycle.*
import com.thecodechemist.pokedexapp.db.Pokemon
import com.thecodechemist.pokedexapp.db.PokemonRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PokemonRepository) : ViewModel() {

    val allPokemon: LiveData<List<Pokemon>> = repository.allPokemon.asLiveData()
    lateinit var selectedPokemon: Pokemon

    fun insert(pokemon: Pokemon) = viewModelScope.launch {
        repository.insert(pokemon)
    }

    fun selectPokemon(pokemon: Pokemon) {
        selectedPokemon = pokemon
    }
}

class MainViewModelFactory(private val repository: PokemonRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}