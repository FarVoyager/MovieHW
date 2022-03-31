package com.example.moviehw.presentation

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.moviehw.R
import com.example.moviehw.domain.BaseViewModel
import com.example.moviehw.utils.isOnline
import org.koin.androidx.compose.getKoin

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun NoInternetPlaceHolder(viewModel: BaseViewModel) {
    val context = getKoin().get<Context>()
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            text = stringResource(R.string.no_internet_placeholder_header)
        )
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
            onClick = {
                viewModel.updateNetworkStatus(isOnline(context))
                Toast.makeText(
                    context,
                    if (isOnline(context)) R.string.internet_connection_restored_toast_text
                    else
                        R.string.internet_connection_off_toast_text,
                    Toast.LENGTH_SHORT
                ).show()
            }) {
            Text(text = stringResource(R.string.no_internet_placeholder_try_again_btn_text))
        }
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
            onClick = { viewModel.loadLastData() }) {
            Text(text = stringResource(R.string.no_internet_placeholder_load_offline_btn_text))
        }
    }
}