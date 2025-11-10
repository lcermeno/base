package com.lcermeno.reddit.baseproject.domain.repository

import com.lcermeno.reddit.baseproject.domain.model.Character
import com.lcermeno.reddit.baseproject.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacters(): Flow<Resource<List<Character>>>
}