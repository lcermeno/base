package com.lcermeno.reddit.baseproject.presentation.characters_list

import com.lcermeno.reddit.baseproject.domain.model.Character

data class CharactersListState(
    val characters: List<Character> = emptyList(),
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val searchQuery: String = ""
)