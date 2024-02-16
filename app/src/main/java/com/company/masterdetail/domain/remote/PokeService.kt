package com.company.masterdetail.domain.remote

import com.company.masterdetail.data.remote.responses.PokeDetailResponse
import com.company.masterdetail.data.remote.responses.PokeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val pokemonUrl = "pokemon"
interface APIService {

    @GET("$pokemonUrl?limit=25")
    suspend fun getPokemon(@Query("offset") offset: Int = 0): Response<PokeResponse>

    @GET("$pokemonUrl/{id}")
    suspend fun getDetail(@Path("id") id: Int): Response<PokeDetailResponse>
}