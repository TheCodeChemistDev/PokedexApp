package com.thecodechemist.pokedexapp

import android.app.Application
import com.thecodechemist.pokedexapp.db.PokemonRepository
import com.thecodechemist.pokedexapp.db.PokemonRoomDatabase

class PokemonApplication : Application() {

    val database by lazy { PokemonRoomDatabase.getDatabase(this) }
    val repository by lazy { PokemonRepository(database.pokemonDao())}
}