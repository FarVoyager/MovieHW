package com.example.moviehw.data.retrofit.converter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieListResultObject(
    val poster_path: String?,
    val adult: Boolean,
    val overview: String,
    val release_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_title: String,
    val original_language: String,
    val title: String,
    val backdrop_path: String?,
    val popularity: Int,
    val vote_count: Int,
    val video: Boolean,
    val vote_average: Int,
    val total_results: Int,
    val total_pages: Int
): Parcelable
