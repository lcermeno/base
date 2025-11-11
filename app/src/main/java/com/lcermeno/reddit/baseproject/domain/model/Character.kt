package com.lcermeno.reddit.baseproject.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val imageUrl: String
)