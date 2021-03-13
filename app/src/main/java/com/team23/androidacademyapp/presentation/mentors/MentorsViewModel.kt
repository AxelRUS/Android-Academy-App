package com.team23.androidacademyapp.presentation.mentors

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.team23.androidacademyapp.domain.Model

class MentorsViewModel : ViewModel() {

    private val firebaseFirestone: FirebaseFirestore = FirebaseFirestore.getInstance()
    val modelMentor: MutableLiveData<MutableList<Model>> = MutableLiveData()
    val modelLiveData: LiveData<MutableList<Model>>
        get() = modelMentor

    init {
        FirebaseFirestore.getInstance().collection("lecture").get()
            .addOnSuccessListener { result ->
                val listData = mutableListOf<Model>()
                for (document in result) {
                    val mVideo: String = document.getString("video") ?: ""
                    val mTitle: String = document.getString("title") ?: ""
                    val mDescription: String = document.getString("description") ?: ""
                    Log.e("XXX",mVideo)

                    val model = Model(
                        mVideo,mTitle,mDescription)
                    listData.add(model)
                }
                modelMentor.value = listData
            }
    }
}