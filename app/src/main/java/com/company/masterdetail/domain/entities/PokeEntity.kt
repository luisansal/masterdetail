package com.company.masterdetail.domain.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class PokeEntity(
    @PrimaryKey
    val id: Int = 0,
    val name: String,
    val url: String,
)

data class PokemonWithDetail(
    @Embedded val pokemon: PokeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonId"
    )
    val detail: PokeDetailEntity
)