package com.company.masterdetail.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.company.masterdetail.domain.entities.PokeEntity
import com.company.masterdetail.domain.entities.PokemonWithDetail

@Dao
interface PokeLocalDao {
    @Transaction
    @Query("SELECT * FROM PokeEntity")
    fun getPokemon(): List<PokemonWithDetail>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg pokeEntity: PokeEntity)
}