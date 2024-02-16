package com.company.masterdetail.domain.model

import java.io.Serializable

data class PokeDetailModel(
    val id: Long?,
    val name: String?,
    val frontDefaultSprite: String?,
    val height: Int?,
    val weight: Int?,
    val types: List<DetailTypeModel>?,
) : Serializable

data class DetailTypeModel(
    val name: String,
    val url: String
) : Serializable

