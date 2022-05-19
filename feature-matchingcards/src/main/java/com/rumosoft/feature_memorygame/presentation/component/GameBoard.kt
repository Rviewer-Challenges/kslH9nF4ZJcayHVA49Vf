package com.rumosoft.feature_memorygame.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rumosoft.feature_memorygame.R
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Ready

@Composable
internal fun GameBoard(
    uiState: Ready,
    modifier: Modifier = Modifier,
) {
    val cardsDescription = stringResource(id = R.string.cards)
    LazyVerticalGrid(
        columns = GridCells.Fixed(uiState.boardInfo.columns),
        modifier = modifier.semantics { contentDescription = cardsDescription },
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        items(count = uiState.boardInfo.cards) { index ->
            Card(
                backgroundColor = Color.Blue,
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxSize()
            ) {
                Text(
                    "${index + 1}",
                    fontSize = 35.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}