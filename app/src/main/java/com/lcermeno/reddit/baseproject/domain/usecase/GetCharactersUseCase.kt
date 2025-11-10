package com.lcermeno.reddit.baseproject.domain.usecase

import com.lcermeno.reddit.baseproject.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    operator fun invoke() = repository.getCharacters()
}