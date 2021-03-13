package com.team23.androidacademyapp.presentation.mentors

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class MentorsViewModel : ViewModel() {

    private val firebaseFirestone: FirebaseFirestore = FirebaseFirestore.getInstance()
    val modelMentor: MutableLiveData<MutableList<ModelMentor>> = MutableLiveData()
    private val modelLiveData: LiveData<MutableList<ModelMentor>>
        get() = modelMentor

    init {
        FirebaseFirestore.getInstance().collection("lecture").get()
            .addOnSuccessListener { result ->
                val listData = mutableListOf<ModelMentor>()
                for (document in result) {
                    val mName: String = document.getString("name") ?: ""
                    val mSurname: String = document.getString("surname") ?: ""
                    val mContact: String = document.getString("description") ?: ""
                    val mFoto: String = document.getString("foto") ?: ""
                    Log.e("XXX", mName)

                    val model = ModelMentor(
                        mName, mContact, mSurname, mFoto
                    )
                    listData.add(model)
                }
                modelMentor.value = listData
            }
    }
}