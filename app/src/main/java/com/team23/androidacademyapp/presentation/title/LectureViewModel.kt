package com.team23.androidacademyapp.presentation.title

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.team23.androidacademyapp.domain.models.Model
import kotlinx.coroutines.launch

class LectureViewModel : ViewModel() {

    private val firebaseFirestone: FirebaseFirestore = FirebaseFirestore.getInstance()
    val model: MutableLiveData<MutableList<Model>> = MutableLiveData()
    val modelLiveData: LiveData<MutableList<Model>>
        get() = model

    init {
        viewModelScope.launch {

        FirebaseFirestore.getInstance().collection("lecture").get()
            .addOnSuccessListener { result ->
                val listData = mutableListOf<Model>()
                for (document in result) {
                    val mVideo: String = document.getString("video") ?: ""
                    val mTitle: String = document.getString("title") ?: ""
                    val mDescription: String = document.getString("description") ?: ""
                    val mWorkshop: String = document.getString("workshop") ?: ""
                    val mFeedback: String = document.getString("feedback") ?: ""
                    Log.e("XXX",mVideo)

                    val model = Model(
                       mVideo,mTitle,mDescription, mWorkshop, mFeedback)
                    listData.add(model)
                }
                model.postValue(listData)
            }
        }
    }

}