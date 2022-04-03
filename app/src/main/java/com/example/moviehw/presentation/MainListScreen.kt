package com.example.moviehw.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.moviehw.R
import com.example.moviehw.domain.mainlist.MainListViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@RequiresApi(Build.VERSION_CODES.M)
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun MainListScreen(navController: NavController) {
    val viewModel: MainListViewModel = getViewModel { parametersOf(navController) }
    val viewState by viewModel.viewState.collectAsState()

    Scaffold {
        if (!viewState.isOnline && !viewState.isOfflineLoaded) {
            NoInternetPlaceHolder(viewModel = viewModel)
        } else {
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
                        Column {
                            Text(
                                modifier = Modifier
                                    .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 5.dp)
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                style = MaterialTheme.typography.h5,
                                textAlign = TextAlign.Center,
                                text = stringResource(R.string.main_list_header)
                            )
                            Text(
                                modifier = Modifier
                                    .padding(bottom = 5.dp)
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                fontSize = 12.sp,
                                color = Color.DarkGray,
                                textAlign = TextAlign.Center,
                                text = if (viewState.isOnline) "" else stringResource(R.string.main_list_screen_offline_mode_marking)
                            )
                        }
                    }
                }

                if (viewState.isLoading) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 150.dp),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            CircularProgressIndicator(modifier = Modifier)
                        }
                    }
                } else {
                    items(viewState.moviesList) {
                        AuthorItem(
                            name = it.title,
                            imageUrl = it.poster_path!!,
                            onClick = {
                                viewModel.onItemSelected(it)
                            }
                        )
                    }
                }
            }
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
