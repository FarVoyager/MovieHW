package com.example.testexercise.presentation

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.testexercise.domain.BaseViewModel
import com.example.testexercise.utils.isOnline
import org.koin.androidx.compose.getKoin

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun NoInternetPlaceHolder(viewModel: BaseViewModel) {
    val context = getKoin().get<Context>()
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 100.dp),
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            text = "Нет соединения с интернетом"
        )
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
            onClick = {
                viewModel.updateNetworkStatus(isOnline(context))
                Toast.makeText(context, if (isOnline(context)) "Интернет-соединение восстановлено" else "Интернет-соединение отсутствует", Toast.LENGTH_SHORT).show()
            }) {
            Text(text = "Попробовать снова")
        }
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
            onClick = { viewModel.loadLastData() }) {
            Text(text = "Показать оффлайн-данные")
        }
    }
}