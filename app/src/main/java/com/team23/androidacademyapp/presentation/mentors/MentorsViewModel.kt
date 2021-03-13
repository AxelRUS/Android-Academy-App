package com.team23.androidacademyapp.presentation.mentors

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.team23.androidacademyapp.domain.models.ModelMentor

class MentorsViewModel : ViewModel() {

    private val firebaseFirestone: FirebaseFirestore = FirebaseFirestore.getInstance()
    val modelMentor: MutableLiveData<MutableList<ModelMentor>> = MutableLiveData()
    private val modelLiveData: LiveData<MutableList<ModelMentor>>
        get() = modelMentor

    init {
        FirebaseFirestore.getInstance().collection("mentor").get()
            .addOnSuccessListener { result ->
                val listData = mutableListOf<ModelMentor>()
                for (document in result) {
                    val mName: String = document.getString("name") ?: ""
                    val mSurname: String = document.getString("surname") ?: ""
                    val mContact: String = document.getString("contact") ?: ""
                    val mFoto: String = document.getString("foto") ?: ""
                    Log.e("XXX", mName)

                    val model = ModelMentor(
                        mName, mSurname, mContact, mFoto
                    )
                    listData.add(model)
                }
                modelMentor.value = listData
            }
    }
}