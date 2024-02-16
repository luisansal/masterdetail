package com.company.masterdetail.data.mappers

import com.company.masterdetail.domain.entities.DetailTypeEntity
import com.company.masterdetail.domain.entities.PokeDetailEntity
import com.company.masterdetail.domain.entities.PokeEntity
import com.company.masterdetail.domain.entities.PokemonWithDetail
import com.company.masterdetail.domain.model.DetailTypeModel
import com.company.masterdetail.domain.model.PokeDetailModel
import com.company.masterdetail.domain.model.PokeModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun PokeModel.toEntity(): PokeEntity {
    return PokeEntity(
        id = this.id,
        name = this.name,
        url = URLEncoder.encode(this.url, StandardCharsets.UTF_8.toString())
    )
}

fun PokeDetailModel.toEntity(): PokeDetailEntity {
    return PokeDetailEntity(
        name = this.name,
        frontDefaultSprite = this.frontDefaultSprite,
        height = this.height,
        weight = this.weight
    )
}

fun List<PokemonWithDetail>.toModel(): List<PokeModel> {
    return this.map {
        PokeModel(
            id = it.pokemon.id,
            name = it.pokemon.name,
            url = it.pokemon.url,
            detail = it.detail.toModel()
        )
    }
}

fun PokeDetailEntity.toModel(): PokeDetailModel {
    return PokeDetailModel(
        id = this.id,
        name = this.name,
        frontDefaultSprite = this.frontDefaultSprite,
        height = this.height,
        weight = this.weight,
        types = null
    )
}

fun DetailTypeModel.toEntity(): DetailTypeEntity {
    return DetailTypeEntity(
        name = this.name,
        url = this.url
    )
}

@JvmName("DetailTypeEntity")
fun List<DetailTypeEntity>.toModel(): List<DetailTypeModel> {
    return this.map {
        DetailTypeModel(
            name = it.name,
            url = it.url
        )
    }
}


