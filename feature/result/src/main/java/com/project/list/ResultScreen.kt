package com.project.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.SearchBar
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ResultRoute(
    navigateToDetail: (Int) -> Unit,
    title: String,
    viewModel: ResultViewModel = hiltViewModel()
) {
    val resultUiState: ResultUiState by viewModel.resultUiState.collectAsStateWithLifecycle()
    ResultScreen(navigateToDetail, title, resultUiState, viewModel)
}

@Composable
fun ResultScreen(
    navigateToDetail: (Int) -> Unit,
    text: String,
    resultUiState: ResultUiState,
    viewModel: ResultViewModel,
    modifier: Modifier = Modifier
) {
    var title by rememberSaveable { mutableStateOf(text) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = modifier.height(64.dp))

        SearchBar(text = title) {
            title = it
            viewModel.searchShow(it)
        }

        Spacer(modifier = modifier.height(20.dp))

        when (resultUiState) {
            is ResultUiState.Error -> {
                Text(
                    text = resultUiState.message,
                    color = Color.Red,
                    modifier = modifier.align(Alignment.CenterHorizontally)
                )
            }
            is ResultUiState.Success -> {
                if (resultUiState.shows.isEmpty()) {
                    Text(
                        text = stringResource(R.string.none_result, title),
                        modifier = modifier.align(Alignment.CenterHorizontally)
                    )
                }

                val interactionSource = remember { MutableInteractionSource() }
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(160.dp),
                    verticalArrangement = Arrangement.spacedBy(40.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(resultUiState.shows) { item ->
                        Column(
                            modifier = Modifier
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null
                                ) {
                                    navigateToDetail(item.id)
                                }
                        ) {
                            GlideImage(
                                imageModel = item.image?.medium,
                                contentScale = ContentScale.Crop,
                                error = ImageBitmap.imageResource(R.drawable.empty),
                                modifier = modifier
                                    .size(width = 160.dp, height = 220.dp)
                                    .clip(RoundedCornerShape(10.dp)),
                                shimmerParams = ShimmerParams(
                                    baseColor = MaterialTheme.colorScheme.background,
                                    highlightColor = MaterialTheme.colorScheme.secondary
                                ),
                            )
                            Spacer(modifier = modifier.height(5.dp))
                            Text(
                                text = item.name,
                                modifier = modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }
            }
            is ResultUiState.Loading -> {}
        }
    }
}
