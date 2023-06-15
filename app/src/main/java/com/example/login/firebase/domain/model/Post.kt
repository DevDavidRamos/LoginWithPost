package com.example.login.firebase.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Post(
    val id: String = UUID.randomUUID().toString(),
    val post: String = "",
    val date: Date = Date(),
    val userName: String = ""

): Parcelable
