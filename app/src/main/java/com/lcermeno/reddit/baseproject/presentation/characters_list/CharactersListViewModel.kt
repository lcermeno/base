package com.lcermeno.reddit.baseproject.presentation.characters_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcermeno.reddit.baseproject.domain.usecase.GetCharactersUseCase
import com.lcermeno.reddit.baseproject.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    getCharactersUseCase: GetCharactersUseCase,
) : ViewModel() {


    private val _uiState = MutableStateFlow(CharactersListState())
    val uiState: StateFlow<CharactersListState>
        get() = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")


    init {

        combine(getCharactersUseCase(), _searchQuery) { result, query ->
            when (val state = result) {
                is Resource.Error -> CharactersListState(
                        loading = false,
                        errorMessage = state.message
                    )


                is Resource.Loading -> CharactersListState(loading = true)

                is Resource.Success -> {
                    val filteredCharacters = if (query.isNotBlank()) {
                        state.data?.filter { character ->
                            character.name.contains(query, ignoreCase = true)
                        } ?: emptyList()
                    } else {
                        state.data ?: emptyList()
                    }

                    CharactersListState(
                        loading = false,
                        errorMessage = null,
                        characters = filteredCharacters,
                        searchQuery = query
                    )
                }

            }
        }.onEach { result ->
            _uiState.update { result }
        }.launchIn(viewModelScope)
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
}