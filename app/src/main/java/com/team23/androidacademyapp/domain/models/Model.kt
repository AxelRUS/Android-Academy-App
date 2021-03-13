package com.team23.androidacademyapp.domain.models
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Model(
    val video:String,
    val title:String,
    val description:String,
    val wokrshop:String,
    val feedback:String
) : Parcelable {
}