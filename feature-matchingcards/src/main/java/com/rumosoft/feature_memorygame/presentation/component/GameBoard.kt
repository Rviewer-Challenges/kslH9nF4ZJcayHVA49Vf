package com.rumosoft.feature_memorygame.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.rumosoft.feature_memorygame.R
import com.rumosoft.feature_memorygame.domain.entity.GameCard
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Ready
import com.rumosoft.library_components.presentation.theme.MemoryGameTheme

@Composable
internal fun GameBoard(
    uiState: Ready,
    modifier: Modifier = Modifier,
    onCardSelected: (GameCard) -> Unit = {},
) {
    val cardsDescription = stringResource(id = R.string.cards)

    LazyVerticalGrid(
        columns = GridCells.Fixed(uiState.board.columns),
        modifier = modifier.semantics { contentDescription = cardsDescription },
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        items(uiState.board.cards) { gameCard ->
            BoardCard(gameCard, onCardSelected)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun BoardCard(
    gameCard: GameCard,
    onCardSelected: (GameCard) -> Unit
) {
    Box(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(.5f)
        .aspectRatio(1f)) {
        if (!gameCard.matched) {
            FlipCard(
                cardFace = gameCard.face,
                onClick = { onCardSelected(gameCard) },
                modifier = Modifier.fillMaxSize(),
                front = {
                    FrontCard(gameCard)
                },
                back = {
                    BackCard()
                },
            )
        }
    }
}

@Composable
private fun FrontCard(gameCard: GameCard, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        val context = LocalContext.current
        val drawableId = remember(gameCard.image) {
            context.resources.getIdentifier(
                gameCard.image,
                "drawable",
                context.packageName
            )
        }
        Image(
            painter = painterResource(id = drawableId),
            contentDescription = gameCard.name
        )
    }
}

@Composable
private fun BackCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Blue),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "?",
            color = MemoryGameTheme.extraColors.white,
            style = MaterialTheme.typography.h1,
        )
    }
}
