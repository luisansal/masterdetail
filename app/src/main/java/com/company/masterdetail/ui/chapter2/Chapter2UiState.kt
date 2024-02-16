package com.company.masterdetail.ui.chapter2

import com.company.masterdetail.domain.model.PokeModel
import com.company.masterdetail.ui.utils.EMPTY

data class Chapter2UiState(
    val navigateToDetail: Boolean = false,
    val pokemonList: List<PokeModel> = emptyList(),
    val isLoading: Boolean = false,
)