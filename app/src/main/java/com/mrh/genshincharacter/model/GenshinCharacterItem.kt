package com.mrh.genshincharacter.model

import com.google.gson.annotations.SerializedName

data class GenshinCharacterItem(
	val id: String,
	val characterName: String,
	val regionName: String,
	val weapon: String,
	val element: String,
	val photoUrl: String,
)