package com.project.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchRoute(navigateToList: (String) -> Unit) {
    SearchScreen(navigateToList)
}

@Composable
fun SearchScreen(
    navigateToList: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(108.dp))
        Text(
            buildAnnotatedString {
                append("보여주 ")
                withStyle(style = SpanStyle(color = Color(0xFFFF7A00))) {
                    append("Show!")
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = TextStyle(fontSize = 28.sp)
        )
        Spacer(modifier = Modifier.height(54.dp))
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { navigateToList(text) }),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, RoundedCornerShape(20.dp))
                .background(Color.White, RoundedCornerShape(20.dp))
                .border(1.dp, Color(0xFFFF7A00), RoundedCornerShape(20.dp))
                .padding(horizontal = 15.dp, vertical = 12.dp),
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.explain),
            color = Color.Gray,
            textAlign = TextAlign.End,
            modifier = Modifier.align(Alignment.End)
        )
    }
}