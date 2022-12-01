package com.project.show.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.project.list.navigation.resultScreen
import com.project.list.navigation.navigateToResult
import com.project.search.navigation.searchRoute
import com.project.search.navigation.searchScreen
import com.project.tvmaze.navigation.detailScreen
import com.project.tvmaze.navigation.navigateToDetail

@Composable
fun TVMazeNavHost(
    navController: NavHostController,
    startDestination: String = searchRoute
) {
    NavHost(navController = navController, startDestination = startDestination) {
        searchScreen(
            navigateToList = { title ->
                navController.navigateToResult(title)
            }
        )
        resultScreen(
            navigateToDetail = { id ->
                navController.navigateToDetail(id)
            }
        )
        detailScreen(
            navigateToBack = {
                navController.popBackStack()
            }
        )
    }
}