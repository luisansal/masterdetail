package com.company.masterdetail.data.repository

import com.company.masterdetail.data.mappers.toModel
import com.company.masterdetail.domain.remote.APIService
import com.company.masterdetail.domain.model.PokeDetailModel
import com.company.masterdetail.domain.model.PokeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokeRemoteRepository @Inject constructor(
    private val apiService: APIService,
    private val pokeLocalRepository: PokeLocalRepository
) {

    private var getDataFromLocal = false

    private suspend fun getPokemonList(offset: Int = 0): List<PokeModel> {
        return withContext(Dispatchers.IO) {
            try {
                getDataFromLocal = false
                val response = apiService.getPokemon(offset)
                response.body()?.toModel() ?: emptyList()
            } catch (e: Exception) {
                getDataFromLocal = true
                pokeLocalRepository.getPokemon().toModel().map {
                    addDetailTypes(it)
                }
            }
        }
    }

    private suspend fun addDetailTypes(pokeModel: PokeModel): PokeModel {
        val detail = pokeLocalRepository.getDetail(pokeModel.detail?.id)
        return pokeModel.copy(
            detail = detail.pokeDetailEntity.toModel().copy(
                types = detail.types.toModel()
            )
        )
    }

    suspend fun getPokemon(offset: Int = 0): List<PokeModel> {
        return withContext(Dispatchers.IO) {
            val pokemon = getPokemonList(offset)
            if (getDataFromLocal) {
                return@withContext pokemon
            }
            pokemon.map { pokeModel ->
                val detail = getDetailPokemon(pokeModel.id)
                val result = PokeModel(
                    id = pokeModel.id,
                    name = pokeModel.name,
                    url = pokeModel.url,
                    detail = detail
                )
                pokeLocalRepository.savePokemon(result)
                result
            }
        }
    }

    private suspend fun getDetailPokemon(id: Int): PokeDetailModel? {
        return withContext(Dispatchers.IO) {
            try {
                getDataFromLocal = false
                val response = apiService.getDetail(id)
                response.body()?.toModel()
            } catch (e: Exception) {
                getDataFromLocal = true
                null
            }
        }
    }


}