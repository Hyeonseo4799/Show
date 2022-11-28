package com.project.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage

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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ResultScreen(
    navigateToDetail: (Int) -> Unit,
    title: String,
    resultUiState: ResultUiState,
    viewModel: ResultViewModel
) {
    var text by remember { mutableStateOf(title) }
    val keyBoardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                keyBoardController?.hide()
                viewModel.searchShow(text)
            }),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, RoundedCornerShape(20.dp))
                .background(Color.White, RoundedCornerShape(20.dp))
                .border(1.dp, Color(0xFFFF7A00), RoundedCornerShape(20.dp))
                .padding(horizontal = 15.dp, vertical = 12.dp),
        )
        Spacer(modifier = Modifier.height(20.dp))
        when (resultUiState) {
            is ResultUiState.Error -> {
                Text(
                    text = resultUiState.message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            is ResultUiState.Loading -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
            }
            is ResultUiState.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(60.dp),
                    horizontalArrangement = Arrangement.spacedBy(60.dp)
                ) {
                    items(resultUiState.shows) { item ->
                        Column(
                            modifier = Modifier.clickable { navigateToDetail(item.id) }
                        ) {
                            AsyncImage(
                                model = item.image?.medium ?: R.drawable.empty,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                            Text(
                                text = item.name,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }
            }
        }
    }
}
