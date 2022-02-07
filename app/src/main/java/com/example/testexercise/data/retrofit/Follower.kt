package com.example.testexercise.data.retrofit

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Follower(
    @Expose val login: String?
): Parcelable
