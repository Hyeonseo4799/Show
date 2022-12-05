package com.project.search

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.SearchBar

@Composable
fun SearchRoute(navigateToList: (String) -> Unit) {
    SearchScreen(navigateToList)
}

@Composable
fun SearchScreen(
    navigateToList: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 20.dp)) {
        Spacer(modifier = modifier.height(150.dp))

        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onBackground)) { append("보여주 ") }
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) { append("Show!") }
            },
            modifier = modifier.align(Alignment.CenterHorizontally),
            style = TextStyle(fontSize = 28.sp)
        )

        Spacer(modifier = modifier.height(30.dp))

        SearchBar { navigateToList(it) }

        Spacer(modifier = modifier.height(24.dp))

        Text(
            text = stringResource(R.string.explain),
            color = Color.Gray,
            textAlign = TextAlign.End,
            modifier = modifier.align(Alignment.End)
        )
    }
}