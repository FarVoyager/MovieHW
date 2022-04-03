package com.example.moviehw.common

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.moviehw.data.retrofit.Author
import com.example.moviehw.data.retrofit.converter.MovieListResultObject
import com.example.moviehw.utils.ParamType
import com.example.moviehw.presentation.DetailsScreen
import com.example.moviehw.presentation.MainListScreen
import com.example.moviehw.utils.NavDestinations

@ExperimentalFoundationApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    @OptIn(ExperimentalCoilApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        //comment
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                startDestination = NavDestinations.LIST_ROUTE
            ) {
                composable("main_list") {
                    MainListScreen(navController)
                }
                composable(
                    "${NavDestinations.DETAILS_ROUTE}/{movie}",
                    arguments = listOf(navArgument("movie") { type = ParamType() })
                ) {
                    val args = it.arguments?.getParcelable<MovieListResultObject>("movie")
                    DetailsScreen(args!!, navController)
                }
            }
        }
    }
}