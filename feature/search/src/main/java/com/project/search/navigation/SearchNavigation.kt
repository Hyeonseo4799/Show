package com.project.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.project.search.SearchRoute

const val searchRoute = "search_route"

fun NavGraphBuilder.searchScreen(
    navigateToList: (String) -> Unit
) {
    composable(route = searchRoute) {
        SearchRoute(navigateToList = navigateToList)
    }
}