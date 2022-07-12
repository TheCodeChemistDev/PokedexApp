package com.thecodechemist.pokedexapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
data class Pokemon (
    @PrimaryKey @ColumnInfo(name = "id") val id: Integer,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "spriteUrl") val spriteUrl: String )