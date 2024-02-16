package com.company.masterdetail.data.mappers

import com.company.masterdetail.BuildConfig
import com.company.masterdetail.data.remote.responses.PokeDetailResponse
import com.company.masterdetail.data.remote.responses.PokeResponse
import com.company.masterdetail.domain.remote.pokemonUrl
import com.company.masterdetail.domain.model.PokeDetailModel
import com.company.masterdetail.domain.model.PokeModel
import com.company.masterdetail.domain.model.DetailTypeModel
import com.company.masterdetail.ui.utils.EMPTY
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

private fun getCleanId(url: String): Int {
    val decodeUrl = URLDecoder.decode(url, StandardCharsets.UTF_8.toString())
    return decodeUrl.replace(BuildConfig.API_BASE_URL, EMPTY)
        .replace("$pokemonUrl/", EMPTY)
        .dropLast(1)
        .toInt()
}

fun PokeResponse.toModel(): List<PokeModel> {
    return this.results.map { pokeElement ->
        PokeModel(
            id = getCleanId(pokeElement.url),
            name = pokeElement.name,
            url = URLEncoder.encode(pokeElement.url, StandardCharsets.UTF_8.toString())
        )
    }
}

fun PokeDetailResponse.toModel(): PokeDetailModel {
    return PokeDetailModel(
        id = this.id,
        name = this.name,
        frontDefaultSprite = URLEncoder.encode(this.sprites.frontDefault, StandardCharsets.UTF_8.toString()),
        height = this.height,
        weight = this.weight,
        types = this.types.map {
            DetailTypeModel(name = it.type.name, url = URLEncoder.encode(it.type.url, StandardCharsets.UTF_8.toString()))
        }
    )
}
