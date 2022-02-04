package com.example.testexercise.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.testexercise.data.Author

@Composable
fun DetailsScreen(author: Author?) {
    println("${author?.login} VVV login")
    Text(text = author?.login.toString())
}