package com.lcermeno.reddit.baseproject.presentation.navigation

import androidx.navigation3.runtime.NavKey
import com.lcermeno.reddit.baseproject.domain.model.Character
import kotlinx.serialization.Serializable

@Serializable
data object CharactersListKey : NavKey

@Serializable
data class CharacterDetailKey(val character: Character): NavKey