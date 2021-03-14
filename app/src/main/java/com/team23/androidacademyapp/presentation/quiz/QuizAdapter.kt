package com.team23.androidacademyapp.presentation.quiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.team23.androidacademyapp.R
import com.team23.androidacademyapp.domain.models.Quiz

class QuizAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var quiz: List<Quiz> = listOf<Quiz>()
    private var correctAnswers: MutableList<Boolean> = mutableListOf()

    override fun getItemViewType(position: Int): Int {
        if (position == quiz.size) {
            return 2
        } else {
            return 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            2 -> return QuizResultViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.quiz_result_fragment,
                    parent,
                    false
                )
            )
            else -> return QuizViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_quiz,
                    parent,
                    false
                )
            )

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            1 -> (holder as QuizViewHolder).onBind(quiz[position], position, this::answerListener)
            2 -> (holder as QuizResultViewHolder).onBind(correctAnswers.count { it -> it == true }.toString())
        }

    }

    override fun getItemCount(): Int {
        return if (quiz.isEmpty()) 0 else quiz.size + 1
    }

    fun bindQuiz(newQuiz: List<Quiz>) {
        quiz = newQuiz
        correctAnswers = MutableList(quiz.size){false}
        notifyDataSetChanged()
    }

    fun answerListener(position: Int, isCorrect: Boolean): Unit {
        correctAnswers[position] = isCorrect
    }

}

class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val question: TextView = itemView.findViewById(R.id.question)

    private val answerButtons: List<Button> = listOf(
        itemView.findViewById(R.id.answer_1),
        itemView.findViewById(R.id.answer_2),
        itemView.findViewById(R.id.answer_3),
        itemView.findViewById(R.id.answer_4)
    )

    private var correctAnswerId: Int? = null

    private var onAnswerListener: ((position: Int, isCorrect: Boolean) -> Unit)? = null

    fun onBind(
        quiz: Quiz,
        position: Int,
        onAnswerListener: (position: Int, isCorrect: Boolean) -> Unit
    ) {
        question.text = quiz.question

        answerButtons.forEachIndexed { index, button ->
            button.text = quiz.answers[index]
            button.setOnClickListener {
                this.onAnswer(position, it)
            }
        }

        correctAnswerId = quiz.rigthAnswer
        this.onAnswerListener = onAnswerListener
    }

    private fun onAnswer(position: Int, view: View) {
        var isCorrectAnswer = false
        when (view.id) {
            R.id.answer_1 -> isCorrectAnswer = correctAnswerId == 1
            R.id.answer_2 -> isCorrectAnswer = correctAnswerId == 2
            R.id.answer_3 -> isCorrectAnswer = correctAnswerId == 3
            R.id.answer_4 -> isCorrectAnswer = correctAnswerId == 4
        }
        
        onAnswerListener?.invoke(position, isCorrectAnswer)
    }
}

class QuizResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val resultTv = itemView.findViewById<TextView>(R.id.result)

    fun onBind(result: String) {
        resultTv.text = "Поздравляем!\n Вы правильно ответили на ${result} вопросов"
    }
}