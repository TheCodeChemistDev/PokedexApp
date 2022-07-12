package com.thecodechemist.pokedexapp.db

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

//PokemonDao provides access to the PokemonRoomDatabase
class PokemonRepository (private val pokemonDao: PokemonDao) {

    val allPokemon: Flow<List<Pokemon>> = pokemonDao.getAllPokemon()

    @WorkerThread
    suspend fun insert(pokemon: Pokemon) {
        pokemonDao.insert(pokemon)
    }

}