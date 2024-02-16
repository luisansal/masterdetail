package com.company.masterdetail.ui.chapter3

import com.company.masterdetail.domain.model.PokeModel
import com.company.masterdetail.ui.utils.EMPTY

data class Chapter3UiState(
    val navigateToDetail: Boolean = false,
    val recipes: List<PokeModel> = emptyList(),
    var txtQuery: String = EMPTY
)