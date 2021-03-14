package com.team23.androidacademyapp.domain.models
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Model(
    val video:String,
    val title:String,
    val description:String,
    val wokrshop:String,
    val feedback:String,
    val quizes: List<Quiz>
) : Parcelable

@Parcelize
data class Quiz(
    val question: String,
    val answers: List<String>,
    val rigthAnswer: Int) : Parcelable
