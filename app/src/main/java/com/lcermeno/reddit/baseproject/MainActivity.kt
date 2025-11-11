package com.lcermeno.reddit.baseproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.lcermeno.reddit.baseproject.presentation.characters_detail.CharacterDetailScreen
import com.lcermeno.reddit.baseproject.presentation.characters_list.CharactersListScreen
import com.lcermeno.reddit.baseproject.presentation.navigation.CharacterDetailKey
import com.lcermeno.reddit.baseproject.presentation.navigation.CharactersListKey
import com.lcermeno.reddit.baseproject.ui.theme.BaseProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaseProjectTheme {
                MainNavigation()
            }
        }
    }
}

@Composable
fun MainNavigation() {

    val backStack = rememberNavBackStack(CharactersListKey)

    val entryProvider = entryProvider {
        entry<CharactersListKey> {
            CharactersListScreen { character ->
                backStack.add(CharacterDetailKey(character))
            }
        }

        entry<CharacterDetailKey> { key ->
            CharacterDetailScreen(key.character)
        }
    }

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider,
        onBack = {
            backStack.removeLastOrNull()
        }
    )
}
