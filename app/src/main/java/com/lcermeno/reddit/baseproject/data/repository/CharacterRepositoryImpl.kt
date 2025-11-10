package com.lcermeno.reddit.baseproject.data.repository

import com.lcermeno.reddit.baseproject.data.api.RickAndMortyApi
import com.lcermeno.reddit.baseproject.data.mapper.toDomain
import com.lcermeno.reddit.baseproject.domain.model.Character
import com.lcermeno.reddit.baseproject.domain.repository.CharacterRepository
import com.lcermeno.reddit.baseproject.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi,
) : CharacterRepository {

    override fun getCharacters(): Flow<Resource<List<Character>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val characters = api
                    .getCharacters()
                    .results
                    .map { it.toDomain() }

                emit(Resource.Success(characters))

            } catch (e: Exception) {
                emit(Resource.Error(message = e.message ?: "Unknown error"))
            }
        }
    }
}