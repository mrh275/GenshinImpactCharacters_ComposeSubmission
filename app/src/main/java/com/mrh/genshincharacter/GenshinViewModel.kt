package com.mrh.genshincharacter

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mrh.genshincharacter.data.GenshinRepository
import com.mrh.genshincharacter.model.GenshinCharacterItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.IllegalArgumentException

class GenshinViewModel(private val repository: GenshinRepository) : ViewModel() {
    private val _groupedChars = MutableStateFlow(
        repository.getChars()
            .sortedBy { it.characterName }
            .groupBy { it.characterName[0] }
    )
    val groupedChars: StateFlow<Map<Char, List<GenshinCharacterItem>>> get() = _groupedChars

    private val _query = mutableStateOf("")
    val query : State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _groupedChars.value = repository.searchChar(_query.value)
            .sortedBy { it.characterName }
            .groupBy { it.characterName[0] }
    }
}

class ViewModelFactory(private val repository: GenshinRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GenshinViewModel::class.java)) {
            return GenshinViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class " + modelClass.name)
    }
}