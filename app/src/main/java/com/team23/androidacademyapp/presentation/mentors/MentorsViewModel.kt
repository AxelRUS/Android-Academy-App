package com.team23.androidacademyapp.presentation.mentors

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.team23.androidacademyapp.domain.models.Mentor
import kotlinx.coroutines.launch

class MentorsViewModel : ViewModel() {

    val _mentorList: MutableLiveData<MutableList<Mentor>> = MutableLiveData()
    private val mentorList: LiveData<MutableList<Mentor>>
        get() = _mentorList

    init {
        viewModelScope.launch {
            FirebaseFirestore.getInstance().collection("mentor").get()
                .addOnSuccessListener { result ->
                    val listData = mutableListOf<Mentor>()
                    for (document in result) {
                        val mName: String = document.getString("name") ?: ""
                        val mSurname: String = document.getString("surname") ?: ""
                        val mContact: String = document.getString("contact") ?: ""
                        val mFoto: String = document.getString("foto") ?: ""
                        Log.e("XXX", mName)

                        val model = Mentor(
                            mName, mSurname, mContact, mFoto
                        )
                        listData.add(model)
                    }
                    _mentorList.postValue(listData)
                }
        }
    }
}