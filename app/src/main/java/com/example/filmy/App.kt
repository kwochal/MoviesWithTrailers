package com.example.filmy

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

const val CHOSEN_MOVIE_KEY = "chosenMovieId"
const val LIST_SCREEN_ROUTE = "listScreen"
const val DETAILS_SCREEN_PATH = "detailsScreen/"

@Composable
fun App(moviesData : MoviesData){
    val navController = rememberNavController()
    NavHost(navController, startDestination= LIST_SCREEN_ROUTE) {
        composable(route= LIST_SCREEN_ROUTE){
            MovieListScreen(moviesData.getMovies(), navController)
        }
        composable(
            route = "$DETAILS_SCREEN_PATH{$CHOSEN_MOVIE_KEY}",
            arguments = listOf(navArgument(CHOSEN_MOVIE_KEY) { type = NavType.IntType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val movieId = arguments.getInt(CHOSEN_MOVIE_KEY)
            val movie = moviesData.getMovieById(movieId)
            if(movie!=null)
                MovieDetailScreen(movie)
        }
    }
}