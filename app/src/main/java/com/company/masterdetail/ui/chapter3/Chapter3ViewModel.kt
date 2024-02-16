package com.company.masterdetail.ui.chapter3

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
class Chapter3ViewModel @Inject constructor(private val pokeRemoteRepository: PokeRemoteRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(Chapter3UiState())
    val uiState: StateFlow<Chapter3UiState> = _uiState.asStateFlow()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val recipes: List<PokeModel> = pokeRemoteRepository.getPokemon()
            _uiState.update { uiState ->
                uiState.copy(
                    recipes = recipes
                )
            }
        }
    }
}