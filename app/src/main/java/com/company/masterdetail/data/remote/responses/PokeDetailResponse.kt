package com.company.masterdetail.data.remote.responses

import com.google.gson.annotations.SerializedName

data class SpritesElement(
    @SerializedName("front_default") val frontDefault: String,
)

data class TypeElement(
    @SerializedName("slot") val slot: String,
    @SerializedName("type") val type: SubTypeElement,
)

data class SubTypeElement(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
)

data class PokeDetailResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("sprites") val sprites: SpritesElement,
    @SerializedName("types") val types: List<TypeElement>
)