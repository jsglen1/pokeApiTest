package com.example.pokeapitest.data.network

import com.example.pokeapitest.PokeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PokeApiClient {
    @GET
    suspend fun getNamePokemonesBy20(@Url url: String): Response<PokeResponse>
}