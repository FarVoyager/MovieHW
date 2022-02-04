package com.example.testexercise.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.testexercise.R
import com.example.testexercise.data.Author
import com.example.testexercise.domain.DetailsViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DetailsScreen(author: Author) {

    val viewModel: DetailsViewModel = getViewModel { parametersOf(author) }
    val viewState by viewModel.viewState.collectAsState()

    Scaffold(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),

        ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 10.dp),
            elevation = 5.dp
        ) {
            Column {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .padding(5.dp)
                            .clip(CircleShape)
                            .height(150.dp),
                        contentScale = ContentScale.FillHeight,
                        painter = rememberImagePainter(author.avatarUrl), contentDescription = ""
                    )
                    Text(
                        modifier = Modifier
                            .padding(20.dp)
                            .wrapContentHeight(),
                        style = MaterialTheme.typography.h4,
                        textAlign = TextAlign.Center,
                        text = author.login
                    )
                    Text(
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .wrapContentHeight(),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        text = "Subscriptions: ${viewState.followersQty}"
                    )
                    Divider(
                        modifier = Modifier
                            .height(1.dp)
                            .padding(horizontal = 20.dp),
                        color = Color(0xFFA2A2A2)
                    )
                }
                Column {
                    Text(
                        modifier = Modifier
                            .padding(horizontal =  20.dp, vertical = 10.dp)
                            .wrapContentHeight(),
                        style = MaterialTheme.typography.h6,
                        color = Color.Gray,
                        text = stringResource(R.string.details_screen_repositories_header),
                    )

                    LazyColumn(modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)) {
                        items(viewState.reposList) {
                            Text(
                                modifier = Modifier.padding(5.dp),
                                text = it.name
                            )
                        }
                    }
                }
            }

        }
    }
}