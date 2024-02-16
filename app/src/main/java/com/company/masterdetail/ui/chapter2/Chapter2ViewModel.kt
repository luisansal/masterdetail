package com.company.masterdetail.ui.chapter2

import androidx.lifecycle.ViewModel
import com.company.masterdetail.data.repository.PokeRemoteRepository
import com.company.masterdetail.domain.model.PokeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Chapter2ViewModel @Inject constructor(private val pokeRemoteRepository: PokeRemoteRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(Chapter2UiState())
    val uiState: StateFlow<Chapter2UiState> = _uiState.asStateFlow()

    init {
        loadMore()
    }

    fun loadMore(offset: Int = 0){
        _uiState.update { uiState ->
            uiState.copy(
                isLoading = true
            )
        }
        CoroutineScope(Dispatchers.IO).launch {
            val pokemonList: List<PokeModel> = pokeRemoteRepository.getPokemon(offset)
            val lastList = _uiState.value.pokemonList
            val moreList = lastList.toMutableList()
            moreList.addAll(pokemonList)

            _uiState.update { uiState ->
                uiState.copy(
                    pokemonList = moreList,
                    isLoading = false
                )
            }
        }
    }
}