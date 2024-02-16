package com.company.masterdetail.data.remote.responses

import com.google.gson.annotations.SerializedName

data class PokeElement(
    @SerializedName("name") var name: String,
    @SerializedName("url") var url: String
)

data class PokeResponse(
    @SerializedName("next") val next: String,
    @SerializedName("results") val results: List<PokeElement>
)