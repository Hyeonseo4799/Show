package com.project.tvmaze.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.project.tvmaze.DetailRoute

const val detailRoute = "detail_route"

fun NavController.navigateToDetail(id: Int) {
    this.navigate("detail_route/$id")
}

fun NavGraphBuilder.detailScreen(
    navigateToBack: () -> Unit
) {
    composable(
        route = "$detailRoute/{id}",
        arguments = listOf(navArgument("id") { type = NavType.IntType })
    ) {
        DetailRoute(navigateToBack)
    }
}