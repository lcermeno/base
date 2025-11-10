package com.lcermeno.reddit.baseproject.data.api

import com.lcermeno.reddit.baseproject.data.api.dto.CharactersResponse
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(): CharactersResponse
}