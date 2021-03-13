package com.team23.androidacademyapp.presentation.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class NewsViewModel : ViewModel() {
    private val firebaseFirestone: FirebaseFirestore = FirebaseFirestore.getInstance()
    val modelNews: MutableLiveData<MutableList<ModelNews>> = MutableLiveData()
    private val modelLiveData: LiveData<MutableList<ModelNews>>
        get() = modelNews

    init {
        FirebaseFirestore.getInstance().collection("news").get()
            .addOnSuccessListener { result ->
                val listData = mutableListOf<ModelNews>()
                for (document in result) {
                    val mNews: String = document.getString("titleNews") ?: ""
                    val mDescriptionNews: String = document.getString("descriptionNews") ?: ""
                    val mImage: String = document.getString("imageNews") ?: ""
                    Log.e("XXXX", mImage)

                    val model = ModelNews(
                        mNews, mImage, mDescriptionNews
                    )
                    listData.add(model)
                }
                modelNews.value = listData
            }
    }
}