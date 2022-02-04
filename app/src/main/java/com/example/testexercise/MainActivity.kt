package com.example.testexercise

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.testexercise.data.Author
import com.example.testexercise.data.ParamType
import com.example.testexercise.domain.MainListViewModel
import com.example.testexercise.domain.NavDestinations
import com.example.testexercise.presentation.DetailsScreen
import com.example.testexercise.presentation.MainListScreen
import com.google.gson.Gson
import org.koin.androidx.compose.getViewModel

@ExperimentalFoundationApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalCoilApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                startDestination = "main_list"
            ) {
                composable("main_list") {
                    MainListScreen()
                }
                composable(
                    "details/{author}",
                    arguments = listOf(navArgument("author") { type = ParamType() })
                ) {
                    val args = it.arguments?.getParcelable<Author>("author")
                    DetailsScreen(args)
                }
            }

            @ExperimentalMaterialApi
            @ExperimentalFoundationApi
            @ExperimentalCoilApi
            @Composable
            fun MainListScreen() {
                val viewModel: MainListViewModel = getViewModel()
                val viewState by viewModel.viewState.collectAsState()

                val navController = rememberNavController()

                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    stickyHeader {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(10.dp),
                            color = Color.White
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(20.dp)
                                    .wrapContentHeight(),
                                style = MaterialTheme.typography.h5,
                                textAlign = TextAlign.Center,
                                text = "Список авторов GitHub API"
                            )
                        }

                    }
                    items(viewState.authorsList) {
                        AuthorItem(
                            name = it.login.toString(),
                            imageUrl = it.avatarUrl.toString(),
                            onClick = {
                                val json = Uri.encode(Gson().toJson(it))
                                println("$json VVV")
                                navController.navigate("${NavDestinations.DETAILS_ROUTE}/$json")
                            }
                        )
                    }
                }
            }

            @ExperimentalMaterialApi
            @ExperimentalCoilApi
            @Composable
            fun AuthorItem(name: String, imageUrl: String, onClick: () -> Unit) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
                    elevation = 5.dp,
                    onClick = onClick
                ) {

                    Row {
                        Image(
                            modifier = Modifier
                                .padding(5.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.FillHeight,
                            painter = rememberImagePainter(imageUrl), contentDescription = ""
                        )

                        Column {
                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = name,
                                fontSize = 16.sp
                            )
                        }
                    }

                }
            }

        }
    }
}