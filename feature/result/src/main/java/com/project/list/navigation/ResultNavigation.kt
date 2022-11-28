package com.project.list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.project.list.ResultRoute

const val resultRoute = "result_route"

fun NavController.navigateToResult(title: String) {
    this.navigate("$resultRoute/$title")
}

fun NavGraphBuilder.resultScreen(
    navigateToDetail: (Int) -> Unit
) {
    composable(route = "$resultRoute/{title}") {
        ResultRoute(navigateToDetail = navigateToDetail, title = it.arguments?.getString("title")!!)
    }
}