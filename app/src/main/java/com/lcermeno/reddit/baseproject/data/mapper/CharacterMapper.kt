package com.lcermeno.reddit.baseproject.data.mapper

import com.lcermeno.reddit.baseproject.data.api.dto.CharacterResponse
import com.lcermeno.reddit.baseproject.domain.model.Character


fun CharacterResponse.toDomain() =
    Character(
        id = id,
        name = name,
        imageUrl = image
    )