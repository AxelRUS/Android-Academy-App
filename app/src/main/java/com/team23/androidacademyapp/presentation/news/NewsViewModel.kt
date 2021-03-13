package com.team23.androidacademyapp.presentation.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsViewModel : ViewModel() {
    val modelNews: MutableLiveData<MutableList<ModelNews>> = MutableLiveData()
}