package com.team23.androidacademyapp.presentation.quiz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.team23.androidacademyapp.R
import com.team23.androidacademyapp.domain.models.Model
import com.team23.androidacademyapp.domain.models.Quiz

class QuizFragment : Fragment() {

    companion object {
        fun newInstance() = QuizFragment()
    }

    private val onAnswerListener = this::onAnswer

    //    private lateinit var viewModel: QuizViewModel
    private lateinit var quizAdapter: QuizAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quiz_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quizAdapter = QuizAdapter()
        view.findViewById<ViewPager2>(R.id.pager).apply {
            this.adapter = quizAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)
//        // TODO: Use the ViewModel

        val model = getArguments()?.getParcelable<Model>("model")
        quizAdapter.bindQuiz(model?.quizes ?: emptyList<Quiz>())
    }

    private fun onAnswer(position: Int, isCorrect: Boolean) {
        Toast.makeText(this.context, "${isCorrect}", Toast.LENGTH_SHORT).show()
        Log.d("X1", "onAnswer: Pos: ${position}, isCorrect: ${isCorrect}")
    }

}