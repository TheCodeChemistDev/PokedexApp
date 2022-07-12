package com.thecodechemist.pokedexapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon_table ORDER BY id ASC")
    fun getAllPokemon(): List<Pokemon>

    @Query("SELECT * FROM pokemon_table WHERE id = :id LIMIT 1")
    fun getPokemonById(id: Integer): Pokemon

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pokemon: Pokemon)

    @Query("DELETE FROM pokemon_table")
    suspend fun deleteAll()

}