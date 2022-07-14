package com.thecodechemist.pokedexapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon_table ORDER BY id ASC")
    fun getAllPokemon(): Flow<List<Pokemon>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: Pokemon)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemon: List<Pokemon>)

    @Query("DELETE FROM pokemon_table")
    suspend fun deleteAll()


}