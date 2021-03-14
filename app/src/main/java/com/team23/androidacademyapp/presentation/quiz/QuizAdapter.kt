package com.team23.androidacademyapp.presentation.quiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.team23.androidacademyapp.R
import com.team23.androidacademyapp.domain.models.Quiz

class QuizAdapter(val onAnswerListener: (position: Int, isCorrect: Boolean) -> Unit) :
    RecyclerView.Adapter<QuizViewHolder>() {

    private var quiz: List<Quiz> = listOf<Quiz>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        return QuizViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_quiz,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.onBind(quiz[position], position, onAnswerListener)
    }

    override fun getItemCount(): Int {
        return quiz.size
    }

    fun bindQuiz(newQuiz: List<Quiz>) {
        quiz = newQuiz
        notifyDataSetChanged()
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
        when (view.id) {
            R.id.answer_1 -> onAnswerListener?.invoke(position, correctAnswerId == 1)
            R.id.answer_2 -> onAnswerListener?.invoke(position, correctAnswerId == 2)
            R.id.answer_3 -> onAnswerListener?.invoke(position, correctAnswerId == 3)
            R.id.answer_4 -> onAnswerListener?.invoke(position, correctAnswerId == 4)
            else -> onAnswerListener?.invoke(position, false)
        }
    }
}