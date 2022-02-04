package com.example.testexercise.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repo(
    @Expose val id: String? = null,
    @Expose val name: String? = null
): Parcelable
