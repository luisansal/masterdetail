package com.company.masterdetail.data.repository

import com.company.masterdetail.data.mappers.toEntity
import com.company.masterdetail.domain.dao.PokeDetailLocalDao
import com.company.masterdetail.domain.dao.PokeDetailTypeLocalDao
import com.company.masterdetail.domain.dao.PokeLocalDao
import com.company.masterdetail.domain.entities.PokeDetailEntity
import com.company.masterdetail.domain.entities.PokemonWithDetail
import com.company.masterdetail.domain.entities.DetailTypeEntity
import com.company.masterdetail.domain.entities.DetailWithTypes
import com.company.masterdetail.domain.model.PokeModel
import javax.inject.Inject

class PokeLocalRepository @Inject constructor(
    private val pokeLocalDao: PokeLocalDao,
    private val pokeDetailLocalDao: PokeDetailLocalDao,
    private val pokeDetailTypeLocalDao: PokeDetailTypeLocalDao
) {
    suspend fun savePokemon(pokeModel: PokeModel) {
        pokeModel.detail?.let {
            val detailId = it.id
            it.toEntity().let {
                saveDetailPokemon(
                    it.copy(
                        pokemonId = pokeModel.id
                    )
                )
            }
            it.types?.forEach {
                saveDetailTypePokemon(
                    it.toEntity().copy(
                        detailId = detailId
                    )
                )
            }
        }
        pokeLocalDao.insertAll(pokeModel.toEntity())
    }

    suspend fun getPokemon(): List<PokemonWithDetail> {
        return pokeLocalDao.getPokemon()
    }

    suspend fun getDetail(id: Long?): DetailWithTypes {
        return pokeDetailLocalDao.getDetail(id)
    }

    private suspend fun saveDetailPokemon(pokeDetailEntity: PokeDetailEntity) {
        pokeDetailLocalDao.insertAll(pokeDetailEntity)
    }

    private suspend fun saveDetailTypePokemon(pokeDetailEntity: DetailTypeEntity) {
        pokeDetailTypeLocalDao.insertAll(pokeDetailEntity)
    }
}