package com.example.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String = "",
    searchShowByTitle: (String) -> Unit
) {
    var title by remember { mutableStateOf(text) }
    val keyBoardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        value = title,
        onValueChange = { title = it },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            keyBoardController?.hide()
            searchShowByTitle(title)
        }),
        modifier = modifier
            .fillMaxWidth()
            .shadow(5.dp, RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.background, RoundedCornerShape(20.dp))
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp)),
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground),
        cursorBrush = Brush.verticalGradient(
            0.00f to MaterialTheme.colorScheme.onBackground,
            1.00f to MaterialTheme.colorScheme.onBackground
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = modifier.padding(end = 5.dp)
                )
                innerTextField()
            }
        }
    )
}