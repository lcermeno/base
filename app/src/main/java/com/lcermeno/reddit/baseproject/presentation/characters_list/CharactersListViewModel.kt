package com.lcermeno.reddit.baseproject.presentation.characters_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcermeno.reddit.baseproject.domain.usecase.GetCharactersUseCase
import com.lcermeno.reddit.baseproject.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    init {

        getCharactersUseCase()
            .onEach { result ->
                when (val state = result) {
                    is Resource.Error -> _uiState.update {
                        it.copy(
                            loading = false,
                            errorMessage = state.message
                        )
                    }

                    is Resource.Loading -> _uiState.update {
                        it.copy(loading = true)
                    }
                    is Resource.Success -> _uiState.update {
                        it.copy(
                            loading = false,
                            errorMessage = null,
                            characters = state.data ?: emptyList()
                        )
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}