package com.team23.androidacademyapp.presentation.news


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.team23.androidacademyapp.domain.models.News
import kotlinx.coroutines.launch


class NewsViewModel : ViewModel() {

    private val _newsList: MutableLiveData<MutableList<News>> = MutableLiveData()
    val newsList: LiveData<MutableList<News>>
        get() = _newsList

    init {
        viewModelScope.launch {
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
                    _newsList.postValue(listData)
                }
        }
    }
}