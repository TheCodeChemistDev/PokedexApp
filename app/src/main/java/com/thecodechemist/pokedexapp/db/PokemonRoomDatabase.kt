package com.thecodechemist.pokedexapp.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.thecodechemist.pokedexapp.network.PokemonApi
import com.thecodechemist.pokedexapp.network.PokemonApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import org.json.JSONTokener

@Database(entities = arrayOf(Pokemon::class), version = 1, exportSchema = false)
public abstract class PokemonRoomDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    private class PokemonDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.pokemonDao())
                }
            }
        }

        suspend fun populateDatabase(pokemonDao: PokemonDao) {
            //Delete the current data from the database
            pokemonDao.deleteAll()

            //TODO: Update to iterate through all Pokemon
            val pokemonList: MutableList<Pokemon> = mutableListOf<Pokemon>()
            for(i in 1..20) {
                try {
                    Log.i("Getting Pokemon ID", i.toString())
                    var apiResponse = PokemonApi.retrofitService.getPokemonById(i.toString())
                    var jsonResultsObject = JSONTokener(apiResponse).nextValue() as JSONObject
                    //TODO: Capitalise first letter of the Pokemon Name
                    var pokemonName = jsonResultsObject.getString("name")
                    var pokemonSpriteUrlsObject = jsonResultsObject.getJSONObject("sprites")
                    var pokemonSpriteUrl = pokemonSpriteUrlsObject.getString("front_default")
                    var pokemonHeight = jsonResultsObject.getInt("height")
                    var pokemonWeight = jsonResultsObject.getInt("weight")
                    var newPokemon = Pokemon(
                        i as Integer,
                        pokemonName,
                        pokemonSpriteUrl,
                        pokemonHeight as Integer,
                        pokemonWeight as Integer)
                    pokemonList.add(newPokemon)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            pokemonDao.insertAll(pokemonList)
        }

    }

    //Singleton Database to prevent multiple instances
    companion object {

        @Volatile
        private var INSTANCE: PokemonRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): PokemonRoomDatabase {
            //Create the database if it does not already exist
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PokemonRoomDatabase::class.java,
                    "pokemon_database"
                ).addCallback(PokemonDatabaseCallback(scope)).build()
                INSTANCE = instance

                instance

            }
        }
    }
}