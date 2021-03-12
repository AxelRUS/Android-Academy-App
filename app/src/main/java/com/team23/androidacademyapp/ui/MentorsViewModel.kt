package com.team23.androidacademyapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MentorsViewModel : ViewModel() {

    private val _mutableMentorsList = MutableLiveData<List<Mentor>>(emptyList())

    val mentorsList: LiveData<List<Mentor>> get() = _mutableMentorsList

    init {
        _mutableMentorsList.value = listOf(Mentor(name = "Test"))
    }

}