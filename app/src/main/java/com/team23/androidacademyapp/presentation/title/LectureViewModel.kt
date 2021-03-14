package com.team23.androidacademyapp.presentation.title

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.team23.androidacademyapp.domain.models.Model
import com.team23.androidacademyapp.domain.models.Quiz
import kotlinx.coroutines.launch

class LectureViewModel : ViewModel() {

    private val _lectureList: MutableLiveData<MutableList<Model>> = MutableLiveData()
    val lectureList: LiveData<MutableList<Model>>
        get() = _lectureList

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

                    val mQuestion: String = document.getString("question") ?: ""
                    val mAnswer1: String = document.getString("answer1") ?: ""
                    val mAnswer2: String = document.getString("answer2") ?: ""
                    val mAnswer3: String = document.getString("answer3") ?: ""
                    val mAnswer4: String = document.getString("answer4") ?: ""

                    var mRightAnswer = 0
                    try {
                        mRightAnswer = document.getDouble("rightanswer")?.toInt() ?: 0
                    }catch (e : Exception){}
                    try{
                        mRightAnswer = Integer.parseInt(document.getString("rightanswer")  ?: "0")
                    }catch (e: Exception){}
                    Log.e("XXX",mVideo)

                    val quizList = listOf<Quiz>(
                        Quiz(mQuestion, listOf(mAnswer1, mAnswer2, mAnswer3, mAnswer4), mRightAnswer)
                    )

                    val model = Model(
                       mVideo,mTitle,mDescription, mWorkshop, mFeedback, quizList)
                    listData.add(model)
                }
                _lectureList.postValue(listData)
            }
        }
    }

}