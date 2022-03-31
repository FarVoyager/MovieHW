package com.example.moviehw.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.example.moviehw.data.retrofit.Author
import com.google.gson.Gson

class ParamType : NavType<Author>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Author? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Author {
        return Gson().fromJson(value, Author::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Author) {
        bundle.putParcelable(key, value)
    }
}