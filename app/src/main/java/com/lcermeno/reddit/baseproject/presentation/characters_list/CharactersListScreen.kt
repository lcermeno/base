package com.lcermeno.reddit.baseproject.presentation.characters_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lcermeno.reddit.baseproject.domain.model.Character
import com.lcermeno.reddit.baseproject.presentation.characters_list.components.CharacterItem
import com.lcermeno.reddit.baseproject.presentation.characters_list.components.ErrorView
import com.lcermeno.reddit.baseproject.presentation.characters_list.components.LoadingView

@Composable
fun CharactersListScreen(
    viewModel: CharactersListViewModel = hiltViewModel(),
    onNavigateToDetail: (Character) -> Unit,
) {

    val uiState by viewModel.uiState.collectAsState()

    val localState = uiState

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->

        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {

            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = viewModel::onSearchQueryChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                singleLine = true,
                placeholder = { Text("Search...") }

            )
            ContentView(paddingValues, localState, uiState, onNavigateToDetail)
        }
    }
}

@Composable
private fun ContentView(
    paddingValues: PaddingValues,
    localState: CharactersListState,
    uiState: CharactersListState,
    onNavigateToDetail: (Character) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {

        if (localState.errorMessage != null) {
            ErrorView(localState.errorMessage)
        } else if (uiState.loading) {
            LoadingView()
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(uiState.characters) { character ->
                    CharacterItem(character, onNavigateToDetail)
                }
            }
        }
    }
}