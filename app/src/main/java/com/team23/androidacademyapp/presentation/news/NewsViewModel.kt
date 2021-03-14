package com.team23.androidacademyapp.presentation.news


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.team23.androidacademyapp.domain.models.News


class NewsViewModel : ViewModel() {

    val modelNews: MutableLiveData<MutableList<News>> = MutableLiveData()
    init {
        FirebaseFirestore.getInstance().collection("news").get()
            .addOnSuccessListener { result ->
                val listData = mutableListOf<News>()
                for (document in result) {
                    val mTitle: String = document.getString("titleNews") ?: ""
                    val mImage: String = document.getString("imageNews") ?: ""
                    val mText: String = document.getString("descriptionNews") ?: ""

                    val model = News(
                        mTitle, mImage, mText
                    )
                    listData.add(model)
                }
                modelNews.value = listData
            }
    }
}