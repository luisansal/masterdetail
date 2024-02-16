package com.company.masterdetail.domain.model

import java.io.Serializable

data class PokeModel(
    val id: Int = 0,
    val name: String,
    val url: String,
    val detail: PokeDetailModel? = null
) : Serializable
