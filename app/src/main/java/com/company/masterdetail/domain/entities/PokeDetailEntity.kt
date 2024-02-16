package com.company.masterdetail.domain.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class PokeDetailEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val pokemonId: Int? = 0,
    val name: String?,
    val frontDefaultSprite: String?,
    val height: Int?,
    val weight: Int?,
)

@Entity
data class DetailTypeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val detailId: Long? = 0,
    val name: String,
    val url: String
)

data class DetailWithTypes(
    @Embedded val pokeDetailEntity: PokeDetailEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "detailId"
    )
    val types: List<DetailTypeEntity>
)