package com.example.moviehw.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.example.moviehw.data.retrofit.Author
import com.example.moviehw.data.retrofit.converter.MovieListResultObject
import com.google.gson.Gson

class ParamType : NavType<MovieListResultObject>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): MovieListResultObject? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): MovieListResultObject {
        return Gson().fromJson(value, MovieListResultObject::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: MovieListResultObject) {
        bundle.putParcelable(key, value)
    }
}