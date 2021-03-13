package com.team23.androidacademyapp.presentation.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.team23.androidacademyapp.R
import com.team23.androidacademyapp.domain.models.Model
import com.team23.androidacademyapp.domain.models.ModelQuiz

class QuizFragment : Fragment() {

    companion object {
        fun newInstance() = QuizFragment()
    }

    private val onAnswerListener = this::onAnswer

    private lateinit var viewModel: QuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quiz_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ViewPager2>(R.id.pager).apply {
            this.adapter = QuizAdapter(onAnswerListener)

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)
        // TODO: Use the ViewModel

        val model = getArguments()?.getParcelable<Model>("model");
    }

    fun onAnswer(position: Int, isCorrect: Boolean) {

    }

}

class QuizAdapter(val onAnswerListener: (position: Int, isCorrect: Boolean) -> Unit): RecyclerView.Adapter<QuizViewHolder>() {

    private var quiz: List<ModelQuiz> = listOf<ModelQuiz>()

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

    fun bindQuiz(newQuiz: List<ModelQuiz>) {
        quiz = newQuiz
    }

}

class QuizViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val question: TextView = itemView.findViewById(R.id.question)

    private val answerButtons: List<Button> = listOf(
        itemView.findViewById(R.id.answer_1),
        itemView.findViewById(R.id.answer_2),
        itemView.findViewById(R.id.answer_3),
        itemView.findViewById(R.id.answer_4)
    )

    private var correctAnswerId: Int? = null

    private var onAnswerListener: ((position: Int, isCorrect: Boolean) -> Unit)? = null

    fun onBind(quiz: ModelQuiz, position: Int, onAnswerListener: (position: Int, isCorrect: Boolean) -> Unit) {
        question.text = quiz.question

        answerButtons.forEachIndexed { index, button ->
            button.text = quiz.answers[index]
            button.setOnClickListener {
                this.onAnswer(position, it)
            }
        }

        correctAnswerId = quiz.correctAnswerID
        this.onAnswerListener = onAnswerListener
    }

    private fun onAnswer(position: Int, view: View){
        when (view.id) {
            R.id.answer_1 -> onAnswerListener?.invoke(position, correctAnswerId == 1)
            R.id.answer_2 -> onAnswerListener?.invoke(position, correctAnswerId == 2)
            R.id.answer_3 -> onAnswerListener?.invoke(position, correctAnswerId == 3)
            R.id.answer_4 -> onAnswerListener?.invoke(position, correctAnswerId == 4)
            else -> onAnswerListener?.invoke(position, false)
        }
    }
}