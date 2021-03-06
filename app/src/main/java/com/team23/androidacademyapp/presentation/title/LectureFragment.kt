package com.team23.androidacademyapp.presentation.title

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.team23.androidacademyapp.R
import com.team23.androidacademyapp.domain.LectureAdapter
import com.team23.androidacademyapp.domain.models.Model

class TitleFragment : Fragment() {

    companion object {
        fun newInstance() = TitleFragment()
    }

    private lateinit var viewModel: LectureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.title_fragment, container, false)

        val button: Button? = view?.findViewById(R.id.iv_quiz)
        button?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_quizFragment)
        )
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LectureViewModel::class.java)

        val listLecture: RecyclerView? = view?.findViewById(R.id.lecture_list)
        val myAdapter = LectureAdapter(onQuizeClickListener, onWorkshopClickListener, onFeedbackClickListener)
        listLecture?.adapter = myAdapter

        viewModel = ViewModelProvider(this).get(LectureViewModel::class.java)
        viewModel.lectureList.observe(viewLifecycleOwner, Observer { model->
            model?.let {
                myAdapter.submitList(model)
                myAdapter.notifyDataSetChanged()
            }
        })
    }

    private val onQuizeClickListener = object : OnQuizClickListener {
        override fun onClick(model: Model) {
            val args = Bundle()
            args.putParcelable("model", model)
            findNavController().navigate(R.id.action_titleFragment_to_quizFragment, args)
        }
    }

    private val onWorkshopClickListener = object : OnWorkshopClickListener {
        override fun onClick(model: Model) {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(model.wokrshop)
            ContextCompat.startActivity(this@TitleFragment.requireContext(), i, null)

         }
    }

    private val onFeedbackClickListener = object : OnFeedbackClickListener {
        override fun onClick(model: Model) {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(model.feedback)
            ContextCompat.startActivity(this@TitleFragment.requireContext(), i, null)
        }
    }
}

interface OnQuizClickListener{
    fun onClick(model : Model)
}

interface OnWorkshopClickListener{
    fun onClick(model : Model)
}

interface OnFeedbackClickListener{
    fun onClick(model : Model)
}