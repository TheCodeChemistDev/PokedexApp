package com.thecodechemist.pokedexapp

import android.app.Application
import com.thecodechemist.pokedexapp.db.PokemonRepository
import com.thecodechemist.pokedexapp.db.PokemonRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PokemonApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())


    //database and repository are only created when they are required, rather than when the app starts
    val database by lazy { PokemonRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { PokemonRepository(database.pokemonDao())}
}