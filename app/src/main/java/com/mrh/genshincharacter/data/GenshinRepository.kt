package com.mrh.genshincharacter.data

import com.mrh.genshincharacter.model.CharactersData
import com.mrh.genshincharacter.model.GenshinCharacterItem

class GenshinRepository {
    fun getChars(): List<GenshinCharacterItem> {
        return CharactersData.characters
    }

    fun searchChar(query: String) : List<GenshinCharacterItem> {
        return CharactersData.characters.filter {
            it.characterName.contains(query, ignoreCase = true)
        }
    }
}